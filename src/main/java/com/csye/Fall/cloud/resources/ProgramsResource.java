package com.csye.Fall.cloud.resources;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye.Fall.cloud.datamodel.Course;
import com.csye.Fall.cloud.datamodel.Program;
import com.csye.Fall.cloud.services.ProgramsService;

//.. /webapi/myresource
@Path("/programs")
public class ProgramsResource {
	ProgramsService programService = new ProgramsService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getprograms() {
		return programService.getAllPrograms();
	}
	
	// ... webapi/program/1 
	@GET
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("programId") long programId) {
		return programService.getProgram(programId);
	}
	
	@DELETE
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program deleteProgram(@PathParam("programId") long programId) {
		return programService.deleteProgram(programId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program addProgram(Program program) {
			return programService.addProgram(program);
	}
	
	@PUT
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProgram(@PathParam("programId") long programId, 
			Program program) {
		return programService.updateProgramInformation(programId, program);
	}
	
	// Adding a program
	public void addProgram(String name) {
		List<Course> courseList = new ArrayList<>();
		programService.addProgram(name, courseList);
	}
}
