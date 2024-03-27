package com.example.walkfromhome;

public class WalkData {
    private String userId,distance;
    public WalkData(){

    }

    public WalkData( String distance) {
        this.distance = distance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
