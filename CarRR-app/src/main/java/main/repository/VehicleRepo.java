package main.repository;

import main.dto.SearchDTO;
import main.facts.Brand;
import main.facts.Category;
import main.facts.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    @Query(value = "select vehicle from Vehicle as vehicle  where " +
            " (((:brand) is null ) or (vehicle.brand.id) in (:brand)) " +
            "and (((:models) is null ) or (vehicle.model.id) in (:models)) " +
            "and (((:fuels) is null) or (vehicle.fuel.id) in (:fuels))" +
            "and (((:transmissions) is null) or (vehicle.transmission.id) in (:transmissions))" +
            "and (((:doorNo) is null) or (vehicle.doorNo) in (:doorNo))" +
            "and (((:seatsNo) is null) or(vehicle.seatsNo) in (:seatsNo))")
    public List<Vehicle> getBySearchParams(@Param("brand") List<Long> brandID,
                                           @Param("models") List<Long> modelID,
                                           @Param("fuels") List<Long> fuelID,
                                           @Param("transmissions") List<Long> transmissionID,
                                           @Param("doorNo") List<Integer> doorNo,
                                           @Param("seatsNo") List<Integer> seatsNo);
}
//+
//    "and (vehicle.categories) in (:category)"
//, @Param("category") List<Category> category
