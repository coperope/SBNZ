package main.controller;

import main.dto.RentalDTO;
import main.dto.VehicleDTO;
import main.facts.Malfunction;
import main.service.RentalService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @GetMapping(path = "/active/{id}", produces = "application/json")
    public ResponseEntity getOwnersActiveRentals(@PathVariable("id") Long ownerID) {
    	List<RentalDTO> rentals = rentalService.getOwnersActiveRentals(ownerID);

        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }
    
    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity finishRental(@RequestBody List<Malfunction> malfunctions, @PathVariable("id") Long rentalID){
        rentalService.finishRental(rentalID, malfunctions);
        return ResponseEntity.ok("rental updated");
    }
}
