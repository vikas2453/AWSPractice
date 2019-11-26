package com.fun.learning.dynamodbex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fun.learning.dynamodbex.model.Observation;
import com.fun.learning.dynamodbex.model.Station;
import com.fun.learning.dynamodbex.repo.ObservationRepo;

@Service
public class ObservationService {
	
	@Autowired
	private ObservationRepo observationRepo;

	public void createObservation(Observation obs) {
		observationRepo.createObservation(obs);
		
		
	}

	public Observation getObservation(int obsId) {
		return observationRepo.findObservationById(obsId);
		
	}
	
	public Station getStation(int obsId) {
		return observationRepo.findStationById(obsId);
		
	}

}
