package com.csye.Fall.cloud.resources;

import java.awt.Image;
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

import com.csye.Fall.cloud.datamodel.Student;
import com.csye.Fall.cloud.services.StudentsService;

//.. /webapi/myresource
@Path("/students")
public class StudentsResource {
	StudentsService studentService = new StudentsService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getstudents() {
		return studentService.getAllStudents();
	}
	
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Student> getStudentsByPrograme(@QueryParam("program") String program) {
//		
//		if (program == null) {
//			return studentService.getAllStudents();
//		}
//		return studentService.getStudentsByPrograme(program);
//		
//	}
	
	// ... webapi/student/1 
	@GET
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("studentId") long studentId) {
		return studentService.getStudent(studentId);
	}
	
	@DELETE
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("studentId") long studentId) {
		return studentService.deleteStudent(studentId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student student) {
			return studentService.addStudent(student);
	}
	
	@PUT
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("studentId") long studentId, 
			Student student) {
		return studentService.updateStudentInformation(studentId, student);
	}
	
	// Adding a student
	public void addStudent(String name, String programName) {
		studentService.addStudent(name, programName);
	}
}
