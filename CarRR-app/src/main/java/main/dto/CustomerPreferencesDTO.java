package main.dto;

import main.facts.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerPreferencesDTO {

    private Long id;
    private List<Category> categoryList = new ArrayList<Category>();
    private List<Tag> tagList = new ArrayList<Tag>();
    private List<BrandDTO> brandList = new ArrayList<BrandDTO>();
    private List<CarModelDTO> modelList = new ArrayList<CarModelDTO>();
    private List<Fuel> fuelList = new ArrayList<Fuel>();
    private List<Transmission> transmissionList = new ArrayList<Transmission>();
    // Customer likes <= seatsNo
    private int seatsNo;
    // Customer likes <= doorNo
    private int doorNo;
    // Customer likes <= fuelConsumption
    private double fuelConsumption;
    private boolean ac;
    // -1 for unlimited.
    private int mileageLimit;

    public CustomerPreferencesDTO() {
    }

    public CustomerPreferencesDTO(Long id, List<Category> categoryList, List<Tag> tagList, List<BrandDTO> brandList, List<CarModelDTO> modelList, List<Fuel> fuelList, List<Transmission> transmissionList, int seatsNo, int doorNo, double fuelConsumption, boolean ac, int mileageLimit) {
        this.id = id;
        this.categoryList = categoryList;
        this.tagList = tagList;
        this.brandList = brandList;
        this.modelList = modelList;
        this.fuelList = fuelList;
        this.transmissionList = transmissionList;
        this.seatsNo = seatsNo;
        this.doorNo = doorNo;
        this.fuelConsumption = fuelConsumption;
        this.ac = ac;
        this.mileageLimit = mileageLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<BrandDTO> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandDTO> brandList) {
        this.brandList = brandList;
    }

    public List<CarModelDTO> getModelList() {
        return modelList;
    }

    public void setModelList(List<CarModelDTO> modelList) {
        this.modelList = modelList;
    }

    public List<Fuel> getFuelList() {
        return fuelList;
    }

    public void setFuelList(List<Fuel> fuelList) {
        this.fuelList = fuelList;
    }

    public List<Transmission> getTransmissionList() {
        return transmissionList;
    }

    public void setTransmissionList(List<Transmission> transmissionList) {
        this.transmissionList = transmissionList;
    }

    public int getSeatsNo() {
        return seatsNo;
    }

    public void setSeatsNo(int seatsNo) {
        this.seatsNo = seatsNo;
    }

    public int getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(int doorNo) {
        this.doorNo = doorNo;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public boolean isAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public int getMileageLimit() {
        return mileageLimit;
    }

    public void setMileageLimit(int mileageLimit) {
        this.mileageLimit = mileageLimit;
    }

    @Override
    public String toString() {
        return "CustomerPreferencesDTO{" +
                "id=" + id +
                ", categoryList=" + categoryList +
                ", tagList=" + tagList +
                ", brandList=" + brandList +
                ", fuelList=" + fuelList +
                ", transmissionList=" + transmissionList +
                ", seatsNo=" + seatsNo +
                ", doorNo=" + doorNo +
                ", fuelConsumption=" + fuelConsumption +
                ", ac=" + ac +
                ", mileageLimit=" + mileageLimit +
                '}';
    }
}
