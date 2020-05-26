package main.controller;

import main.dto.UserDTO;
import main.facts.User;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.ValidationException;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> getTest() {

        return ResponseEntity.ok("UserController");
    }

    @PostMapping(path = "/login", consumes = "application/json", produces= "application/json")
    public ResponseEntity loginUser(@RequestBody UserDTO loginDTO) {

        User temp = userService.findByEmail(loginDTO.getEmail());

        if(temp != null) {
            if (temp.getPassword().equals(loginDTO.getPassword())) {
                return new ResponseEntity<>(loginDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Wrong password!", HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>("Wrong email!", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity addUser(@RequestBody UserDTO registrationDTO, UriComponentsBuilder ucBuilder) {

        User existUser = this.userService.findByEmail(registrationDTO.getEmail());
        if (existUser != null) {
            return new ResponseEntity<>("Email already exists.", HttpStatus.CONFLICT);
        }
        UserDTO created = new UserDTO();
        try{
            userService.registerUser(registrationDTO);
        }catch (ValidationException e){
            return new ResponseEntity<>("Request with same email already exists.", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<UserDTO>(created , HttpStatus.CREATED);
    }
}
