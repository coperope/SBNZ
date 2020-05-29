package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.facts.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
