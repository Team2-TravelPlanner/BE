package com.laioffer.travelplanner.services;


import java.util.List;

import com.laioffer.travelplanner.model.common.OperationResponse;

public interface FetchService {
    public OperationResponse FetchCategories(List<String> places);
}
