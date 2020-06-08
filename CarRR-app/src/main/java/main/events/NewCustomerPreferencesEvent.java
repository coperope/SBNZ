package main.events;

import main.facts.Customer;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class NewCustomerPreferencesEvent {

    public long timestamp;
    public Customer customer;

    public NewCustomerPreferencesEvent() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
