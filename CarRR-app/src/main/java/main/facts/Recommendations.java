package main.facts;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Recommendations {
    @Id
    @SequenceGenerator(name="recommendations_id_seq",sequenceName="recommendations_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="recommendations_id_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    Customer customer;

    @ElementCollection
    @CollectionTable(name = "search_recommendation_mapping",
            joinColumns = {@JoinColumn(name = "recommendations_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "vehicle_id")
    @Column(name = "no_of_hits")
    Map<Vehicle, Integer> searchMap;

    @ElementCollection
    @CollectionTable(name = "rental_recommendation_mapping",
            joinColumns = {@JoinColumn(name = "recommendations_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "vehicle_id")
    @Column(name = "no_of_hits")
    Map<Vehicle, Integer> rentalMap;

    @ElementCollection
    @CollectionTable(name = "preferences_recommendation_mapping",
            joinColumns = {@JoinColumn(name = "recommendations_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "vehicle_id")
    @Column(name = "no_of_hits")
    Map<Vehicle, Integer> preferencesMap;

    public Recommendations() {
        searchMap = new HashMap<Vehicle, Integer>();
        rentalMap = new HashMap<Vehicle, Integer>();
        preferencesMap = new HashMap<Vehicle, Integer>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<Vehicle, Integer> getSearchMap() {
        return searchMap;
    }

    public void setSearchMap(HashMap<Vehicle, Integer> searchMap) {
        this.searchMap = searchMap;
    }

    public Map<Vehicle, Integer> getRentalMap() {
        return rentalMap;
    }

    public void setRentalMap(HashMap<Vehicle, Integer> rentalMap) {
        this.rentalMap = rentalMap;
    }

    public Map<Vehicle, Integer> getPreferencesMap() {
        return preferencesMap;
    }

    public void setPreferencesMap(HashMap<Vehicle, Integer> preferencesMap) {
        this.preferencesMap = preferencesMap;
    }

    public void addPreferencesHits(Vehicle vehicle, int hits) {
        if(preferencesMap.containsKey(vehicle)) {
            preferencesMap.put(vehicle, preferencesMap.get(vehicle) + hits);
        } else {
            preferencesMap.put(vehicle, hits);
        }
    }
    
    public void addRentalHits(Vehicle vehicle, Object hits) {
        if (hits == null) {
            return;
        }
        Integer hitsInt = (Integer) hits;
        if(rentalMap.containsKey(vehicle)) {
        	rentalMap.put(vehicle, rentalMap.get(vehicle) + hitsInt);
        } else {
        	rentalMap.put(vehicle, hitsInt);
        }
    }
    
    public void addSearchHits(Vehicle vehicle, Object hits) {
		if (hits == null) {
			return;
		}
		Integer hitsInt = (Integer) hits;
		if(searchMap.containsKey(vehicle)) {
			searchMap.put(vehicle, searchMap.get(vehicle) + hitsInt);
		} else {
			searchMap.put(vehicle, hitsInt);
		}
	}

    @Override
    public String toString() {
        return "Recommendations{" +
                "id=" + id +
                ",\n customer=" + customer +
                ",\n searchMap=" + searchMap +
                ",\n rentalMap=" + rentalMap +
                ",\n preferencesMap=" + preferencesMap +
                '}';
    }
}
