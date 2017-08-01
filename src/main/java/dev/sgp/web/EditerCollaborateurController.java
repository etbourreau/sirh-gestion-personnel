package dev.sgp.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditerCollaborateurController extends HttpServlet {
	private static final long serialVersionUID = -2796943719061702802L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Optional<String> matricule = Optional.ofNullable(req.getParameter("matricule"));
		
		resp.setContentType("text/html");
		if(matricule.isPresent()){
			resp.setStatus(200);
			resp.getWriter().write("<h1>Edition de collaborateur</h1>"+"<p>Matricule : "+matricule.get()+"</p>");
		}else{
			resp.setStatus(400);
			resp.getWriter().write("Un matricule est attendu");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> params = Stream.of("matricule", "titre", "nom", "prenom").filter(s -> req.getParameter(s)==null).collect(Collectors.toList());
		
		String msg = null;
		Integer code = null;
		
		resp.setContentType("text/html");
		if(params.isEmpty()){
			msg = "Création d'un collaborateur avec les informations suivantes :<br>";
			msg += "Matricule="+req.getParameter("matricule")+",titre="+req.getParameter("titre")+",nom="+req.getParameter("nom")+",prenom="+req.getParameter("prenom");
			code = 201;
		}else{
			msg = "Les paramètres suivants sont incorrects : "+String.join(", ", params)+".";
			code = 400;
		}
		
		resp.setStatus(code);
		resp.getWriter().write(msg);
	}
	
}
