package main.facts;

import java.util.HashMap;

public class RentalRecomendations {
    HashMap<Vehicle, Integer> map = new HashMap<Vehicle, Integer>();

    public HashMap<Vehicle, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<Vehicle, Integer> map) {
        this.map = map;
    }

    public void addHits(Vehicle vehicle, Object hits) {
        if (hits == null) {
            return;
        }
        Integer hitsInt = (Integer) hits;
        if(map.containsKey(vehicle)) {
            map.put(vehicle, map.get(vehicle) + hitsInt);
        } else {
            map.put(vehicle, hitsInt);
        }
    }

    @Override
    public String toString() {
        return "RentalRecommendations [map=" + map + "]";
    }
}
