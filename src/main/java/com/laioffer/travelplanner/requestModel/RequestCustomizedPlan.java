package com.laioffer.travelplanner.requestModel;

import java.util.List;

public class RequestCustomizedPlan {
    private List<String> places;
    private List<String> categories;
    private RequestSettingsModel settings;

    public RequestCustomizedPlan() {
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

    public RequestSettingsModel getSettings() {
        return settings;
    }

    public void setSettings(RequestSettingsModel settings) {
        this.settings = settings;
    }
}
