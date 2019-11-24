package com.fun.learning.dynamodbex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fun.learning.dynamodbex.model.Observation;
import com.fun.learning.dynamodbex.model.Station;
import com.fun.learning.dynamodbex.service.ObservationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/observations")
@Slf4j
public class ObservationController {
	
	@Autowired
	private ObservationService observationService;
	
	@PostMapping("/create")
	public Observation createObservation(@RequestBody Observation obs) {
		
		observationService.createObservation(obs);
		
		log.info("creating observation");
		
		return obs;
	}
	
	@GetMapping("/getObs")
	public Observation getObservation(@RequestParam int obsId) {
		log.info("creating observation");
		
		return observationService.getObservation(obsId);
	}
	
	@GetMapping("/getStation")
	public Station getStation(@RequestParam int stationId) {
		log.info("creating observation");
		
		return observationService.getStation(stationId);
	}

}
