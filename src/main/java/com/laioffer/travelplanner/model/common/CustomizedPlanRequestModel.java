package com.laioffer.travelplanner.model.common;


import java.util.List;

public class CustomizedPlanRequestModel {
    private List<String> places;
    private List<String> categories;
    private SettingsRequestModel settings;
    // Bin added in order to compile
    private List <String> placeIds;


    public CustomizedPlanRequestModel() {
    }

    public List<String> getPlaces() {
        return places;
    }

    public void setPlaces(List<String> places) {
        this.places = places;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
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
