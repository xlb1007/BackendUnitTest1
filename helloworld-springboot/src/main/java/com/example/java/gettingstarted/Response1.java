package com.example.java.gettingstarted;

/**
 * Created by boli on 5/8/17.
 */
public class Response1 {
    //long uid;
    //long tid;
    //long gid;
    String lat;
    String lon;
    String name;

    //public Response1 (long uid, long tid, long gid, double lat, double lon, HashMap<Long, Gym> array) {
    public Response1 (String lat, String lon, String name) {
        this.lat = lat;
        this.lon = lon;
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getName() {
        return name;
    }

}
