package main.repository;

import main.facts.ExtraFeatures;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtraFeaturesRepo extends JpaRepository<ExtraFeatures, Long> {
}
