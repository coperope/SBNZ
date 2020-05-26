package main.service;

import main.facts.*;
import main.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiclePartsService {

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private TagRepo tagRepo;

    @Autowired
    private FuelRepo fuelRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private TransmissionRepo transmissionRepo;

    @Autowired
    private CarModelRepo carModelRepo;

    public Brand addBrand(Brand brand) {
        brandRepo.save(brand);
        return brand;
    }

    public Tag addTag(Tag tag) {
        tagRepo.save(tag);
        return tag;
    }

    public Fuel addFuel(Fuel fuel) {
        fuelRepo.save(fuel);
        return fuel;
    }

    public CarModel addCarModel(CarModel carModel) {
        carModelRepo.save(carModel);
        return carModel;
    }

    public Transmission addTransmission(Transmission transmission) {
        transmissionRepo.save(transmission);
        return transmission;
    }

    public Category addCategory(Category category){
       categoryRepo.save(category);
       return  category;
    }

    public List<Tag> getAllTags(){
        return tagRepo.findAll();
    }

    public List<Fuel> getAllFuelTypes(){
        return fuelRepo.findAll();
    }

    public List<Category> getAllCategories(){
        return categoryRepo.findAll();
    }

    public List<Transmission> getAllTransmissions(){
        return transmissionRepo.findAll();
    }

    public List<CarModel> getAllCarModelsByBrand(Long brandID){
        Brand brand = brandRepo.getOne(brandID);
        return carModelRepo.getAllByBrand(brand);
    }

    public void removeBrand(){

    }

    public void removeCarModel(){

    }

    public void removeFuel(){

    }

    public void removeCategory(){

    }

    public void removeTag(){

    }

    public void removeTransmission(){
        
    }
}
