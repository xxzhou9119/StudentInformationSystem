package com.csye.Fall.cloud.services;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye.Fall.cloud.datamodel.Course;
import com.csye.Fall.cloud.datamodel.DynamoDbConnector;
import com.csye.Fall.cloud.datamodel.InMemoryDatabase;
import com.csye.Fall.cloud.datamodel.Professor;
import com.csye.Fall.cloud.datamodel.Student;

public class StudentsService {
//static HashMap<Long, Student> student_Map = InMemoryDatabase.getStudentDB();
     static DynamoDbConnector dynamoDb;
     DynamoDBMapper mapper; 
     
     public StudentsService() {
 		dynamoDb = new DynamoDbConnector();
 		dynamoDb.init();
 		mapper = new DynamoDBMapper(dynamoDb.getClient());
 	}
	
	// Getting a list of all Student 
	// GET "..webapi/students"
	public List<Student> getAllStudents() {	
		//Getting the list
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
		    .withIndexName("studentId-index")
		    .withConsistentRead(false);

	    List<Student> list =  mapper.scan(Student.class, scanExpression);
	    return list ;
	}

	
	public Student addStudent(Student student) {	
		Student stu = new Student();
		stu.setFirstName(student.getFirstName());
		stu.setLastName(student.getLastName());
		stu.setDepartment(student.getDepartment());
//		prof.setJoiningDate(joiningDate);
		stu.setStudentId(student.getLastName() + "." + student.getFirstName().charAt(0));
		stu.setCourseIds(student.getCourseIds());
		mapper.save(stu);
		
		System.out.println("Item added.");
	    
	    return stu;
	    
	}
	
	// Getting One Student
	public Student getStudent(String studentId) {
		List<Student> list = getStudentFromDDB(studentId);
		return list.size() != 0 ? list.get(0) : null;
	}
	
	// Deleting a student
	public Student deleteStudent(String studentId) {
		List<Student> list = getStudentFromDDB(studentId);
		Student stu = null;
		if(list.size() != 0){
			stu = list.get(0);
			mapper.delete(stu);
			Student deletedStu = mapper.load(Student.class, stu.getId());
			
			if (deletedStu == null) {
	            System.out.println("Done - The student is deleted.");
	            System.out.println(stu.toString());
	        }
		}
		
		return stu;
	}
	
	// Updating Student Info
	public Student updateStudentInformation(String studentId, Student student) {	
		List<Student> list = getStudentFromDDB(studentId);
		Student oldStu = null;
		if(list.size() != 0) {
			oldStu = list.get(0);
			oldStu.setFirstName(student.getFirstName());
			oldStu.setLastName(student.getLastName());
			oldStu.setDepartment(student.getDepartment());
			oldStu.setCourseIds(student.getCourseIds());
            mapper.save(oldStu);
			
			System.out.println("Item updated.");
		}
		
		return oldStu;
	}
	
	// Get students in a department 
//	public List<Student> getStudentsByPrograme(String program) {	
//		
//	}
	
	// helper function 
	public List<Student> getStudentFromDDB(String stuId){
	    HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
	    eav.put(":v1",  new AttributeValue().withS(stuId));

	    DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
			.withIndexName("studentId-index")
			.withConsistentRead(false)
			.withKeyConditionExpression("studentId = :v1")
			.withExpressionAttributeValues(eav);

	    List<Student> list =  mapper.query(Student.class, queryExpression);
		return list;
	}
	
}
