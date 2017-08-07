package dev.sgp.web;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;

@WebServlet("/collaborateurs/lister")
public class ListerCollaborateursController extends HttpServlet {

	@Inject private CollaborateurService collabService;
	@Inject private DepartementService departementService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String rechNom = null;
		Integer rechDepartement = 0;
		Boolean rechInactifs = false;
		
		Optional<Object> filtreNom = Optional.ofNullable(req.getParameter("nom"));
		if(filtreNom.isPresent() && !filtreNom.get().equals("")){
			rechNom = (String) filtreNom.get();
			req.setAttribute("nom", rechNom);
		}else{
			req.setAttribute("nom", "");
		}
		
		Optional<Object> filtreDepartement = Optional.ofNullable(req.getParameter("departement"));
		if(filtreDepartement.isPresent() && !filtreDepartement.get().equals("")){
			rechDepartement = Integer.valueOf((String) filtreDepartement.get());
		}
		req.setAttribute("departement", rechDepartement);
		
		Optional<Object> filtreInactifs = Optional.ofNullable(req.getParameter("inactifs"));
		if(filtreInactifs.isPresent() && !filtreInactifs.get().equals("")){
			rechInactifs = true;
		}
		req.setAttribute("inactifs", rechInactifs);
		
		req.setAttribute("listeCollabs", collabService.listerCollaborateurs(rechNom, rechDepartement, rechInactifs));
		req.setAttribute("listeDepartements", departementService.listerDepartements());
		req.getRequestDispatcher("/WEB-INF/views/collab/listerCollaborateurs.jsp").forward(req, resp);
		
	}

}
