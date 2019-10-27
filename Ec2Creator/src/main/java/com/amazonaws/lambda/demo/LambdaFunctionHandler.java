package com.amazonaws.lambda.demo;

import java.util.Arrays;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.IpRange;
import com.amazonaws.services.ec2.model.KeyPair;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

	// private static Logger logger=
	// Logger.getLogger(LambdaFunctionHandler.class.getName());

	private static final AWSCredentials credentials;

	static {
		// put your accesskey and secretkey here
		credentials = new BasicAWSCredentials("AKIAI7EMEBAQ5775JBJA", "JRhWcd9YwHJxYm9xE0NiO5Kf3fWzsy4rKfGKp42J");
	}

	@Override
	public String handleRequest(Object input, Context context) {

		AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_2).build();
		
		//createSecurityGroup(ec2Client, "myFirstSGGroupfromJava", "myFirstSGGroupfromJava", "tcp", 22, 22);
		//KeyPair keypair= createKeyPair(ec2Client, "keypair-from-code");
		//createInstance(ec2Client, "keypair-from-code", "myFirstSGGroupfromJava");
		DescribeInstancesResult describeInstancesResult = ec2Client.describeInstances();

		describeInstancesResult.getReservations().forEach(r->r.getInstances().forEach(instance->	System.out.printf(
                "Found instance with id %s, " +
                "AMI %s, " +
                "type %s, " +
                "state %s " +
                "and monitoring state %s",
                instance.getInstanceId(),
                instance.getImageId(),
                instance.getInstanceType(),
                instance.getState().getName(),
                instance.getMonitoring().getState())
		));
		return null;
	}

	public KeyPair createKeyPair(AmazonEC2 ec2Client, String keyPairName) {
		CreateKeyPairRequest createKeyPairRequest = new CreateKeyPairRequest();
		createKeyPairRequest.withKeyName(keyPairName);

		CreateKeyPairResult createKeyPairResult = ec2Client.createKeyPair(createKeyPairRequest);

		KeyPair keypair = createKeyPairResult.getKeyPair();
		return keypair;
	}

	public Reservation createInstance(AmazonEC2 ec2Client, String keyPair, String securityGroup) {
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest();

		runInstancesRequest.withImageId("ami-00c03f7f7f2ec15c3").withInstanceType(InstanceType.T2Micro).withMinCount(1)
				.withMaxCount(1).withKeyName("keypair-from-code").withSecurityGroups("myfirstSGfromCode");
		RunInstancesResult result = ec2Client.runInstances(runInstancesRequest);
		return result.getReservation();

	}

	public void createSecurityGroup(AmazonEC2 ec2Client, String sgName, String description, String protocol, int inPort,
			int outPort) {

		CreateSecurityGroupRequest csgr = new CreateSecurityGroupRequest();
		csgr.withGroupName(sgName).withDescription(description);

		ec2Client.createSecurityGroup(csgr);

		IpPermission ipPermission = new IpPermission();

		IpRange ipRange = new IpRange().withCidrIp("0.0.0.0/0");
		ipPermission.withIpv4Ranges(Arrays.asList(new IpRange[] { ipRange })).withIpProtocol(protocol)
				.withFromPort(inPort).withToPort(outPort);

		AuthorizeSecurityGroupIngressRequest asgir = new AuthorizeSecurityGroupIngressRequest();
		asgir.withGroupName(sgName).withIpPermissions(ipPermission);

		ec2Client.authorizeSecurityGroupIngress(asgir);

	}

}
