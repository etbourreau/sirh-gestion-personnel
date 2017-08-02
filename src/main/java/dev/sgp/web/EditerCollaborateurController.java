package dev.sgp.web;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;
import dev.sgp.util.Constantes;

@WebServlet("/collaborateurs/editer")
public class EditerCollaborateurController extends HttpServlet {

	private final static CollaborateurService collabService = Constantes.COLLAB_SERVICE;
	private final static DepartementService departementService = Constantes.DEPARTEMENT_SERVICE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Optional<String> id = Optional.ofNullable(req.getParameter("id"));
		if(id.isPresent() && collabService.getCollaborateurByMatricule(id.get()).isPresent()){
			req.setAttribute("collab", collabService.getCollaborateurByMatricule(id.get()).get());
			req.setAttribute("listeDepartements", departementService.listerDepartements());
			req.getRequestDispatcher("/WEB-INF/views/collab/editerCollaborateur.jsp").forward(req, resp);
		}else{
			resp.setStatus(400);
			resp.getWriter().write("Merci de renseigner un id de collaborateur valide !\nEx: ...sgp/collaborateurs/editer?id=62");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}
