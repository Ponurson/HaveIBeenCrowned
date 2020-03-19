package com.krupa.maciej.model;

public class OneMinute {
    private int infectedId;
    private String hashedLocation;
    private String minuteTimestamp;

    public OneMinute(int infectedId, String hashedLocation, String minuteTimestamp) {
        this.infectedId = infectedId;
        this.hashedLocation = hashedLocation;
        this.minuteTimestamp = minuteTimestamp;
    }

    public int getInfectedId() {
        return infectedId;
    }

    public void setInfectedId(int infectedId) {
        this.infectedId = infectedId;
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
}
