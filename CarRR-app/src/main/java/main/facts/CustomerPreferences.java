package main.facts;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerPreferences implements Serializable {
    @Id
    @SequenceGenerator(name="customer_preferences_id_seq",sequenceName="customer_preferences_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="customer_preferences_id_seq")
    private Long id;

    @ManyToMany
    @JoinTable(name = "customer_preferences_category", joinColumns = @JoinColumn(name = "customer_preferences_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categoryList = new ArrayList<Category>();

    @ManyToMany
    @JoinTable(name = "customer_preferences_tag", joinColumns = @JoinColumn(name = "customer_preferences_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tagList = new ArrayList<Tag>();

    @ManyToMany
    @JoinTable(name = "customer_preferences_brand", joinColumns = @JoinColumn(name = "customer_preferences_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "brand_id", referencedColumnName = "id"))
    private List<Brand> brandList = new ArrayList<Brand>();

    @ManyToMany
    @JoinTable(name = "customer_preferences_fuel", joinColumns = @JoinColumn(name = "customer_preferences_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "fuel_id", referencedColumnName = "id"))
    private List<Fuel> fuelList = new ArrayList<Fuel>();

    @ManyToMany
    @JoinTable(name = "customer_preferences_transmission", joinColumns = @JoinColumn(name = "customer_preferences_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "transmission_id", referencedColumnName = "id"))
    private List<Transmission> transmissionList = new ArrayList<Transmission>();

    // Customer likes <= seatsNo
    @Column(name = "seatsNo")
    private int seatsNo;

    // Customer likes <= doorNo
    @Column(name = "doorNo")
    private int doorNo;

    // Customer likes <= fuelConsumption
    @Column(name = "fuelConsumption")
    private double fuelConsumption;

    @Column(name = "ac")
    private boolean ac;

    // -1 for unlimited.
    @Column(name = "mileageLimit")
    private int mileageLimit;


    public CustomerPreferences() {
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

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
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
}
