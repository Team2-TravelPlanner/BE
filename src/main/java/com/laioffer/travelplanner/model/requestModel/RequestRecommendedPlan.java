package com.laioffer.travelplanner.model.requestModel;

//import sun.dc.pr.PRError;

import java.util.List;
import java.util.Map;

public class RequestRecommendedPlan {
    private List<String> categories;
    private RequestSettingsModel settings;

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
