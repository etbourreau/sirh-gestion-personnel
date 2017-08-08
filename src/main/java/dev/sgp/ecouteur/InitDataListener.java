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

		cs.sauvegarderCollaborateur(
			new Collaborateur(UUID.randomUUID().toString(), "Guironnet", "Damichou", LocalDate.of(1992, 3, 21),
					"5 rue des Coquelicots", "223456789012345", cs.getEmail("Guironnet", "Damichou"),
					"https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAkLAAAAJDIzOGEzNDA0LWZkOTUtNDAyMS1hY2E3LTIzMWZjY2IwYWNlYg.jpg",
					ZonedDateTime.now(), true, "Développeur Front .NET", dps.getDepartementById(3),
					"Crédit Agricole", "4 couleurs", "Taliban", "0664880470"));
		cs.sauvegarderCollaborateur(
			new Collaborateur(UUID.randomUUID().toString(), "Samson", "Jéjé",
				LocalDate.of(1993, 1, 7), "19 rue des Aubergines", "123456789012345", cs.getEmail("Samson", "Jéjé"),
				"https://scontent-cdg2-1.xx.fbcdn.net/v/t1.0-9/313559_4852773566692_531069632_n.jpg?oh=5d7d9218f3d4d2a1eb2185f1a9f0c785&oe=5A255812",
				ZonedDateTime.now(), true, "Développeur COBOL", dps.getDepartementById(3), "Brico Dépôt", "Violet",
				"Cali Bani", "0647924931"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// context destroy
	}

}