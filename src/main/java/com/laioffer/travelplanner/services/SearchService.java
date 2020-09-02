package com.laioffer.travelplanner.services;

import com.laioffer.travelplanner.model.search.SearchRequestModel;
import com.laioffer.travelplanner.model.search.SearchResponseModel;
import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;

public interface SearchService {
    public boolean ping() throws IOException;
    public SearchResponseModel searchPlaces(SearchRequestModel model) throws Exception;
    public SearchResponseModel getAllPlaces(SearchRequestModel model) throws Exception;
}
