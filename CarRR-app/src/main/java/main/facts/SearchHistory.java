package main.facts;

import javax.persistence.*;
import java.util.Map;

@Entity
public class SearchHistory {
    @Id
    @SequenceGenerator(name="search_history_id_seq",sequenceName="search_history_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="search_history_id_seq")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "category_search_mapping",
            joinColumns = {@JoinColumn(name = "search_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "category_id")
    @Column(name = "no_of_occurrences")
    private Map<Category, Integer> categories;

    @ElementCollection
    @CollectionTable(name = "tag_search_mapping",
            joinColumns = {@JoinColumn(name = "search_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "tag_id")
    @Column(name = "no_of_occurrences")
    private Map<Tag, Integer> tags;

    @ElementCollection
    @CollectionTable(name = "brand_search_mapping",
            joinColumns = {@JoinColumn(name = "search_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "brand_id")
    @Column(name = "no_of_occurrences")
    private  Map<Brand, Integer> brands;

    @ElementCollection
    @CollectionTable(name = "model_search_mapping",
            joinColumns = {@JoinColumn(name = "search_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "model_id")
    @Column(name = "no_of_occurrences")
    private Map<CarModel, Integer> model;

    @ElementCollection
    @CollectionTable(name = "fuel_search_mapping",
            joinColumns = {@JoinColumn(name = "search_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "fuel_id")
    @Column(name = "no_of_occurrences")
    private Map<Fuel, Integer> fuel;

    @ElementCollection
    @CollectionTable(name = "transmission_search_mapping",
            joinColumns = {@JoinColumn(name = "search_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "transmission_id")
    @Column(name = "no_of_occurrences")
    private Map<Transmission, Integer> transmission;

    @ElementCollection
    @CollectionTable(name = "seatsNo_search_mapping",
            joinColumns = {@JoinColumn(name = "search_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "seats_id")
    @Column(name = "no_of_occurrences")
    private Map<Integer, Integer> seatsNo;

    @ElementCollection
    @CollectionTable(name = "doorNo_mapping",
            joinColumns = {@JoinColumn(name = "search_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "door_id")
    @Column(name = "no_of_occurrences")
    private Map<Integer, Integer> doorNo;

    // In liters per 100km.
    @ElementCollection
    @CollectionTable(name = "fuelConsumption_search_mapping",
            joinColumns = {@JoinColumn(name = "search_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "fuelConsumption_id")
    @Column(name = "no_of_occurrences")
    private Map<Long, Integer> fuelConsumption;
}
