package main.repository;

import main.facts.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelRepo extends JpaRepository<Fuel, Long> {
}
