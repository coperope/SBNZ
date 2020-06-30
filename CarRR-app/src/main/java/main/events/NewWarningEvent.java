package main.events;

import org.kie.api.definition.type.Role;

import main.facts.User;
import main.facts.Vehicle;

@Role(Role.Type.EVENT)
public class NewWarningEvent {

	public long timestamp;
	public Vehicle vehicle;
	public User owner;

	public NewWarningEvent() {
	}

	public NewWarningEvent(Vehicle vehicle, User owner) {
		super();
		this.vehicle = vehicle;
		this.owner = owner;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
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
}
