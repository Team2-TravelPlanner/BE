package com.laioffer.travelplanner.model.common;


import java.util.List;

public class CustomizedPlanRequestModel {
    private List<String> places;
    private List<String> categories;
    private SettingsRequestModel settings;

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
}
