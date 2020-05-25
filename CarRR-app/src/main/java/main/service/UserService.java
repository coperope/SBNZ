package main.service;

import main.dto.UserDTO;
import main.facts.User;
import main.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User findByEmail(String email)  {
        User u = userRepo.findByEmail(email);
        return u;
    }

    public UserDTO registerUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        userRepo.save(user);
        return userDTO;
    }
}
