package main.facts;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class RentalHistory implements Serializable {
    @Id
    @SequenceGenerator(name="rental_history_id_seq",sequenceName="rental_history_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="rental_history_id_seq")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "category_mapping",
            joinColumns = {@JoinColumn(name = "rental_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "category_id")
    @Column(name = "no_of_occurrences")
    private Map<Category, Integer> categories;

    @ElementCollection
    @CollectionTable(name = "tag_mapping",
            joinColumns = {@JoinColumn(name = "rental_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "tag_id")
    @Column(name = "no_of_occurrences")
    private Map<Tag, Integer> tags;

    @ElementCollection
    @CollectionTable(name = "brand_mapping",
            joinColumns = {@JoinColumn(name = "rental_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "brand_id")
    @Column(name = "no_of_occurrences")
    private  Map<Brand, Integer> brands;

    @ElementCollection
    @CollectionTable(name = "model_mapping",
            joinColumns = {@JoinColumn(name = "rental_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "model_id")
    @Column(name = "no_of_occurrences")
    private Map<CarModel, Integer> model;

    @ElementCollection
    @CollectionTable(name = "fuel_mapping",
            joinColumns = {@JoinColumn(name = "rental_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "fuel_id")
    @Column(name = "no_of_occurrences")
    private Map<Fuel, Integer> fuel;

    @ElementCollection
    @CollectionTable(name = "transmission_mapping",
            joinColumns = {@JoinColumn(name = "rental_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "transmission_id")
    @Column(name = "no_of_occurrences")
    private Map<Transmission, Integer> transmission;

    @ElementCollection
    @CollectionTable(name = "seatsNo_mapping",
            joinColumns = {@JoinColumn(name = "rental_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "seats_id")
    @Column(name = "no_of_occurrences")
    private Map<Integer, Integer> seatsNo;

    @ElementCollection
    @CollectionTable(name = "doorNo_mapping",
            joinColumns = {@JoinColumn(name = "rental_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "door_id")
    @Column(name = "no_of_occurrences")
    private Map<Integer, Integer> doorNo;

    // In liters per 100km.
    @ElementCollection
    @CollectionTable(name = "fuelConsumption_mapping",
            joinColumns = {@JoinColumn(name = "rental_history_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "fuelConsumption_id")
    @Column(name = "no_of_occurrences")
    private Map<Long, Integer> fuelConsumption;
    
    

	public RentalHistory() {
		super();
		this.brands = new HashMap<Brand, Integer>();
		this.categories = new HashMap<Category, Integer>();
		this.doorNo = new HashMap<Integer, Integer>();
		this.fuel = new HashMap<Fuel, Integer>();
		this.fuelConsumption = new HashMap<Long, Integer>();
		this.model = new HashMap<CarModel, Integer>();
		this.seatsNo = new HashMap<Integer, Integer>();
		this.tags = new HashMap<Tag, Integer>();
		this.transmission = new HashMap<Transmission, Integer>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<Category, Integer> getCategories() {
		return categories;
	}

	public void setCategories(Map<Category, Integer> categories) {
		this.categories = categories;
	}

	public Map<Tag, Integer> getTags() {
		return tags;
	}

	public void setTags(Map<Tag, Integer> tags) {
		this.tags = tags;
	}

	public Map<Brand, Integer> getBrands() {
		return brands;
	}

	public void setBrands(Map<Brand, Integer> brands) {
		this.brands = brands;
	}

	public Map<CarModel, Integer> getModel() {
		return model;
	}

	public void setModel(Map<CarModel, Integer> model) {
		this.model = model;
	}

	public Map<Fuel, Integer> getFuel() {
		return fuel;
	}

	public void setFuel(Map<Fuel, Integer> fuel) {
		this.fuel = fuel;
	}

	public Map<Transmission, Integer> getTransmission() {
		return transmission;
	}

	public void setTransmission(Map<Transmission, Integer> transmission) {
		this.transmission = transmission;
	}

	public Map<Integer, Integer> getSeatsNo() {
		return seatsNo;
	}

	public void setSeatsNo(Map<Integer, Integer> seatsNo) {
		this.seatsNo = seatsNo;
	}

	public Map<Integer, Integer> getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(Map<Integer, Integer> doorNo) {
		this.doorNo = doorNo;
	}

	public Map<Long, Integer> getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(Map<Long, Integer> fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

    public void updateRentalHistory(Vehicle vehicle) {
    	
    	//Category update
    	for (Category category : vehicle.getCategories()) {
    		Integer categoryValue = categories.get(category);
			if (categoryValue != null) {
				categoryValue += 1;
			}else {
				categories.put(category, 1);
			}
		}
    	
    	//Tag update
    	for (Tag tag : vehicle.getTags()) {
    		Integer tagValue = tags.get(tag);
			if (tagValue != null) {
				tagValue += 1;
			}else {
				tags.put(tag, 1);
			}
		}
    	
    	//Brand update
    	Integer brandValue = brands.get(vehicle.getBrand());
    	if (brandValue != null) {
    		brandValue += 1;
		}else {
			brands.put(vehicle.getBrand(), 1);
		}
    	
    	//Model update
    	Integer modelValue = model.get(vehicle.getModel());
    	if (modelValue != null) {
    		modelValue += 1;
		}else {
			model.put(vehicle.getModel(), 1);
		}
    	
    	//Fuel update
    	Integer fuelValue = fuel.get(vehicle.getFuel());
    	if (fuelValue != null) {
    		fuelValue += 1;
		}else {
			fuel.put(vehicle.getFuel(), 1);
		}
    	
    	//Transmission update
    	Integer transmissionValue = transmission.get(vehicle.getTransmission());
    	if (transmissionValue != null) {
    		transmissionValue += 1;
		}else {
			transmission.put(vehicle.getTransmission(), 1);
		}
    	
    	//Number of seats update
    	Integer seatsNoValue = seatsNo.get(vehicle.getSeatsNo());
    	if (seatsNoValue != null) {
    		seatsNoValue += 1;
		}else {
			seatsNo.put(vehicle.getSeatsNo(), 1);
		}
    	
    	//Number of door update
    	Integer doorNoValue = doorNo.get(vehicle.getDoorNo());
    	if (doorNoValue != null) {
    		doorNoValue += 1;
		}else{
			doorNo.put(vehicle.getDoorNo(), 1);
		}
    	
    	//Fuel consumption update
    	Integer fuelConsumptionValue = fuelConsumption.get(vehicle.getFuelConsumption());
    	if (fuelConsumptionValue != null) {
    		fuelConsumptionValue += 1;
		}else{
			fuelConsumption.put(vehicle.getFuelConsumption(), 1);
		}
    }

	@Override
	public String toString() {
		return "RentalHistory [id=" + id + "]";
	}    
}
