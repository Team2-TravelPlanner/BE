package com.laioffer.travelplanner.services.implementation;

import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.search.PlaceInfoModel;
import com.laioffer.travelplanner.model.search.SearchRequestModel;
import com.laioffer.travelplanner.model.search.SearchResponseModel;
import com.laioffer.travelplanner.repositories.PlaceRepository;
import com.laioffer.travelplanner.services.SearchService;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Query;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoBoundingBoxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchServiceImpl implements SearchService {

	private static final int DEFAULT_DISPLAY_LIMIT = 10;

	@Autowired
	private PlaceRepository placeRepository;

	@Resource
	TransportClient client;

	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public boolean ping() {
		ActionFuture<ClusterHealthResponse> health = client.admin().cluster().health(new ClusterHealthRequest());
		ClusterHealthStatus status = health.actionGet().getStatus();
		if (status.value() == ClusterHealthStatus.RED.value()) {
			throw new RuntimeException(
					"elasticsearch cluster health status is red.");
		}
		return true;
	}

	@Override
	public SearchResponseModel searchPlaces(SearchRequestModel model) throws Exception {
		SearchResponseModel searchResponseModel = new SearchResponseModel();
		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

		GeoBoundingBoxQueryBuilder geoBoundingBoxQueryBuilder = QueryBuilders.geoBoundingBoxQuery("location")
				.setCorners(
						GeoPoint.fromGeohash(String.valueOf(new GeoPoint(model.getUpperLeftLat(), model.getUpperLeftLon()))),
						new GeoPoint(model.getLowerRightLat(), model.getLowerRightLon())
				);

		Integer displayItemLimit = model.getDisplayItemLimit();
		if (displayItemLimit == null || displayItemLimit == 0) {
			displayItemLimit = DEFAULT_DISPLAY_LIMIT;
		}

		Integer currentPageNumber = model.getCurrentPageNumber();
		if (currentPageNumber == null) {
			currentPageNumber = 0;
		}

		boolQueryBuilder.filter(geoBoundingBoxQueryBuilder);
		Query searchQuery = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withPageable(PageRequest.of(currentPageNumber, displayItemLimit)).build();

		List<PlaceInfoModel> placeInfoModelList = new ArrayList<>();
		SearchHits<Place> placeSearchHits = null;
		try {
//			placeList = elasticsearchTemplate.queryForList(searchQuery, Place.class);
			placeSearchHits = elasticsearchTemplate.search(searchQuery, Place.class, IndexCoordinates.of("travel"));
//        	int pages = elasticsearchTemplate.queryForPage(searchQuery, Place.class);
			placeSearchHits.forEach(place -> {
				PlaceInfoModel placeInfoModel = new PlaceInfoModel();
				placeInfoModel.setId(place.getContent().getPlaceId());
				placeInfoModel.setName(place.getContent().getPlaceName());
				placeInfoModel.setAddress(place.getContent().getAddress());
				placeInfoModelList.add(placeInfoModel);
			});

		} catch (Exception e) {
			searchResponseModel.setOperationResponse(OperationResponse.getFailedResponse("Failed to search places in ES!"));
			return searchResponseModel;
		}

		int maxPageNumber = 0;
		if (placeSearchHits.getTotalHits() % displayItemLimit == 0) {
			maxPageNumber = (int) (placeSearchHits.getTotalHits() / displayItemLimit);
		} else {
			maxPageNumber = (int) (placeSearchHits.getTotalHits() / displayItemLimit) + 1;
		}
		searchResponseModel.setCurrentPageNumber(currentPageNumber);
		searchResponseModel.setPlaceInfoModels(placeInfoModelList);
		searchResponseModel.setMaxPageNumber(maxPageNumber);
		searchResponseModel.setOperationResponse(OperationResponse.getSuccessResponse());
		return searchResponseModel;
	}
}
