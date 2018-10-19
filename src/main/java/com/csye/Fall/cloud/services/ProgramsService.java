package com.csye.Fall.cloud.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye.Fall.cloud.datamodel.Course;
import com.csye.Fall.cloud.datamodel.InMemoryDatabase;
import com.csye.Fall.cloud.datamodel.Program;


public class ProgramsService {
static HashMap<Long, Program> program_Map = InMemoryDatabase.getProgramDB();
	
	// Getting a list of all Program 
	// GET "..webapi/programs"
	public List<Program> getAllPrograms() {	
		//Getting the list
		ArrayList<Program> list = new ArrayList<>();
		Program p = new Program("is", 1, new ArrayList<Course>());
		program_Map.put((long) 1, p);
		
		for (Program program : program_Map.values()) {
			list.add(program);
		}
		return list ;
	}
	
	// Adding a program
	public void addProgram(String name, List<Course> courseList) {
		// Next Id 
		long nextAvailableId = program_Map.size() + 1;
		//Create a Program Object
		Program program = new Program(name, nextAvailableId , courseList);
		program_Map.put(nextAvailableId, program);
	}
	
	public Program addProgram(Program program) {	
		long nextAvailableId = program_Map.size() + 1;
		program.setProgramId(nextAvailableId);
		program_Map.put(nextAvailableId, program);
		return program_Map.get(nextAvailableId);
	}
	
	// Getting One Program
	public Program getProgram(Long programId) {
		return program_Map.get(programId);
	}
	
	// Deleting a program
	public Program deleteProgram(Long programId) {
		Program deletedProfDetails = program_Map.get(programId);
		program_Map.remove(programId);
		return deletedProfDetails;
	}
	
	// Updating Program Info
	public Program updateProgramInformation(Long programId, Program program) {	
		Program oldProfObject = program_Map.get(programId);
		programId = oldProfObject.getProgramId();
		program.setProgramId(programId);
		// Publishing New Values
		program_Map.put(programId, program) ;
		
		return program;
	}
	
//	// Get programs in a department 
//	public List<Program> getProgramsByProgramName(String programName) {	
//		//Getting the list
//		ArrayList<Program> list = new ArrayList<>();
//		for (Program program : program_Map.values()) {
//			if (program.getProgramName().equals(programName)) {
//				list.add(program);
//			}
//		}
//		return list ;
//	}
}
