package main.facts;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Rental implements Serializable {
    @Id
    @SequenceGenerator(name="rental_id_seq",sequenceName="rental_id_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="rental_id_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @OneToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Column(name = "dateFrom")
    private String dateFrom;

    @Column(name = "dateTo")
    private String dateTo;

    public Rental(){

    }

    public Rental(Vehicle vehicle, User owner, Customer customer, String dateFrom, String dateTo) {
        this.vehicle = vehicle;
        this.owner = owner;
        this.customer = customer;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
