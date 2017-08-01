package dev.sgp.web;

import java.io.IOException;
import java.util.Optional;

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
		Optional<String> matricule = Optional.ofNullable(req.getParameter("matricule"));
		Optional<String> titre = Optional.ofNullable(req.getParameter("titre"));
		Optional<String> nom = Optional.ofNullable(req.getParameter("nom"));
		Optional<String> prenom = Optional.ofNullable(req.getParameter("prenom"));
		
		resp.setContentType("text/html");
		String msg = null;
		Integer code = null;
		
		if(!matricule.isPresent() || !titre.isPresent() || !nom.isPresent() || !prenom.isPresent()){
			msg = "Les paramètres suivants sont incorrects : ";
			boolean first = true;
			
			if(!matricule.isPresent()){
				first = false;
				msg += "le matricule";
			}
			
			if(!titre.isPresent()){
				if(first){ first = false; }else{ msg += ", "; }
				msg += "le titre";
			}
			
			if(!nom.isPresent()){
				if(first){ first = false; }else{ msg += ", "; }
				msg += "le nom";
			}
			
			if(!prenom.isPresent()){
				if(!first){ msg += ", "; }
				msg += "le prénom";
			}
			msg += ".";
			code = 400;
		}else{
			msg = "Création d'un collaborateur avec les informations suivantes :<br>";
			msg += "Matricule="+matricule.get()+",titre="+titre.get()+",nom="+nom.get()+",prenom="+prenom.get();
			code = 201;
		}
		
		resp.setStatus(code);
		resp.getWriter().write(msg);
	}
	
}
