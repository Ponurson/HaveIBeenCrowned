package com.krupa.maciej.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OneMinuteDAO {

    private static final String CREATE_ONE_MINUTE_QUERY =
            "INSERT INTO infected_routes_verified(infected_id, location, timestamp) VALUES (?, ?, ?)";
    private static final String READ_ONE_MINUTE_BY_TIMESTAMP_QUERY =
            "SELECT * FROM infected_routes_verified where timestamp >= ?";
    private static final String DELETE_ONE_MINUTE_QUERY =
            "DELETE FROM dania WHERE infected_id = ? and timestamp = ?";

    public static void delete(int infectedId, String timestamp) {
        try (Connection conn = DBUtils.connect()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_ONE_MINUTE_QUERY);
            statement.setInt(1, infectedId);
            statement.setString(2, timestamp);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<OneMinute> readByTimestamp(String timestamp) {
        List<OneMinute> oneMinutes = new ArrayList<>();
        try (Connection connection = DBUtils.connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ONE_MINUTE_BY_TIMESTAMP_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, timestamp);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                oneMinutes.add(mapUser(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oneMinutes;
    }

    public static OneMinute create(OneMinute oneMinute) {
        if (oneMinute != null) {
            try (Connection connection = DBUtils.connect()) {
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ONE_MINUTE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, oneMinute.getInfectedId());
                preparedStatement.setString(2, oneMinute.getHashedLocation());
                preparedStatement.setString(3, oneMinute.getMinuteTimestamp());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return oneMinute;
    }

    private static OneMinute mapUser(ResultSet resultSet) throws SQLException {
        OneMinute oneMinute = new OneMinute(resultSet.getInt("infected_id"),
                resultSet.getString("location"),
                resultSet.getString("timestamp"));
        return oneMinute;
    }
}
