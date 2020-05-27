package main.service;

//import main.MainApp;
import main.dto.BrandDTO;
import main.dto.CarModelDTO;
import main.dto.VehicleDTO;
import main.facts.*;
import main.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ModelMapper modelMapper;

    public BrandDTO addBrand(BrandDTO brandDTO) {
        Brand brand = modelMapper.map(brandDTO,Brand.class);
        brandRepo.save(brand);
        return brandDTO;
    }

    public Tag addTag(Tag tag) {
        tagRepo.save(tag);
        //MainApp.taggingAndCategorisation.insert(tag);
        return tag;
    }

    public Fuel addFuel(Fuel fuel) {
        fuelRepo.save(fuel);
        return fuel;
    }

    public CarModelDTO addCarModel(CarModelDTO carModelDTO) {
        CarModel carModel = modelMapper.map(carModelDTO, CarModel.class);
        carModelRepo.save(carModel);
        return carModelDTO;
    }

    public Transmission addTransmission(Transmission transmission) {
        transmissionRepo.save(transmission);
        return transmission;
    }

    public Category addCategory(Category category){
       categoryRepo.save(category);
       //MainApp.taggingAndCategorisation.insert(category);
       return  category;
    }

    public List<BrandDTO> getAllBrands(){
        List<Brand> brands = brandRepo.findAll();
        return brands
                .stream()
                .map(this::convertBrandToDTO)
                .collect(Collectors.toList());
    }
    public List<Tag> getAllTags(){
        return  tagRepo.findAll();
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

    public List<CarModelDTO> getAllCarModelsByBrand(Long brandID){
        Brand brand = brandRepo.getOne(brandID);
        List<CarModel> carModels = carModelRepo.getAllByBrand(brand);
        return carModels
                .stream()
                .map(this::convertCarModelToDTO)
                .collect(Collectors.toList());
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

    private Object convertEntityToDTO(Object entity, Class objectClass){
        return modelMapper.map(entity, objectClass);
    }

    private BrandDTO convertBrandToDTO(Brand brand){
        return modelMapper.map(brand, BrandDTO.class);
    }

    private CarModelDTO convertCarModelToDTO(CarModel carModel){
        return modelMapper.map(carModel, CarModelDTO.class);
    }
}
