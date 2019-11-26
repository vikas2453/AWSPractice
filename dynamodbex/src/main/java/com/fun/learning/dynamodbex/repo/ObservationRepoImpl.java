package com.fun.learning.dynamodbex.repo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.fun.learning.dynamodbex.model.Address;
import com.fun.learning.dynamodbex.model.Observation;
import com.fun.learning.dynamodbex.model.Station;

@Repository
public class ObservationRepoImpl implements ObservationRepo {

	private AmazonDynamoDB dynamoDbClient = null;

	@PostConstruct
	public void initialize() {
		// AwsBasicCredentials awsCreds = AwsBasicCredentials.create(key, secretKey);
		dynamoDbClient = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2).build();

	}

	public Observation createObservation(Observation observation) {
		Map<String, AttributeValue> observationMap = new HashMap<String, AttributeValue>();

		observationMap.put("id", new AttributeValue().withS("5"));

		observationMap.put("stationId", new AttributeValue().withN(observation.getStationid()));

		observationMap.put("date", new AttributeValue().withS(observation.getDate()));

		observationMap.put("time", new AttributeValue().withS(observation.getTime()));

		observationMap.put("image", new AttributeValue().withS(observation.getImage()));

		PutItemRequest request = new PutItemRequest().withTableName("Observation").withItem(observationMap);

		dynamoDbClient.putItem(request);

		return observation;

	}

	@Override
	public Station findStationById(int stationId) {
		Map<String, AttributeValue> key = new HashMap<>();
		key.put("id", new AttributeValue().withN(String.valueOf(stationId)));
		// key.put("stationId", new AttributeValue().withN(observation.getStationid()))

		GetItemRequest getItemRequest = new GetItemRequest().withTableName("station").withKey(key);
		Map<String, AttributeValue> responseItem = dynamoDbClient.getItem(getItemRequest).getItem();

		Station station = new Station();
		Address address = new Address();
		address.setCity(responseItem.get("Address").getM().get("City").getS());
		address.setStreet(responseItem.get("Address").getM().get("Street").getS());
		//observation.setImage(responseItem.get("image").getS());
		//observation.setStationid(responseItem.get("stationId").getS());
		return station;
	}

	@Override
	public Observation findObservationById(int obsId) {
		Map<String, AttributeValue> key = new HashMap<>();
		key.put("id", new AttributeValue().withS(String.valueOf(obsId)));
		// key.put("stationId", new AttributeValue().withN(observation.getStationid()))

		GetItemRequest getItemRequest = new GetItemRequest().withTableName("Observation").withKey(key);
		Map<String, AttributeValue> responseItem = dynamoDbClient.getItem(getItemRequest).getItem();

		Observation observation = new Observation();
		
		
		
		return observation;
	}

	@Override
	public Observation findObservationByIdAndStationId(int obsId, int stationId) {		

		GetItemRequest getItemRequest = new GetItemRequest().withTableName("Observation").
				addKeyEntry("id", new AttributeValue().withS(String.valueOf(obsId))).
				addKeyEntry("stationId", new AttributeValue().withN(String.valueOf(stationId)));
		
		Map<String, AttributeValue> responseItem = dynamoDbClient.getItem(getItemRequest).getItem();
		Observation observation = new Observation();
		
		observation.setDate( responseItem.get("date").getS());
		observation.setImage( responseItem.get("image").getS());
		
		
		
		return observation;
	}

}
