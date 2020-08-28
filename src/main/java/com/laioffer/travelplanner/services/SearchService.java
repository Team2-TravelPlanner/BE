package com.laioffer.travelplanner.services;

import com.laioffer.travelplanner.model.search.SearchRequestModel;
import com.laioffer.travelplanner.model.search.SearchResponseModel;

public interface SearchService {
    public SearchResponseModel searchPlaces(SearchRequestModel model) throws Exception;
}