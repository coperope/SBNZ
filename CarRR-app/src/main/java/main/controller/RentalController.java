package main.controller;

import main.dto.RentalDTO;
import main.dto.VehicleDTO;
import main.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "rental")
public class RentalController {
    @Autowired
    RentalService rentalService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addRental(@RequestBody RentalDTO rentalDTO){
        rentalService.addRental(rentalDTO);
        return ResponseEntity.ok("more rental");
    }
}
