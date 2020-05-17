package main.facts;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.SequenceGenerator;

import main.dto.SearchDTO;

@Entity
public class SearchHistory implements Serializable {
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
	@CollectionTable(name = "doorNo_mapping", joinColumns = {
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
		for (Brand brand : searchParam.getBrands()) {
			Integer brandValue = this.brands.get(brand);
			if (brandValue != null) {
				brandValue += factor;
			} else {
				this.brands.put(brand, factor);
			}
		}

		// Model update
		for (CarModel model : searchParam.getModels()) {
			Integer modelValue = this.model.get(model);
			if (modelValue != null) {
				modelValue += factor;
			} else {
				this.model.put(model, factor);
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
}
