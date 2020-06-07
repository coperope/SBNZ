package main.events;

import main.facts.Rental;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class NewRentalEvent {
    public long timestamp;
    public Rental rental;

    public NewRentalEvent() {
    }

    public NewRentalEvent(Rental rental) {
        this.rental = rental;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
