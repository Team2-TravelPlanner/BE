package com.laioffer.travelplanner.services.implementation;

import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.search.PlaceInfoModel;
import com.laioffer.travelplanner.model.search.SearchRequestModel;
import com.laioffer.travelplanner.model.search.SearchResponseModel;
import com.laioffer.travelplanner.repositories.PlaceRepository;
import com.laioffer.travelplanner.services.SearchService;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

@Component
public class SearchServiceImpl implements SearchService {

	private static final int DEFAULT_DISPLAY_LIMIT = 10;

	@Autowired
	private PlaceRepository placeRepository;

	@Resource
	RestHighLevelClient client;

	@Resource
	private ElasticsearchRestTemplate elasticsearchTemplate;

	@Override
	public boolean ping() throws IOException {
		ClusterHealthRequest request = new ClusterHealthRequest();
		request.indices("travel");
		request.timeout(TimeValue.timeValueSeconds(50));
		request.local(true);
		ClusterHealthResponse health = client.cluster().health(request, RequestOptions.DEFAULT);
		ClusterHealthStatus status = health.getStatus();
		if (status.value() == ClusterHealthStatus.RED.value()) {
			throw new RuntimeException(
					"elasticsearch cluster health status is red.");
		}
		return true;
	}

	@Override
	public SearchResponseModel searchPlaces(SearchRequestModel model) throws Exception {
		SearchResponseModel searchResponseModel = new SearchResponseModel();

		String keyword = model.getKeyword();
		String category = model.getCategory();

		BoolQueryBuilder boolQueryBuilder = boolQuery();
		// search by keyword
		//info 1
		if (!StringUtils.isEmpty(keyword)) {
			boolQueryBuilder.must(QueryBuilders.wildcardQuery("placeName", "*" + keyword + "*"));
		}
		// search by category
		if (!StringUtils.isEmpty(category)) {
			boolQueryBuilder.must(QueryBuilders.matchQuery("categories", category));
		}

		/**
		 * Used for the rangeQuery in the future
		 */
//		BoolQueryBuilder rangeQuery = boolQuery();
//		rangeQuery.must(rangeQuery("lat").gte(model.getLowerRightLat()).lte(model.getUpperLeftLat()));
//		rangeQuery.must(rangeQuery("lon").lte(model.getLowerRightLon()).lte(model.getUpperLeftLon()));

//		GeoBoundingBoxQueryBuilder geoBoundingBoxQueryBuilder = QueryBuilders.geoBoundingBoxQuery("location");
//		geoBoundingBoxQueryBuilder.setCorners(model.getUpperLeftLat(), model.getUpperLeftLon(), model.getLowerRightLat(), model.getLowerRightLon());
//		boolQueryBuilder.must(rangeQuery);

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(boolQueryBuilder);

		//info 2
		searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
//		searchSourceBuilder.sort(new FieldSortBuilder("placeId").order(SortOrder.ASC));

		SearchRequest searchRequest = new SearchRequest("travel");
		searchRequest.types(String.valueOf(Place.class));
		searchRequest.source(searchSourceBuilder);

//		MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("placeName", "");
//		matchQueryBuilder.fuzziness(Fuzziness.AUTO);

//		SearchResponse searchResponse = null;
//		try {
//			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//		} catch (Exception e) {
//			searchResponseModel.setOperationResponse(OperationResponse.getFailedResponse("Failed to search places in ES!"));
//			return searchResponseModel;
//		}

//		RestStatus status = searchResponse.status();
//		TimeValue took = searchResponse.getTook();
//		Boolean terminatedEarly = searchResponse.isTerminatedEarly();
//		boolean timedOut = searchResponse.isTimedOut();
//
//		SearchHits hits = searchResponse.getHits();
//		TotalHits totalHits = hits.getTotalHits();
//		// the total number of hits, must be interpreted in the context of totalHits.relation
//		long numHits = totalHits.value;
//		// whether the number of hits is accurate (EQUAL_TO) or a lower bound of the total (GREATER_THAN_OR_EQUAL_TO)
//		TotalHits.Relation relation = totalHits.relation;
//		float maxScore = hits.getMaxScore();
//
//
//		SearchHit[] searchHits = hits.getHits();
//		for (SearchHit hit : searchHits) {
//			// do something with the SearchHit
//			String index = hit.getIndex();
//			String id = hit.getId();
//			float score = hit.getScore();
//		}

		Integer displayItemLimit = model.getDisplayItemLimit();
		if (displayItemLimit == null || displayItemLimit == 0) {
			displayItemLimit = DEFAULT_DISPLAY_LIMIT;
		}

		Integer currentPageNumber = model.getCurrentPageNumber();
		if (currentPageNumber == null) {
			currentPageNumber = 0;
		}

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
//				placeInfoModel.setAddress(place.getContent().getAddress());
				placeInfoModel.setLat(place.getContent().getLat());
				placeInfoModel.setLon(place.getContent().getLon());
				String address = place.getContent().getAddress();
				address = address.replaceAll("<(.*?)>", "");
				placeInfoModel.setAddress(address);
				placeInfoModel.setImageLink(place.getContent().getImageLink());
				placeInfoModel.setPopularity(place.getContent().getPopularity());
				placeInfoModel.setWebsite(place.getContent().getWebsite());
				placeInfoModel.setCategories(place.getContent().getCategories());

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
