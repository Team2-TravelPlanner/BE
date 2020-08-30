package com.laioffer.travelplanner.services;

import com.laioffer.travelplanner.model.search.SearchRequestModel;
import com.laioffer.travelplanner.model.search.SearchResponseModel;

import java.io.IOException;

public interface SearchService {
    public boolean ping() throws IOException;
    public SearchResponseModel searchPlaces(SearchRequestModel model) throws Exception;
}