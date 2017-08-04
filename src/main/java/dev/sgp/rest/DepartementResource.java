package dev.sgp.rest;

import java.util.List;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dev.sgp.entite.Departement;
import dev.sgp.service.DepartementService;

@Path("/departements")
public class DepartementResource {
	
	@Inject DepartementService dps;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Departement> getDepartements() {
		return dps.listerDepartements();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getDepartement(@PathParam("id") Integer id) {
		try{
			return dps.getDepartementById(id);
		}catch(EJBException e){
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	
}
