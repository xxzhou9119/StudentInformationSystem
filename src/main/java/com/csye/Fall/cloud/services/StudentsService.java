package com.csye.Fall.cloud.services;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye.Fall.cloud.datamodel.Course;
import com.csye.Fall.cloud.datamodel.InMemoryDatabase;
import com.csye.Fall.cloud.datamodel.Student;

public class StudentsService {
static HashMap<Long, Student> student_Map = InMemoryDatabase.getStudentDB();
	
	// Getting a list of all Student 
	// GET "..webapi/students"
	public List<Student> getAllStudents() {	
		//Getting the list
		ArrayList<Student> list = new ArrayList<>();
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("Cloud"));
		courses.add(new Course("Java"));
		Student s = new Student("A", 1 , courses, "is");
		student_Map.put((long) 1,s);
		
		for (Student student : student_Map.values()) {
			list.add(student);
		}
		return list ;
	}
	
	// Adding a student
	public void addStudent(String name, String programName) {
		// Next Id 
		long nextAvailableId = student_Map.size() + 1;
		 List<Course> courses = new ArrayList<>();
		//Create a Student Object
		Student student = new Student(name, nextAvailableId , courses, programName);
		student_Map.put(nextAvailableId, student);
	}
	
	public Student addStudent(Student student) {	
		long nextAvailableId = student_Map.size() + 1;
		student.setStudentId(nextAvailableId);
		student_Map.put(nextAvailableId, student);
		return student_Map.get(nextAvailableId);
	}
	
	// Getting One Student
	public Student getStudent(Long studentId) {
		return student_Map.get(studentId);
	}
	
	// Deleting a student
	public Student deleteStudent(Long studentId) {
		Student deletedProfDetails = student_Map.get(studentId);
		student_Map.remove(studentId);
		return deletedProfDetails;
	}
	
	// Updating Student Info
	public Student updateStudentInformation(Long studentId, Student student) {	
		Student oldProfObject = student_Map.get(studentId);
		studentId = oldProfObject.getStudentId();
		student.setStudentId(studentId);
		// Publishing New Values
		student_Map.put(studentId, student) ;
		
		return student;
	}
	
	// Get students in a department 
	public List<Student> getStudentsByPrograme(String program) {	
		//Getting the list
		ArrayList<Student> list = new ArrayList<>();
		for (Student student : student_Map.values()) {
			if (student.getProgrameName().equals(program)) {
				list.add(student);
			}
		}
		return list ;
	}
}
