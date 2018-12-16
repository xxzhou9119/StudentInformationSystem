package com.amazonaws.lambda.demo;

import com.amazonaws.lambda.demo.datamodel.Course;
import com.amazonaws.services.dynamodbv2.datamodeling.AttributeTransformer.Parameters;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClient;
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClientBuilder;
import com.amazonaws.services.stepfunctions.model.StartExecutionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TriggerStepFunction implements RequestHandler<DynamodbEvent, String> {

    @Override
    public String handleRequest(DynamodbEvent event, Context context) {
        context.getLogger().log("Received event: " + event);
        

        for (DynamodbStreamRecord record : event.getRecords()) {
        	if(record.getEventName().equals("INSERT")) {
        		System.out.println(record.getDynamodb().toString());
        		Course course = new Course();
        		
        		String id = record.getDynamodb().getNewImage().get("id").getS();
        		String courseId = record.getDynamodb().getNewImage().get("courseId").getS();
        		String department = record.getDynamodb().getNewImage().get("department").getS();
        		
        		String boardId = "EMPTY";
        		String SNSTopicArn = "EMPTY";
        		
        		if(record.getDynamodb().getNewImage().containsKey("boardId")) {
        			 boardId = record.getDynamodb().getNewImage().get("boardId").getS();
        		}
        		if(record.getDynamodb().getNewImage().containsKey("snsTopicArn")) {
        			SNSTopicArn = record.getDynamodb().getNewImage().get("snsTopicArn").getS();
       		}
        		
        		
        		course.setId(id);
        		course.setBoardId(boardId);
        		course.setCourseId(courseId);
        		course.setDepartment(department);
        		course.setSNSTopicArn(SNSTopicArn);
        		
        		ObjectMapper jsonMapper = new ObjectMapper();
        		
        		AWSStepFunctions awsStepFunctionsClient = AWSStepFunctionsClientBuilder.defaultClient();
        		
        		StartExecutionRequest startExecutionRequest = new StartExecutionRequest()
        				.withStateMachineArn("arn:aws:states:us-east-1:599961460399:stateMachine:test");
        				
//        				
        		try {
					startExecutionRequest.setInput(jsonMapper.writeValueAsString(course));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
        		
        		awsStepFunctionsClient.startExecution(startExecutionRequest);
				
			}
            
//            System.out.println(record.getEventID());
//            System.out.println(record.getEventName());
//            System.out.println(record.getDynamodb().toString());
        }
        return "ok";
    	
    }
}