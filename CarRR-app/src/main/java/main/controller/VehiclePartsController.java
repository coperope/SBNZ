package main.controller;

import main.service.VehiclePartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "")
public class VehiclePartsController {

    @Autowired
    private VehiclePartsService vehiclePartsService;

    @GetMapping(path="/fuel" ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getFuelTypes(){
        return new ResponseEntity<>("TempResponse", HttpStatus.OK);
    }

    @GetMapping(path="/brand" ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBrandTypes(){
        return new ResponseEntity<>("TempResponse", HttpStatus.OK);

    }

    @GetMapping(path="/brand/{id}/model" ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getBrandModels(@PathVariable Long id){
        return new ResponseEntity<>("TempResponse", HttpStatus.OK);

    }

    @GetMapping(path="/category" ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCategoryTypes(){
        return new ResponseEntity<>("TempResponse", HttpStatus.OK);

    }

    @GetMapping(path="/transmission" ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTransmissionTypes(){
        return new ResponseEntity<>("TempResponse", HttpStatus.OK);

    }


}
