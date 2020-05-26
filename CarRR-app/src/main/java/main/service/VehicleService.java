package main.service;

import main.dto.VehicleDTO;
import main.facts.Brand;
import main.facts.Vehicle;
import main.repository.CarModelRepo;
import main.repository.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;

    public Vehicle addVehicle(Vehicle vehicle) {
        vehicleRepo.save(vehicle);
        return vehicle;
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepo.findAll();
    }

}
