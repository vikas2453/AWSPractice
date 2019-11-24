package com.fun.learning.dynamodbex.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Observation {

	private String stationid;
	private String date;
	private String time;
	private String image;
    private List<String> tags;
}
