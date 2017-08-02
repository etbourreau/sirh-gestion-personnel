package dev.sgp.util;

import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;
import dev.sgp.service.VisiteService;

public interface Constantes {

	CollaborateurService COLLAB_SERVICE = new CollaborateurService();
	DepartementService DEPARTEMENT_SERVICE = new DepartementService();
	VisiteService VISITE_SERVICE = new VisiteService();
	
}
