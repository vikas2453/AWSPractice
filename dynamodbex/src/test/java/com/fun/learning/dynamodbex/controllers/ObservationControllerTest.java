package com.fun.learning.dynamodbex.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fun.learning.dynamodbex.BaseTest;
import com.fun.learning.dynamodbex.model.Observation;

public class ObservationControllerTest extends BaseTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	//@Transactional
	public void testCreateObservation() throws Exception {
		
		List<String> tagList= new ArrayList<>();		
		
		
		mockMvc.perform(post("/observations/create")
				.content(asJsonString(new Observation("224", "11/10/2019", "12:30", "image", tagList)) )
				.contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON)
			   );

	}
	
	@Test
	public void testGetStation() throws Exception {
		
		List<String> tagList= new ArrayList<>();			
		
		mockMvc.perform(get("/observations/getObs?obsId=5")
				//.content(asJsonString(new Observation("221", "11/10/2019", "12:30", "image", tagList)) )
				//.contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON)
			   );

	}
	
	@Test
	public void testGetObservation() throws Exception {
		
		List<String> tagList= new ArrayList<>();			
		
		mockMvc.perform(get("/observations/getObs?obsId=5")
				//.content(asJsonString(new Observation("221", "11/10/2019", "12:30", "image", tagList)) )
				//.contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON)
			   );

	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
