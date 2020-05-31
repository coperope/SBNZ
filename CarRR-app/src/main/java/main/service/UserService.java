package main.service;

import main.dto.CustomerPreferencesDTO;
import main.dto.UserDTO;
import main.facts.Customer;
import main.facts.CustomerPreferences;
import main.facts.User;
import main.repository.CustomerPreferencesRepo;
import main.repository.CustomerRepo;
import main.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CustomerPreferencesRepo customerPreferencesRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Customer findCustomerById(Long id)  {
        Customer customer = customerRepo.findById(id).orElse(null);

        return customer;
    }

    public User findByEmail(String email)  {
        User u = userRepo.findByEmail(email);
        if (u == null){
            u = customerRepo.findByEmail(email);
        }
        return u;
    }

    public UserDTO registerUser(UserDTO userDTO) {
        User user = new User();
        if(userDTO.isCustomer()){
            user = new Customer();
        }
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setCustomer(userDTO.isCustomer());
        if(user.isCustomer()){
            customerRepo.save((Customer)user);
        }else{
            userRepo.save(user);
        }
        return userDTO;
    }

    public CustomerPreferencesDTO addCustomerPreferences(CustomerPreferencesDTO customerPreferencesDTO, Long userID){
        Customer customer = customerRepo.findById(userID).orElse(null);
        if (customer == null){
            throw new ValidationException("User with given ID does not exist");
        }
        customer.setPreferences(customerPreferencesDTOtoEntity(customerPreferencesDTO));

        customerPreferencesRepo.save(customer.getPreferences());
        customerRepo.save(customer);
        return customerPreferencesDTO;
    }

    public CustomerPreferences customerPreferencesDTOtoEntity(CustomerPreferencesDTO customerPreferencesDTO){
        return modelMapper.map(customerPreferencesDTO, CustomerPreferences.class);
    }
}
