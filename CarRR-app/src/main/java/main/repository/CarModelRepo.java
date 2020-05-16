package main.repository;

import main.facts.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepo extends JpaRepository<CarModel, Long> {
}
