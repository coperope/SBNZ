package main.facts;

import java.util.HashMap;

public class PreferencesRecommendations {

	HashMap<Vehicle, Integer> map = new HashMap<Vehicle, Integer>();

	public HashMap<Vehicle, Integer> getMap() {
		return map;
	}

	public void setMap(HashMap<Vehicle, Integer> map) {
		this.map = map;
	}
	
	public void addHits(Vehicle vehicle, int hits) {
		if(map.containsKey(vehicle)) {
			map.put(vehicle, map.get(vehicle) + hits);
		} else {
			map.put(vehicle, hits);
		}
	}

	@Override
	public String toString() {
		return "PreferencesRecommendations [map=" + map.values() + "]";
	}
	
	
}
