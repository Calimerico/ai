package com;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CityDB {

    public static final String ORADEA = "Oradea";
    public static final String ZERIND = "Zerind";
    public static final String ARAD = "Arad";
    public static final String TIMISOARA = "Timisoara";
    public static final String LUGOJ = "Lugoj";
    public static final String MEHADIA = "Mehadia";
    public static final String DOBRETA = "Dobreta";
    public static final String SIBIU = "Sibiu";
    public static final String RIMNICU_VILCEA = "RimnicuVilcea";
    public static final String CRAIOVA = "Craiova";
    public static final String FAGARAS = "Fagaras";
    public static final String PITESTI = "Pitesti";
    public static final String GIURGIU = "Giurgiu";
    public static final String BUCHAREST = "Bucharest";
    public static final String NEAMT = "Neamt";
    public static final String URZICENI = "Urziceni";
    public static final String IASI = "Iasi";
    public static final String VASLUI = "Vaslui";
    public static final String HIRSOVA = "Hirsova";
    public static final String EFORIE = "Eforie";

    private static Set<CityDistance> distances;
    private static Map<String,Integer> distanceFromBucharest;

    static {
        distances = new HashSet<>();
        distances.add(new CityDistance(ORADEA, ZERIND, 71.0));
        distances.add(new CityDistance(ORADEA, SIBIU, 151.0));
        distances.add(new CityDistance(ZERIND, ARAD, 75.0));
        distances.add(new CityDistance(ARAD, TIMISOARA, 118.0));
        distances.add(new CityDistance(ARAD, SIBIU, 140.0));
        distances.add(new CityDistance(TIMISOARA, LUGOJ, 111.0));
        distances.add(new CityDistance(LUGOJ, MEHADIA, 70.0));
        distances.add(new CityDistance(MEHADIA, DOBRETA, 75.0));
        distances.add(new CityDistance(DOBRETA, CRAIOVA, 120.0));
        distances.add(new CityDistance(SIBIU, FAGARAS, 99.0));
        distances.add(new CityDistance(SIBIU, RIMNICU_VILCEA, 80.0));
        distances.add(new CityDistance(RIMNICU_VILCEA, PITESTI, 97.0));
        distances.add(new CityDistance(RIMNICU_VILCEA, CRAIOVA, 146.0));
        distances.add(new CityDistance(CRAIOVA, PITESTI, 138.0));
        distances.add(new CityDistance(FAGARAS, BUCHAREST, 211.0));
        distances.add(new CityDistance(PITESTI, BUCHAREST, 101.0));
        distances.add(new CityDistance(GIURGIU, BUCHAREST, 90.0));
        distances.add(new CityDistance(BUCHAREST, URZICENI, 85.0));
        distances.add(new CityDistance(NEAMT, IASI, 87.0));
        distances.add(new CityDistance(URZICENI, VASLUI, 142.0));
        distances.add(new CityDistance(URZICENI, HIRSOVA, 98.0));
        distances.add(new CityDistance(IASI, VASLUI, 92.0));
        distances.add(new CityDistance(HIRSOVA, EFORIE, 86.0));

        distanceFromBucharest = new HashMap<>();
        distanceFromBucharest.put(ARAD, 366);
        distanceFromBucharest.put(BUCHAREST, 0);
        distanceFromBucharest.put(CRAIOVA, 160);
        distanceFromBucharest.put(DOBRETA, 242);
        distanceFromBucharest.put(EFORIE, 161);
        distanceFromBucharest.put(FAGARAS, 176);
        distanceFromBucharest.put(GIURGIU, 77);
        distanceFromBucharest.put(HIRSOVA, 151);
        distanceFromBucharest.put(IASI, 226);
        distanceFromBucharest.put(LUGOJ, 244);
        distanceFromBucharest.put(MEHADIA, 241);
        distanceFromBucharest.put(NEAMT, 234);
        distanceFromBucharest.put(ORADEA, 380);
        distanceFromBucharest.put(PITESTI, 100);
        distanceFromBucharest.put(RIMNICU_VILCEA, 193);
        distanceFromBucharest.put(SIBIU, 253);
        distanceFromBucharest.put(TIMISOARA, 329);
        distanceFromBucharest.put(URZICENI, 80);
        distanceFromBucharest.put(VASLUI, 199);
        distanceFromBucharest.put(ZERIND, 374);
    }

    public static Map<String, Double> fromCityWithDistances(String city) {
        Map<String, Double> map = new HashMap<>();
        distances.stream().
                filter(cityDistance -> cityDistance.getCity1().equals(city) || cityDistance.getCity2().equals(city))
                .forEach(cityDistance -> {
                    String city1 = cityDistance.getCity1();
                    String city2 = cityDistance.getCity2();
                    map.put(city1.equals(city) ? city2 : city1,cityDistance.getDistance());
                });
        return map;
    }

    public static Set<String> fromCity(String city) {
        Set<String> set = new HashSet<>();
        distances.stream().
                filter(cityDistance -> cityDistance.getCity1().equals(city) || cityDistance.getCity2().equals(city))
                .forEach(cityDistance -> {
                    String city1 = cityDistance.getCity1();
                    String city2 = cityDistance.getCity2();
                    set.add(city1.equals(city) ? city2 : city1);
                });
        return set;
    }

    public static Map<String, Integer> distanceFromBucharest() {
        return distanceFromBucharest;
    }
}
