package main.controller;

import main.dto.VehicleDTO;
import main.facts.Vehicle;
import main.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> vehicleDTOS = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicleDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addVehicle(@RequestBody VehicleDTO vehicleDTO){
        System.out.println(vehicleDTO);
        vehicleService.addVehicle(vehicleDTO);
        return ResponseEntity.ok("more");
    }


}
