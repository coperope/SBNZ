package main.repository;

import main.dto.SearchDTO;
import main.facts.Brand;
import main.facts.Category;
import main.facts.Tag;
import main.facts.User;
import main.facts.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    @Query(value = "select distinct vehicle from Vehicle as vehicle inner join vehicle.tags as tag left join vehicle.categories as category where" +
    		"(((:brand) is null ) or (vehicle.brand.id) in (:brand)) " +
            "and (((:models) is null ) or (vehicle.model.id) in (:models)) " +
            "and (((:fuels) is null) or (vehicle.fuel.id) in (:fuels))" +
            "and (((:transmissions) is null) or (vehicle.transmission.id) in (:transmissions))" +
            "and (((:doorNo) is null) or (vehicle.doorNo) in (:doorNo))" +
            "and (((:seatsNo) is null) or (vehicle.seatsNo) in (:seatsNo))" +
            "and (((:categories) is null) or (category.id) in (:categories))" +
            "and (((:tags) is null) or (tag.id) in (:tags))" +
            "and (((:fuelConsumptions) is null) or (vehicle.fuelConsumption) in (:fuelConsumptions))")
    public List<Vehicle> getBySearchParams(@Param("brand") List<Long> brandID,
                                           @Param("models") List<Long> modelID,
                                           @Param("fuels") List<Long> fuelID,
                                           @Param("transmissions") List<Long> transmissionID,
                                           @Param("doorNo") List<Integer> doorNo,
                                           @Param("seatsNo") List<Integer> seatsNo,
                                           @Param("categories") List<Long> categoryID,
                                           @Param("tags") List<Long> tagID,
                                           @Param("fuelConsumptions") List<Double> fuelConsumption);

	public List<Vehicle> findByOwner(User owner);
}
//+
//    "and (vehicle.categories) in (:category)"
//, @Param("category") List<Category> category

//"and (((:tags) is null ) or (vehicle.tags.id) in (:models))"
//"and (((:categories) is null ) or (vehicle.categories.id) in (:categories))"