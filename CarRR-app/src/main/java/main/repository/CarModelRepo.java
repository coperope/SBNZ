package main.repository;

import main.facts.Brand;
import main.facts.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CarModelRepo extends JpaRepository<CarModel, Long> {

    ArrayList<CarModel> getAllByBrand(Brand brand);
}
