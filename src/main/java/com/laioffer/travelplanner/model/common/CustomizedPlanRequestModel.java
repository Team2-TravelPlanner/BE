package com.laioffer.travelplanner.model.common;


import java.util.List;

public class CustomizedPlanRequestModel {

    private SettingsRequestModel settings;
    // Bin added in order to compile
    private List <String> placeIds;


    public CustomizedPlanRequestModel() {
    }

    public SettingsRequestModel getSettings() {
        return settings;
    }



    public void setSettings(SettingsRequestModel settings) {
        this.settings = settings;
    }


    // Bin added in order to compile
    public List <String> getPlaceIds() {
        return placeIds;
    }

    public void setPlaceIds(List <String> placeIds) { this.placeIds = placeIds; }
}
