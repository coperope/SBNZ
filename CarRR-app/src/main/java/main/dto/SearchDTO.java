package main.dto;

import java.util.List;
import java.util.Map;

import main.facts.Brand;
import main.facts.CarModel;
import main.facts.Category;
import main.facts.Customer;
import main.facts.Fuel;
import main.facts.Tag;
import main.facts.Transmission;

public class SearchDTO {

	private Customer customer;
	private List<Category> categories;
	private List<Tag> tags;
	private List<Brand> brands;
	private List<CarModel> models;
	private List<Fuel> fuels;
	private List<Transmission> transmissions;
	private List<Integer> seatsNo;
	private List<Integer> doorNo;
	private List<Double> fuelConsumptions;
	private Integer scaleFactor;

	public SearchDTO() {
		super();
		this.scaleFactor = 1;
	}
	
	public SearchDTO(Customer customer, List<Category> categories, List<Tag> tags, List<Brand> brands,
			List<CarModel> models, List<Fuel> fuels, List<Transmission> transmissions, List<Integer> seatsNo,
			List<Integer> doorNo, List<Double> fuelConsumptions, Integer scaleFactor) {
		super();
		this.customer = customer;
		this.categories = categories;
		this.tags = tags;
		this.brands = brands;
		this.models = models;
		this.fuels = fuels;
		this.transmissions = transmissions;
		this.seatsNo = seatsNo;
		this.doorNo = doorNo;
		this.fuelConsumptions = fuelConsumptions;
		this.scaleFactor = scaleFactor;
	}



	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public List<CarModel> getModels() {
		return models;
	}

	public void setModels(List<CarModel> models) {
		this.models = models;
	}

	public List<Fuel> getFuels() {
		return fuels;
	}

	public void setFuels(List<Fuel> fuels) {
		this.fuels = fuels;
	}

	public List<Transmission> getTransmissions() {
		return transmissions;
	}

	public void setTransmissions(List<Transmission> transmissions) {
		this.transmissions = transmissions;
	}

	public List<Integer> getSeatsNo() {
		return seatsNo;
	}

	public void setSeatsNo(List<Integer> seatsNo) {
		this.seatsNo = seatsNo;
	}

	public List<Integer> getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(List<Integer> doorNo) {
		this.doorNo = doorNo;
	}

	public List<Double> getFuelConsumptions() {
		return fuelConsumptions;
	}

	public void setFuelConsumptions(List<Double> fuelConsumptions) {
		this.fuelConsumptions = fuelConsumptions;
	}

	public int getScaleFactor() {
		return scaleFactor;
	}

	public void setScaleFactor(Integer scaleFactor) {
		this.scaleFactor = scaleFactor;
	}
}
