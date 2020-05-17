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
    
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_search_history_id")
    private SearchHistory searchHistory;

	public CustomerPreferences getPreferences() {
		return preferences;
	}

	public void setPreferences(CustomerPreferences preferences) {
		this.preferences = preferences;
	}

	public RentalHistory getRentalHistory() {
		return rentalHistory;
	}

	public void setRentalHistory(RentalHistory rentalHistory) {
		this.rentalHistory = rentalHistory;
	}

	public SearchHistory getSearchHistory() {
		return searchHistory;
	}

	public void setSearchHistory(SearchHistory searchHistory) {
		this.searchHistory = searchHistory;
	}
}
