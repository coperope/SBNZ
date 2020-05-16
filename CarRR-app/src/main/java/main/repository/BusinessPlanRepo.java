package main.repository;

import main.facts.BusinessPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessPlanRepo extends JpaRepository<BusinessPlan, Long> {
}
