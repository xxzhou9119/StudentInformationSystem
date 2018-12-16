package com.csye.Fall.cloud.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csye.Fall.cloud.datamodel.Course;
import com.csye.Fall.cloud.datamodel.Registrar;
import com.csye.Fall.cloud.services.RegistrarService;

@Path("/registerOffering")
public class RegistrarResource {

	RegistrarService registrarSer = new RegistrarService();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Registrar addProfessor(Registrar registrar) {
		return registrarSer.addRegistra(registrar);
	}
}
