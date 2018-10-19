package com.csye.Fall.cloud.datamodel;

import java.util.HashMap;

//import test.Program;
//import test.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class InMemoryDatabase {

	private static HashMap<Long, Professor> professorDB = new HashMap<> ();
	private static HashMap<Long, Student> studentDB = new HashMap<> ();
	private static HashMap<Long, Program> programDB = new HashMap<> ();

	public static HashMap<Long, Professor> getProfessorDB() {
		return professorDB;
	}
	public static HashMap<Long, Student> getStudentDB() {
		return studentDB;
	}
	public static HashMap<Long, Program> getProgramDB() {
		return programDB;
	}
}
