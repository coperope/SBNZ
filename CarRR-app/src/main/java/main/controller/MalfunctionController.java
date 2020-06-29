package main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.dto.RentalDTO;
import main.facts.Malfunction;
import main.service.MalfunctionService;
import main.service.RentalService;

@RestController
@RequestMapping(value = "malfunction")
public class MalfunctionController {
	
	@Autowired
    MalfunctionService malfunctionService;

	@GetMapping(path = "", produces = "application/json")
    public ResponseEntity getAllMalfunctions() {
    	List<Malfunction> malfunctions = malfunctionService.getAllMalfunctions();

        return new ResponseEntity<>(malfunctions, HttpStatus.OK);
    }
}
