package com.fun.learning.dynamodbex.repo;

import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fun.learning.dynamodbex.BaseTest;
import com.fun.learning.dynamodbex.model.Observation;

public class ObservationRepoTest extends BaseTest {
	
	@Autowired
	private ObservationRepo observationRepo;
	
	@Test
	public void testFindObservationByIdAndStationId() {
		Observation obs= observationRepo.findObservationByIdAndStationId(5, 221);
		assertThat(obs.getDate(), equalTo("11/10/2019"));
		assertThat(obs.getImage(), equalTo("image"));
	}
	
	/*@Test
	public void testFindObservationById() {
		Observation obs= observationRepo.findObservationById(2);
		assertThat(obs.getDate(), equalTo("11/10/2019"));
		assertThat(obs.getImage(), equalTo("image"));
	}
	*/
	

}
