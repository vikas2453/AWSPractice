package com.fun.learning.dynamodbex.repo;

import com.fun.learning.dynamodbex.model.Observation;
import com.fun.learning.dynamodbex.model.Station;

public interface ObservationRepo {
	
	public Observation createObservation(Observation observation);

	public Station findStationById(int stationId);
	
	public Observation findObservationById(int obsId);
	
	public Observation findObservationByIdAndStationId(int obsId, int stationId);

}
