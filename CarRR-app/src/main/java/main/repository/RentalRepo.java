package main.repository;

import main.facts.Rental;
import main.facts.User;
import main.facts.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepo extends JpaRepository<Rental, Long> {
    public List<Rental> findAllByVehicle(Vehicle vehicle);
    
    public List<Rental> findAllByOwnerAndFinished(User owner, boolean finished);
}
