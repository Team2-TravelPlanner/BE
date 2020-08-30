package com.laioffer.travelplanner.antcolonyalgorithm;

import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.model.plan.PlaceDetailModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ACO {
    private CityGraph weight_distance;
    private List<Place> places;
    private List<Place> order;
    private Map<Integer, Place> map;
    private List<PlaceDetailModel> placeDetailModels;
//    private int citynum = places.size();
    private int p = 1000;//迭代次数
    private double bestLength;
    private String bestTour;
    private int antNum = 100;
    private int bestAnt = Integer.MAX_VALUE;
    private ANT[] ants;
    private double alpha = 1.0;
    private double beta = 5.0;
    private double rho = 0.5;
    private double Q = 1000;
    private long startTime;
    private long endTime;

    public ACO(List<Place> places) {
        this.places = places;
    }

    //初始化城市信息，假设为非对称TSP问题
    private void Init_Distance() {
        map = new HashMap<>();
        weight_distance = new CityGraph(places, map);
        order = new ArrayList<>();
        placeDetailModels = new ArrayList<>();
    }

    //参数初始化
    private void Init_paras() {
        bestLength = Double.MAX_VALUE;
        bestTour = "";
    }

    /**
     * P<最大迭代次数
     */
    private void Init_Ants() { //每次循环的每只蚂蚁都是新蚂蚁，没有残留信息
        ants = null;
        ants = new ANT[antNum];
        for (int i = 0; i < antNum; i++) {
            ants[i] = new ANT(places.size(), alpha, beta);
        }
    }

    //对每只蚂蚁按概率选择移动到下一个节点，直至遍历全部节点
    //当一只蚂蚁结束遍历时，应该更新全局信息素矩阵
    //计算每只蚂蚁的路径长度，
    private void MovetoNextCity() {
        for (int i = 0; i < antNum; i++) {
            ants[i].chooseNextCity();
//            ants[i].updatePheromone();//信息素更新在一次迭代结束
//            ants[i].calRoad();
        }
    }

    //记录当前最好解
    private void findBestRoad() {

        for (int i = 0; i < antNum; i++) {

            if (bestLength > ants[i].getRoadLength()) {
                bestLength = ants[i].getRoadLength();
                bestTour = ants[i].getRoad();
                bestAnt = i;
            }
        }
        System.out.println("current best solution：" + bestTour);
        System.out.println("Shortest Path：" + bestLength);
    }
    //按更新方程修改轨迹长度

    private void updatePheromone() {
        for (int i = 0; i < antNum; i++) {
            ants[i].updatePheromone(Q, (endTime - startTime) / 1000);
        }

    }

    /**
     * 迭代结束
     */
    public void iterator() {
        Init_Distance();
        Init_paras();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < p; i++) {//一次迭代即更新了一次解空间
            System.out.println("The" + i + "times iteration：");
            Init_Ants();
            MovetoNextCity();
            findBestRoad();
            endTime = System.currentTimeMillis();
            updatePheromone();
        }
        for (int i = 0; i < bestTour.length(); i = i + 2) {
//            for(int j = 0; j < places.size(); j++) {
//                if (Character.getNumericValue(bestTour.charAt(i)) == Integer.valueOf(places.get(j).getPlaceId())) {
//                    order.add(places.get(j));
//                    PlaceDetail p = new PlaceDetail();
//                    p.setPlace(places.get(j));
//                    placeDetails.add(p);
//                }
//            }
            PlaceDetailModel p = new PlaceDetailModel();
            p.setPlace(map.get(Character.getNumericValue(bestTour.charAt(i))));
            placeDetailModels.add(p);
        }
    }

    public String getBestTour() {
        return bestTour;
    }

    public List<Place> getOrder() {
        return order;
    }

    public List<PlaceDetailModel> getPlaceDetailModels() {
        return placeDetailModels;
    }

//    public static void main(String[] args) {
//        List<Place> places = new ArrayList<>();
//        Place place1 = new Place("1", "Empire State Building", "20 W 34th St, New York, NY 10001", -73.985428, 40.748817);
//        Place place2 = new Place("2", "Times Square", "20 W 34th St, New York, NY 10001", -73.985130, 40.758896);
//        Place place3 = new Place("3", "Memorial & Museum", "20 W 34th St, New York, NY 10001", -74.013611, 40.711694);
//        Place place4 = new Place("4","Charging Bull", "20 W 34th St, New York, NY 10001", -74.0134, 40.7056);
//        Place place5 = new Place("5", "United Nations Headquarters", "20 W 34th St, New York, NY 10001", -73.9679163, 40.7495461);
//        Place place6 = new Place("6", "Museum of the City of New York", "20 W 34th St, New York, NY 10001", -73.962311, 40.778965);
//        Place place7 = new Place("7", "Statue of Liberty National Monument", "20 W 34th St, New York, NY 10001", -74.044502, 40.689247);
//        Place place8 = new Place("8", "Brooklyn Bridge", "20 W 34th St, New York, NY 10001", -73.997002, 40.706001);
//        Place place9 = new Place("9", "General Grant National Memorial", "20 W 34th St, New York, NY 10001", -73.9578, 40.8081);
//        places.add(place1);
//        places.add(place2);
//        places.add(place3);
//        places.add(place4);
//        places.add(place5);
//        places.add(place6);
//        places.add(place7);
//        places.add(place8);
//        places.add(place9);
//
//        ACO aco = new ACO(places);
//        aco.iterator();
}
