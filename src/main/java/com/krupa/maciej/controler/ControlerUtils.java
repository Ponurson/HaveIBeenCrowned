package com.krupa.maciej.controler;

import java.util.ArrayList;

public class ControlerUtils {

    public static ArrayList<String> getIntermediateTimestamps(String startTimestamp, String stopTimestamp) {
        Long startTime = Long.parseLong(startTimestamp);
        Long endTime = Long.parseLong(stopTimestamp);
        Long fullFiveMin = startTime - (startTime % (5 * 60 * 1000));

        ArrayList outList = new ArrayList<String>();

        while (fullFiveMin + 5 * 60 * 1000 <= endTime) {
            fullFiveMin += 5 * 60 * 1000;
            outList.add(fullFiveMin + "");
        }

        return outList;
    }

}



