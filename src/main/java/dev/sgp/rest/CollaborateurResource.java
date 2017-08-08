package dev.sgp.rest;

import java.util.List;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dev.sgp.entite.Collaborateur;
import dev.sgp.entite.Departement;
import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;

@Path("/collaborateurs")
public class CollaborateurResource {
	
	@Inject CollaborateurService cs;
	@Inject DepartementService dps;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Collaborateur> getCollaborateurs(@QueryParam("departement") Integer idDepartement) {
		if(idDepartement==null){
			return cs.listerCollaborateurs(null, 0, true);
		}else{
			return cs.getCollaborateurByIdDepartement(idDepartement);
		}
	}
	
	@GET
	@Path("/{matricule}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getCollaborateur(@PathParam("matricule") String matricule) {
		Collaborateur c = cs.getCollaborateurByMatricule(matricule);
		if(c == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}else{
			return c;
		}
	}
	
	@PUT
	@Path("/{matricule}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object modCollaborateur(@PathParam("matricule") String matricule,
			@QueryParam("adresse") String adresse, @QueryParam("tel") String tel,
			@QueryParam("fonction") String fonction,
			@QueryParam("departement") Integer idDepartement) {
		Collaborateur c = cs.getCollaborateurByMatricule(matricule);
		if(c == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}else{
			if(adresse==null && tel==null && fonction==null && idDepartement==null){
				return Response.status(Response.Status.NOT_MODIFIED).build();
			}else{
				if(adresse!=null){
					c.setAdresse(adresse);
					cs.updateCollaborateur(c);
				}
				if(tel!=null){
					c.setTel(tel);
					cs.updateCollaborateur(c);
				}
				if(fonction!=null){
					c.setIntitulePoste(fonction);
					cs.updateCollaborateur(c);
				}
				if(idDepartement!=null){
					try{
						Departement d = dps.getDepartementById(idDepartement);
						c.setDepartement(d);
						cs.updateCollaborateur(c);
					}catch(EJBException e){
						return Response.status(Status.NOT_FOUND)
								.entity("wrong departement "+idDepartement.toString()).build();
					}
				}
				return Response.status(Response.Status.OK).build();
			}
		}
	}
	
	@GET
	@Path("/{matricule}/banque")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getBanqueCollaborateur(@PathParam("matricule") String matricule) {
		Collaborateur c = cs.getCollaborateurByMatricule(matricule);
		if(c == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}else{
			return Json.createObjectBuilder()
					.add("banque", c.getBanque())
					.add("bic", c.getBic())
					.add("iban", c.getIban())
				  .build();
		}
	}
	
	@PUT
	@Path("/{matricule}/banque")
	@Produces(MediaType.APPLICATION_JSON)
	public Object modBanqueCollaborateur(@PathParam("matricule") String matricule, @QueryParam("banque") String banque, @QueryParam("bic") String bic, @QueryParam("iban") String iban) {
		Collaborateur c = cs.getCollaborateurByMatricule(matricule);
		if(c == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}else{
			if(banque==null && bic==null && iban==null){
				return Response.status(Response.Status.NOT_MODIFIED).build();
			}else{
				if(banque!=null){
					c.setBanque(banque);
					cs.updateCollaborateur(c);
				}
				if(bic!=null){
					c.setBic(bic);
					cs.updateCollaborateur(c);
				}
				if(iban!=null){
					c.setIban(iban);
					cs.updateCollaborateur(c);
				}
				return Response.status(Response.Status.OK).build();
			}
		}
	}
	
}
