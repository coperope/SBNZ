package main.facts;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    // TODO: 5/13/2020 Add missing fields and add annotations.

    @Id
    @SequenceGenerator(name="vehicle_id_seq",sequenceName="vehicle_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="vehicle_id_seq")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Brand brand;

    @OneToOne(fetch = FetchType.EAGER)
    private CarModel model;

    @OneToOne(fetch = FetchType.EAGER)
    private Fuel fuel;

    @OneToOne(fetch = FetchType.EAGER)
    private Transmission transmission;

    @Column(name = "seatsNo")
    private int seatsNo;

    @Column(name = "doorNo")
    private int doorNo;

    // In liters per 100km.
    @Column(name = "fuelConsumption")
    private double fuelConsumption;

    // If car has A/C.
    @Column(name = "ac")
    private boolean ac;

    @ManyToMany
    @JoinTable(name = "vehicle_categories", joinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "vehicle_tags", joinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

    // Features like sensors or auto a/c that come as an extra to the base version of a car.
    // In a luxury car it is expected to have these even in a basic version.
    @ManyToMany
    @JoinTable(name = "vehicle_extra_features", joinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "feature_id", referencedColumnName = "id"))
    private List<ExtraFeatures> features;

    // -1 for unlimited.
    @Column(name = "mileageLimit")
    private int mileageLimit;

    // In $ per km, zero for unlimited.
    @Column(name = "mileagePrice")
    private double mileagePrice;

    // In kW
    @Column(name = "power")
    private double power;

    // In kg
    @Column(name = "weight")
    private double weight;

    // In liters.
    @Column(name = "cargoVolume")
    private int cargoVolume;

    // In liters.
    @Column(name = "passengerAreaVolume")
    private int passengerAreaVolume;

    // In centimeters.
    @Column(name = "width")
    private int width;

    // In centimeters.
    @Column(name = "height")
    private int height;

    // In centimeters.
    @Column(name = "length")
    private int length;

    // In liters.
    @Column(name = "tankVolume")
    private double tankVolume;

    // In euros.
    @Column(name = "pricePerDay")
    private int pricePerDay;
    
    @Column(name = "numberOfStars")
    private int numberOfStars;

    @Column(name = "numberOfReviews")
    private int numberOfReviews;
    
    public Vehicle(){

    }

	public Vehicle(Long id, Brand brand, CarModel model, Fuel fuel, Transmission transmission, int seatsNo, int doorNo,
			double fuelConsumption, boolean ac, List<Category> categories, List<Tag> tags, List<ExtraFeatures> features,
			int mileageLimit, double mileagePrice, double power, double weight, int cargoVolume,
			int passengerAreaVolume, int width, int height, int length, double tankVolume, int pricePerDay) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.fuel = fuel;
		this.transmission = transmission;
		this.seatsNo = seatsNo;
		this.doorNo = doorNo;
		this.fuelConsumption = fuelConsumption;
		this.ac = ac;
		this.categories = categories;
		this.tags = tags;
		this.features = features;
		this.mileageLimit = mileageLimit;
		this.mileagePrice = mileagePrice;
		this.power = power;
		this.weight = weight;
		this.cargoVolume = cargoVolume;
		this.passengerAreaVolume = passengerAreaVolume;
		this.width = width;
		this.height = height;
		this.length = length;
		this.tankVolume = tankVolume;
		this.pricePerDay = pricePerDay;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		return "Vehicle [id=" + id + ", brand=" + brand + ", model=" + model + ", fuel=" + fuel + ", transmission="
				+ transmission + ", seatsNo=" + seatsNo + ", doorNo=" + doorNo + ", fuelConsumption=" + fuelConsumption
				+ ", ac=" + ac + ", categories=" + categories + ", tags=" + tags + ", features=" + features
				+ ", mileageLimit=" + mileageLimit + ", mileagePrice=" + mileagePrice + ", power=" + power + ", weight="
				+ weight + ", cargoVolume=" + cargoVolume + ", passengerAreaVolume=" + passengerAreaVolume + ", width="
				+ width + ", height=" + height + ", length=" + length + ", tankVolume=" + tankVolume + ", pricePerDay="
				+ pricePerDay + "]";
	}
}
