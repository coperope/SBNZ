package main.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.facts.*;

public class SearchDTO {

	private UserDTO customer;
	private List<Category> categories;
	private List<Tag> tags;
	private List<BrandDTO> brands;
	private List<CarModelDTO> models;
	private List<Fuel> fuels;
	private List<Transmission> transmissions;
	private List<Integer> seatsNo;
	private List<Integer> doorNo;
	private List<Double> fuelConsumptions;
	private Integer scaleFactor;
//	private Customer realCustomerForRules;

	public SearchDTO() {
		super();
		this.categories = new ArrayList<Category>();
		this.tags = new ArrayList<Tag>();
		this.brands = new ArrayList<BrandDTO>();
		this.models = new ArrayList<CarModelDTO>();
		this.fuels = new ArrayList<Fuel>();
		this.transmissions = new ArrayList<Transmission>();
		this.seatsNo = new ArrayList<Integer>();
		this.doorNo = new ArrayList<Integer>();
		this.fuelConsumptions = new ArrayList<Double>();
		this.scaleFactor = 1;
	}
	
	public SearchDTO(UserDTO customer, List<Category> categories, List<Tag> tags, List<BrandDTO> brands,
			List<CarModelDTO> models, List<Fuel> fuels, List<Transmission> transmissions, List<Integer> seatsNo,
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

//		this.realCustomerForRules = realCustomerForRules;
	}

	public UserDTO getCustomer() {
		return customer;
	}

	public void setCustomer(UserDTO customer) {
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

	public List<BrandDTO> getBrands() {
		return brands;
	}

	public void setBrands(List<BrandDTO> brands) {
		this.brands = brands;
	}

	public List<CarModelDTO> getModels() {
		return models;
	}

	public void setModels(List<CarModelDTO> models) {
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

//	public Customer getRealCustomerForRules() {
//		return realCustomerForRules;
//	}
//
//	public void setRealCustomerForRules(Customer realCustomerForRules) {
//		this.realCustomerForRules = realCustomerForRules;
//	}

	@Override
	public String toString() {
		return "SearchDTO{" +
				"customer=" + customer +
				", categories=" + categories +
				", tags=" + tags +
				", brands=" + brands +
				", models=" + models +
				", fuels=" + fuels +
				", transmissions=" + transmissions +
				", seatsNo=" + seatsNo +
				", doorNo=" + doorNo +
				", fuelConsumptions=" + fuelConsumptions +
				", scaleFactor=" + scaleFactor +
				'}';
	}
}
