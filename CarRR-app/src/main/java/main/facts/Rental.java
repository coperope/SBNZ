package main.facts;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    private long dateFrom;

    @Column(name = "dateTo")
    private long dateTo;

    @Column(name = "traveledKm")
    private Double traveledKm;

    @Column(name = "rating")
    private int rating;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Malfunction> malfunctions;
    
    @Column(name = "finished")
    private boolean finished = false;

    public Rental(){
    	this.finished = false;
    }

    public Rental(Vehicle vehicle, User owner, Customer customer, long dateFrom, long dateTo) {
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

    public long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(long dateFrom) {
        this.dateFrom = dateFrom;
    }

    public long getDateTo() {
        return dateTo;
    }

    public void setDateTo(long dateTo) {
        this.dateTo = dateTo;
    }

    public Double getTraveledKm() {
        return traveledKm;
    }

    public void setTraveledKm(Double traveledKm) {
        this.traveledKm = traveledKm;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Malfunction> getMalfunctions() {
        return malfunctions;
    }

    public void setMalfunctions(List<Malfunction> malfunctions) {
        this.malfunctions = malfunctions;
    }

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
