package com.csye.Fall.cloud;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.csye.Fall.cloud.services.BoardService;
import com.csye.Fall.cloud.services.CourseService;

public class EmailAnnouncement implements RequestHandler<DynamodbEvent, String>{
	private static AmazonSNS SNS_HELPER = AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	
	@Override
	public String handleRequest(DynamodbEvent ddbEvent, Context context) {
		
		for (DynamodbStreamRecord record : ddbEvent.getRecords()){
			if(record.getEventName().equals("INSERT")) {
				System.out.println(record.getDynamodb().toString());
				String boardId = record.getDynamodb().getNewImage().get("boardId").getS();
				String topicArn = getTopicArnByBoardId(boardId);
				String message = record.getDynamodb().getNewImage().get("announcementText").getS();
				sendEmailNotification(topicArn, message, "new announcement");
			}

//	           System.out.println(record.getEventID());
//	           System.out.println(record.getEventName());
//	           System.out.println(record.getDynamodb().toString());
	           
	        }
		
	    return "Successfully processed " + ddbEvent.getRecords().size() + " records.";
			
	}
	
	public static String createTopic(String topicName) {
		return SNS_HELPER.createTopic(topicName).getTopicArn();
	}
	
	public static void subscribe(String topicArn, String email) {
		SNS_HELPER.subscribe(topicArn, "email", email);
		
	}
	
	public void sendEmailNotification(String topicArn, final String message, final String subject) {
		PublishRequest publishRequest = new PublishRequest(topicArn, message, subject);
		SNS_HELPER.publish(publishRequest);
	}
	
	private String getTopicArnByBoardId(String boardId) {
		BoardService boardSer = new BoardService();
		CourseService courseSer = new CourseService();
		String courseId = boardSer.getBoardFromDDB(boardId).get(0).getCourseId();
		return courseSer.getCourseFromDDB(courseId).get(0).getSNSTopicArn();
	}
}
