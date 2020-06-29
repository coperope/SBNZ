package main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.facts.Malfunction;
import main.repository.MalfunctionRepo;

@Service
public class MalfunctionService {
	
	@Autowired
	MalfunctionRepo malfunctionRepo;

	public List<Malfunction> getAllMalfunctions() {
		List<Malfunction> malfunctions = malfunctionRepo.findAll();
		return malfunctions;
	}

}
