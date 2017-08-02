package dev.sgp.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.sgp.entite.Collaborateur;
import dev.sgp.service.CollaborateurService;
import dev.sgp.util.Constantes;

public class AjouterCollaborateurController extends HttpServlet {

	private final CollaborateurService collabService = Constantes.COLLAB_SERVICE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg = (String)req.getParameter("msg");
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("/WEB-INF/views/collab/ajouterCollaborateur.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> params = Stream.of("nom", "prenom", "datenaissance", "adresse", "numss").filter(s -> req.getParameter(s) == null)
				.collect(Collectors.toList());
		
		resp.setContentType("text/html");
		if (params.isEmpty()) {
			params = Stream.of("nom", "prenom", "datenaissance", "adresse", "numss").map(s -> req.getParameter(s)).collect(Collectors.toList());
			String[] date = params.get(2).split("-");
			LocalDate dateNaissance = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
			Collaborateur collab = new Collaborateur(collabService.getMatricule(), params.get(0), params.get(1), dateNaissance, params.get(3), params.get(4), collabService.getEmail(params.get(0), params.get(1)), "image.jpg", ZonedDateTime.now(), true);
			collabService.sauvegarderCollaborateur(collab);
		}else{
		}
		resp.sendRedirect("lister");
	}

}
