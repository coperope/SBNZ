package main.events;

import main.facts.Vehicle;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class NewVehicleEvent {

	public long timestamp;
	public Vehicle vehicle;
	
	public NewVehicleEvent() {
		
	}
	
	public NewVehicleEvent(Vehicle vehicle) {
		this.vehicle = vehicle;
		//this.timestamp = timestamp;
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
	
	
}
