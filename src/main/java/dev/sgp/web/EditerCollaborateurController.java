package dev.sgp.web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.sgp.entite.Collaborateur;
import dev.sgp.entite.Departement;
import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;

@WebServlet("/collaborateurs/editer")
public class EditerCollaborateurController extends HttpServlet {
	
	@Inject
	private CollaborateurService collabService;
	@Inject
	private DepartementService departementService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Optional<String> id = Optional.ofNullable(req.getParameter("matricule"));
		if (id.isPresent() && collabService.getCollaborateurByMatricule(id.get())!=null) {
			req.setAttribute("collab", collabService.getCollaborateurByMatricule(id.get()));
			req.setAttribute("listeDepartements", departementService.listerDepartements());
			req.getRequestDispatcher("/WEB-INF/views/collab/editerCollaborateur.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("lister");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String matricule = req.getParameter("matricule");
		String adresse = req.getParameter("adresse");
		String tel = req.getParameter("telephone");
		boolean inactif = req.getParameter("desactiver") == null;
		String intitulePoste = req.getParameter("intitulePoste");
		String banque = req.getParameter("banque");
		String iban = req.getParameter("iban");
		String bic = req.getParameter("bic");
		Departement departement = departementService.getDepartementById(Integer.valueOf(req.getParameter("departement")));

		collabService.updateCollaborateur(
				new Collaborateur(matricule, "", "", LocalDate.now(), adresse, "", "", "", ZonedDateTime.now(), inactif, intitulePoste, departement, banque, bic, iban, tel));
		resp.sendRedirect("lister");
	}

}
