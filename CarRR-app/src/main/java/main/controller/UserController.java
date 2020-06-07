package main.controller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.dto.CustomerPreferencesDTO;
import main.dto.UserDTO;
import main.dto.VehicleDTO;
import main.facts.Customer;
import main.facts.User;
import main.facts.Vehicle;
import main.service.UserService;
import main.service.VehicleService;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private VehicleService vehicleService;
    
    @GetMapping
    public ResponseEntity<String> getTest() {

        return ResponseEntity.ok("UserController");
    }

    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity loginUser(@RequestBody UserDTO loginDTO) {

        User temp = userService.findByEmail(loginDTO.getEmail());

        if (temp != null) {
            if (temp.getPassword().equals(loginDTO.getPassword())) {
                temp = this.userService.findByEmail(loginDTO.getEmail());
                loginDTO.setId(temp.getId());
                loginDTO.setName(temp.getName());
                loginDTO.setSurname(temp.getSurname());
                loginDTO.setPhoneNumber(temp.getPhoneNumber());
                loginDTO.setCustomer(temp.isCustomer());

                loginDTO.setPassword("**********");
                return new ResponseEntity<>(loginDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Wrong password!", HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>("Wrong email!", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity addUser(@RequestBody UserDTO registrationDTO) {

        User existUser = this.userService.findByEmail(registrationDTO.getEmail());
        if (existUser != null) {
            return new ResponseEntity<>("Email already exists.", HttpStatus.CONFLICT);
        }
        UserDTO created = new UserDTO();
        try {
            created = userService.registerUser(registrationDTO);
        } catch (ValidationException e) {
            return new ResponseEntity<>("Request with same email already exists.", HttpStatus.CONFLICT);
        }
        existUser = this.userService.findByEmail(registrationDTO.getEmail());
        created.setId(existUser.getId());
        created.setPassword("**********");
        return new ResponseEntity<UserDTO>(created, HttpStatus.CREATED);
    }

    @PostMapping(path = "/user/{id}/preferences", consumes = "application/json", produces = "application/json")
    public ResponseEntity addUserPreferences(@RequestBody CustomerPreferencesDTO customerPreferencesDTO, @PathVariable("id") Long userID) {
        try {
            CustomerPreferencesDTO retVal = userService.addCustomerPreferences(customerPreferencesDTO, userID);
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(path = "/user/{id}/preferences", produces = "application/json")
    public ResponseEntity getUserPreferences(@PathVariable("id") Long userID) {
        Customer customer = userService.findCustomerById(userID);

        return new ResponseEntity<>(customer.getPreferences(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}/recommendations", produces = "application/json")
    public ResponseEntity getUserRecommendations(@PathVariable("id") Long userID) {
    	LinkedHashMap<Vehicle, Integer> recommendations = vehicleService.getUserRecommendations(userID);

        return new ResponseEntity<>(recommendations, HttpStatus.OK);
    }
}
