package main.controller;

import main.dto.BrandDTO;
import main.dto.CarModelDTO;
import main.facts.*;
import main.service.VehiclePartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "")
public class VehiclePartsController {

    @Autowired
    private VehiclePartsService vehiclePartsService;

    @GetMapping(path="/fuel" ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Fuel>> getFuelTypes(){
        return new ResponseEntity<>(vehiclePartsService.getAllFuelTypes(), HttpStatus.OK);
    }

    @GetMapping(path="/brand" ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BrandDTO>> getBrandTypes(){
        List<BrandDTO> brands = vehiclePartsService.getAllBrands();
        for (BrandDTO b : brands) {
            System.out.println(b);
        }
        return new ResponseEntity<>(vehiclePartsService.getAllBrands(), HttpStatus.OK);
    }

    @GetMapping(path="/brand/{id}/model" ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarModelDTO>> getBrandModels(@PathVariable Long id){
        return new ResponseEntity<>(vehiclePartsService.getAllCarModelsByBrand(id), HttpStatus.OK);
    }

    @GetMapping(path="/category" ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCategoryTypes(){
        return new ResponseEntity<>(vehiclePartsService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping(path="/tag" ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tag>> getTagTypes(){
        return new ResponseEntity<>(vehiclePartsService.getAllTags(), HttpStatus.OK);
    }

    @GetMapping(path="/transmission" ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Transmission>> getTransmissionTypes(){
        return new ResponseEntity<>(vehiclePartsService.getAllTransmissions(), HttpStatus.OK);
    }
}
