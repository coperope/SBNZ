package main.dto;

import main.facts.*;

import javax.persistence.*;
import java.util.List;

public class VehicleDTO {

    private Long id;

    private Brand brand;

    private CarModel model;

    private Fuel fuel;

    private Transmission transmission;

    private int seatsNo;

    private int doorNo;

    // In liters per 100km.
    private double fuelConsumption;

    // If car has A/C.
    private boolean ac;

    private List<Category> categories;

    private List<Tag> tags;

    // Features like sensors or auto a/c that come as an extra to the base version of a car.
    // In a luxury car it is expected to have these even in a basic version.
    private List<ExtraFeatures> features;

    // -1 for unlimited.
    private int mileageLimit;

    // In $ per km, zero for unlimited.
    private double mileagePrice;

    // In kW
    private double power;

    // In kg
    private double weight;

    // In liters.
    private int cargoVolume;

    // In liters.
    private int passengerAreaVolume;

    // In centimeters.
    private int width;

    // In centimeters.
    private int height;

    // In centimeters.
    private int length;

    // In liters.
    private double tankVolume;

    // In euros.
    private int pricePerDay;

    private int numberOfStars;

    private int numberOfReviews;

    public VehicleDTO(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
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

    public List<ExtraFeatures> getFeatures() {
        return features;
    }

    public void setFeatures(List<ExtraFeatures> features) {
        this.features = features;
    }

    public int getMileageLimit() {
        return mileageLimit;
    }

    public void setMileageLimit(int mileageLimit) {
        this.mileageLimit = mileageLimit;
    }

    public double getMileagePrice() {
        return mileagePrice;
    }

    public void setMileagePrice(double mileagePrice) {
        this.mileagePrice = mileagePrice;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(int cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

    public int getPassengerAreaVolume() {
        return passengerAreaVolume;
    }

    public void setPassengerAreaVolume(int passengerAreaVolume) {
        this.passengerAreaVolume = passengerAreaVolume;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(double tankVolume) {
        this.tankVolume = tankVolume;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "id=" + id +
                ", brand=" + brand +
                ", model=" + model +
                ", fuel=" + fuel +
                ", transmission=" + transmission +
                ", seatsNo=" + seatsNo +
                ", doorNo=" + doorNo +
                ", fuelConsumption=" + fuelConsumption +
                ", ac=" + ac +
                ", categories=" + categories +
                ", tags=" + tags +
                ", features=" + features +
                ", mileageLimit=" + mileageLimit +
                ", mileagePrice=" + mileagePrice +
                ", power=" + power +
                ", weight=" + weight +
                ", cargoVolume=" + cargoVolume +
                ", passengerAreaVolume=" + passengerAreaVolume +
                ", width=" + width +
                ", height=" + height +
                ", length=" + length +
                ", tankVolume=" + tankVolume +
                ", pricePerDay=" + pricePerDay +
                ", numberOfStars=" + numberOfStars +
                ", numberOfReviews=" + numberOfReviews +
                '}';
    }
}
