package com.krupa.maciej.model;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

public class OneMinute {
    private int id;
    private String hashedLocation;
    private String minuteTimestamp;

    public OneMinute(String hashedLocation, String minuteTimestamp) {
        this.hashedLocation = hashedLocation;
        this.minuteTimestamp = minuteTimestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashedLocation() {
        return hashedLocation;
    }

    public void setHashedLocation(String hashedLocation) {
        this.hashedLocation = hashedLocation;
    }

    public String getMinuteTimestamp() {
        return minuteTimestamp;
    }

    public void setMinuteTimestamp(String minuteTimestamp) {
        this.minuteTimestamp = minuteTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneMinute oneMinute = (OneMinute) o;
        if (minuteTimestamp.equals(oneMinute.minuteTimestamp) &&
                BCrypt.checkpw(hashedLocation, oneMinute.hashedLocation)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashedLocation, minuteTimestamp);
    }
}
