package main.repository;

import main.facts.Brand;
import main.facts.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarModelRepo extends JpaRepository<CarModel, Long> {
    List<CarModel> getAllByBrand(Brand brand);
}
