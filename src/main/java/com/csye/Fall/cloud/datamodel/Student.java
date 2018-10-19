package com.csye.Fall.cloud.datamodel;

import java.awt.Image;
import java.util.List;

public class Student {
	private String firstName;
	private long studentId;
	private List<Course> courses;
//	private Image image;
	private String programName;
	
	public Student() {
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}

//	public Image getImage() {
//		return image;
//	}
//
//	public void setImage(Image image) {
//		this.image = image;
//	}

	public String getProgrameName() {
		return programName;
	}

	public void setProgrameName(String programName) {
		this.programName = programName;
	}

	public Student(String firstName, long studentId, List<Course> courses, String programName) {
		super();
		this.firstName = firstName;
		this.studentId = studentId;
		this.courses = courses;
		this.programName = programName;
	}
	

}
