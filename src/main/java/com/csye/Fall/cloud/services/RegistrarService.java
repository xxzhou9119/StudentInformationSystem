package com.csye.Fall.cloud.services;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.csye.Fall.cloud.datamodel.DynamoDbConnector;
import com.csye.Fall.cloud.datamodel.Registrar;

public class RegistrarService {

	static DynamoDbConnector dynamoDb;
    DynamoDBMapper mapper; 
    
    public RegistrarService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
    
    public Registrar addRegistra(Registrar registrar) {
    	mapper.save(registrar);
    	return registrar; 	
    }
    
    
}
