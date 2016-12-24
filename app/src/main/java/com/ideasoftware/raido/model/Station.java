package com.ideasoftware.raido.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by cembasarbaskan on 27/11/2016.
 */
@IgnoreExtraProperties
public class Station {

    public String stationName;
    public String playingSongName;

    public Station(){

    };

    public Station(String stationName, String playingSongName) {
        this.stationName = stationName;
        this.playingSongName = playingSongName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getPlayingSongName() {
        return playingSongName;
    }

    public void setPlayingSongName(String playingSongName) {
        this.playingSongName = playingSongName;
    }
}