package dev.sgp.ecouteur;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dev.sgp.entite.Collaborateur;
import dev.sgp.entite.Departement;
import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;

@WebListener
public class InitDataListener implements ServletContextListener {

	@Inject
	private DepartementService dps;
	@Inject
	private CollaborateurService cs;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		dps.sauvegarderDepartement(new Departement(null, "Comptabilité"));
		dps.sauvegarderDepartement(new Departement(null, "Ressources Humaines"));
		dps.sauvegarderDepartement(new Departement(null, "Informatique"));
		dps.sauvegarderDepartement(new Departement(null, "Administratif"));

		cs.sauvegarderCollaborateur(new Collaborateur(UUID.randomUUID().toString(), "Guironnet", "Damichou",
				LocalDate.of(1992, 3, 21), "5 rue des Coquelicots", "223456789012345", cs.getEmail("Guironnet", "Damichou"), "image.jpg", ZonedDateTime.now(), true,
				"Développeur Front .NET", dps.getDepartementById(3), "Crédit Agricole", "4 couleurs", "Taliban", "0664880470"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// context destroy
	}

}