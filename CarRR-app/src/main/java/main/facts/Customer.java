package main.facts;

import javax.persistence.*;

@Entity
public class Customer extends User {

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_preferences_id")
    private CustomerPreferences preferences;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_rental_history_id")
    private RentalHistory rentalHistory;
}
