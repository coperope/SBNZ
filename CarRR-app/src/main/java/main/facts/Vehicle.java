package main.facts;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    // TODO: 5/13/2020 Add missing fields and add annotations.

    @Id
    @SequenceGenerator(name="vehicle_id_seq",sequenceName="vehicle_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="vehicle_id_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @OneToOne(fetch = FetchType.LAZY)
    private CarModel model;

    @OneToOne(fetch = FetchType.LAZY)
    private Fuel fuel;

    @OneToOne(fetch = FetchType.LAZY)
    private Transmission transmission;

    @Column(name = "seatsNo")
    private int seatsNo;

    @Column(name = "doorNo")
    private int doorNo;

    // In liters per 100km.
    @Column(name = "fuelConsumption")
    private Long fuelConsumption;

    // If car has A/C.
    @Column(name = "ac")
    private boolean ac;

    @ManyToMany
    @JoinTable(name = "vehicle_categories", joinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "vehicle_tags", joinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

    // Zero for unlimited.
    @Column(name = "mileageLimit")
    private int mileageLimit;

    public Vehicle(){

    }
    public Vehicle(Long id, Brand brand, CarModel model, Fuel fuel, Transmission transmission, int seatsNo, int doorNo, Long fuelConsumption, boolean ac, List<Category> categories, List<Tag> tags) {
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

    public Long getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Long fuelConsumption) {
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

    @Override
    public String toString() {
        return "Vehicle{" +
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return getSeatsNo() == vehicle.getSeatsNo() &&
                getDoorNo() == vehicle.getDoorNo() &&
                isAc() == vehicle.isAc() &&
                getId().equals(vehicle.getId()) &&
                getBrand().equals(vehicle.getBrand()) &&
                getModel().equals(vehicle.getModel()) &&
                getFuel().equals(vehicle.getFuel()) &&
                getTransmission().equals(vehicle.getTransmission()) &&
                getFuelConsumption().equals(vehicle.getFuelConsumption()) &&
                Objects.equals(getCategories(), vehicle.getCategories()) &&
                Objects.equals(getTags(), vehicle.getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBrand(), getModel(), getFuel(), getTransmission(), getSeatsNo(), getDoorNo(), getFuelConsumption(), isAc());
    }
}
