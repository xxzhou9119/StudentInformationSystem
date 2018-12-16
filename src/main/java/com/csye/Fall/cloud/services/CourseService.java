package com.csye.Fall.cloud.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye.Fall.cloud.EmailAnnouncement;
import com.csye.Fall.cloud.datamodel.Board;
import com.csye.Fall.cloud.datamodel.Course;
import com.csye.Fall.cloud.datamodel.DynamoDbConnector;

public class CourseService {
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
	
	public CourseService(){
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
		
	}
	
	
	// Getting a list of all course 
	// GET "..webapi/courses"
	public List<Course> getAllCourses() {	
		//Getting the list
	   DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
		    .withIndexName("courseId-index")
		    .withConsistentRead(false);

	   List<Course> list =  mapper.scan(Course.class, scanExpression);
       return list ;
   }
	
	// Adding a course
	public Course addCourse(Course course) {
		String topicArn = EmailAnnouncement.createTopic("course"+course.getCourseId());
	    Course course2 = new Course();
	    course2.setCourseId(course.getCourseId());
	    course2.setBoardId(course.getBoardId());
	    course2.setDepartment(course.getDepartment());
	    course2.setProfessorId(course.getProfessorId());
	    course2.setStudentTaId(course.getStudentTaId());
	    course2.setStudentIds(course.getStudentIds());
	    course2.setSNSTopicArn(topicArn);
		mapper.save(course2);
		
		//add corresponding board to database
		if(!course.getBoardId().equals("")) {
			Board board = new Board(course2.getBoardId(),course2.getCourseId());
			BoardService boardSer = new BoardService();
			boardSer.addBoard(board);
		}
			
		System.out.println("Item added:");
		System.out.println(course2.toString());
		    
		return course2;
   }
	
	// Getting One course
    public Course getCourse(String courseId) {
		List<Course> list = getCourseFromDDB(courseId);
		return list.size() != 0 ? list.get(0) : null;
	}
		
	// Deleting a course
	public Course deleteCourse(String courseId) {
		List<Course> list = getCourseFromDDB(courseId);
		Course cour = null;
		if(list.size() != 0){
			cour = list.get(0);
			mapper.delete(cour);
			Course deletedCourse = mapper.load(Course.class, cour.getId());
				
			if (deletedCourse == null) {
		        System.out.println("Done - The course is deleted.");
		        System.out.println(cour.toString());
		    }
		}
			
	 return cour;
	}
		
	// Updating course Info
	public Course updateCourseInformation(String courseId, Course course) {	
			List<Course> list = getCourseFromDDB(courseId);
			Course oldCourse = null;
			if(list.size() != 0) {
				oldCourse = list.get(0);
				oldCourse.setBoardId(course.getBoardId());
				oldCourse.setDepartment(course.getDepartment());
				oldCourse.setProfessorId(course.getProfessorId());
				oldCourse.setStudentTaId(course.getStudentTaId());
				oldCourse.setStudentIds(course.getStudentIds());
                oldCourse.setSNSTopicArn(course.getSNSTopicArn());
                
				mapper.save(oldCourse);
				System.out.println("Item updated:");
			    System.out.println(oldCourse.toString());
			}
			
			return oldCourse;
		}
	
		
	// helper function 
	public List<Course> getCourseFromDDB(String courseId){
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(courseId));

		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
			.withIndexName("courseId-index")
			.withConsistentRead(false)
		    .withKeyConditionExpression("courseId = :v1")
			.withExpressionAttributeValues(eav);

	    List<Course> list =  mapper.query(Course.class, queryExpression);
	    return list;
   }
		
	
}

