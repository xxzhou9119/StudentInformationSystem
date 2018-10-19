package com.csye.Fall.cloud.datamodel;

import java.util.List;

public class Program {
	private String programName;
	private List<Course> courseList;
	private long programId;
	
	public long getProgramId() {
		return programId;
	}

	public void setProgramId(long programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public Program() {
		
	}
	
	public Program(String programName, long programId, List<Course> courseList) {
		super();
		this.programName = programName;
		this.courseList = courseList;
		this.programId = programId;
	}
}
