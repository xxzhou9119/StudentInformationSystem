package com.csye.Fall.cloud.datamodel;

import java.util.List;



public class Course {
	private List<Lecture> lectures;
	private List<Student> students;
	private Professor professor;
	private Student studentTa;
	private long courseId;
	private List<String> roster;
	private String courseName;
	
	public Course() {
		
	}
	
   public Course(String courseName) {
		this.courseName = courseName;
	}
	
}
