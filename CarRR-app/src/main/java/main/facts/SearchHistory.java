package main.facts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import main.dto.BrandDTO;
import main.dto.CarModelDTO;
import main.dto.SearchDTO;
import main.repository.BrandRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class SearchHistory implements Serializable {

	@Autowired
	@Transient
	ModelMapper modelMapper;

	@Autowired
	@Transient
	BrandRepo brandRepo;

	@Id
	@SequenceGenerator(name = "search_history_id_seq", sequenceName = "search_history_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "search_history_id_seq")
	private Long id;

	@ElementCollection
	@CollectionTable(name = "category_search_mapping", joinColumns = {
			@JoinColumn(name = "search_history_id", referencedColumnName = "id") })
	@MapKeyColumn(name = "category_id")
	@Column(name = "no_of_occurrences")
	private Map<Category, Integer> categories;

	@ElementCollection
	@CollectionTable(name = "tag_search_mapping", joinColumns = {
			@JoinColumn(name = "search_history_id", referencedColumnName = "id") })
	@MapKeyColumn(name = "tag_id")
	@Column(name = "no_of_occurrences")
	private Map<Tag, Integer> tags;

	@ElementCollection
	@CollectionTable(name = "brand_search_mapping", joinColumns = {
			@JoinColumn(name = "search_history_id", referencedColumnName = "id") })
	@MapKeyColumn(name = "brand_id")
	@Column(name = "no_of_occurrences")
	private Map<Brand, Integer> brands;

	@ElementCollection
	@CollectionTable(name = "model_search_mapping", joinColumns = {
			@JoinColumn(name = "search_history_id", referencedColumnName = "id") })
	@MapKeyColumn(name = "model_id")
	@Column(name = "no_of_occurrences")
	private Map<CarModel, Integer> model;

	@ElementCollection
	@CollectionTable(name = "fuel_search_mapping", joinColumns = {
			@JoinColumn(name = "search_history_id", referencedColumnName = "id") })
	@MapKeyColumn(name = "fuel_id")
	@Column(name = "no_of_occurrences")
	private Map<Fuel, Integer> fuel;

	@ElementCollection
	@CollectionTable(name = "transmission_search_mapping", joinColumns = {
			@JoinColumn(name = "search_history_id", referencedColumnName = "id") })
	@MapKeyColumn(name = "transmission_id")
	@Column(name = "no_of_occurrences")
	private Map<Transmission, Integer> transmission;

	@ElementCollection
	@CollectionTable(name = "seatsNo_search_mapping", joinColumns = {
			@JoinColumn(name = "search_history_id", referencedColumnName = "id") })
	@MapKeyColumn(name = "seats_id")
	@Column(name = "no_of_occurrences")
	private Map<Integer, Integer> seatsNo;

	@ElementCollection
	@CollectionTable(name = "doorNo_search_mapping", joinColumns = {
			@JoinColumn(name = "search_history_id", referencedColumnName = "id") })
	@MapKeyColumn(name = "door_id")
	@Column(name = "no_of_occurrences")
	private Map<Integer, Integer> doorNo;

	// In liters per 100km.
	@ElementCollection
	@CollectionTable(name = "fuelConsumption_search_mapping", joinColumns = {
			@JoinColumn(name = "search_history_id", referencedColumnName = "id") })
	@MapKeyColumn(name = "fuelConsumption_id")
	@Column(name = "no_of_occurrences")
	private Map<Double, Integer> fuelConsumption;
	
	public SearchHistory() {
		super();
		this.brands = new HashMap<Brand, Integer>();
		this.categories = new HashMap<Category, Integer>();
		this.doorNo = new HashMap<Integer, Integer>();
		this.fuel = new HashMap<Fuel, Integer>();
		this.fuelConsumption = new HashMap<Double, Integer>();
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

	public Map<Double, Integer> getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(Map<Double, Integer> fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	public void updateSearchHistory(SearchDTO searchParam) {
		Integer factor = searchParam.getScaleFactor();

		// Category update
		for (Category category : searchParam.getCategories()) {
			Integer categoryValue = this.categories.get(category);
			if (categoryValue != null) {
				categoryValue += factor;
			} else {
				this.categories.put(category, factor);
			}
		}

		// Tag update
		for (Tag tag : searchParam.getTags()) {
			Integer tagValue = this.tags.get(tag);
			if (tagValue != null) {
				tagValue += factor;
			} else {
				this.tags.put(tag, factor);
			}
		}
		
		// Brand update
		for (BrandDTO brandDTO : searchParam.getBrands()) {
//			Integer brandValue = this.brands.get(modelMapper.map(brandDTO, Brand.class));
			System.out.println(this.brands);
			System.out.println("******************");
			System.out.println(brandRepo);
			Integer brandValue = this.brands.get(brandRepo.findById(brandDTO.getId()));
			if (brandValue != null) {
				brandValue += factor;
			} else {
				this.brands.put(modelMapper.map(brandDTO, Brand.class), factor);
			}
		}

		// Model update
		for (CarModelDTO modelDTO : searchParam.getModels()) {
			Integer modelValue = this.model.get(modelMapper.map(modelDTO, CarModel.class));
			if (modelValue != null) {
				modelValue += factor;
			} else {
				this.model.put(modelMapper.map(modelDTO, CarModel.class), factor);
			}
		}

		// Fuel update
		for (Fuel fuel : searchParam.getFuels()) {
			Integer fuelValue = this.fuel.get(fuel);
			if (fuelValue != null) {
				fuelValue += factor;
			} else {
				this.fuel.put(fuel, factor);
			}
		}

		// Transmission update
		for (Transmission transmission : searchParam.getTransmissions()) {
			Integer transmissionValue = this.transmission.get(transmission);
			if (transmissionValue != null) {
				transmissionValue += factor;
			} else {
				this.transmission.put(transmission, factor);
			}
		}

		// Number of seats update
		for (Integer seatsNo : searchParam.getSeatsNo()) {
			Integer seatsNoValue = this.seatsNo.get(seatsNo);
			if (seatsNoValue != null) {
				seatsNoValue += factor;
			} else {
				this.seatsNo.put(seatsNo, factor);
			}
		}

		// Number of doors update
		for (Integer doorNo : searchParam.getDoorNo()) {
			Integer doorNoValue = this.doorNo.get(doorNo);
			if (doorNoValue != null) {
				doorNoValue += factor;
			} else {
				this.doorNo.put(doorNo, factor);
			}
		}

		// Number of doors update
		for (Double fuelConsumption : searchParam.getFuelConsumptions()) {
			Integer fuelConsumptionsValue = this.fuelConsumption.get(fuelConsumption);
			if (fuelConsumptionsValue != null) {
				fuelConsumptionsValue += factor;
			} else {
				this.fuelConsumption.put(fuelConsumption, factor);
			}
		}

	}

	@Override
	public String toString() {
		return "SearchHistory [id=" + id + "]";
	}
}
