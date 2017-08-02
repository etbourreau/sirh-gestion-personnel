package dev.sgp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.sgp.entite.Collaborateur;

public class CollaborateurService {

	private static final Logger LOG= LoggerFactory.getLogger(CollaborateurService.class);
	List<Collaborateur> listeCollaborateurs = new ArrayList<>();

	public List<Collaborateur> listerCollaborateurs() {
		return listeCollaborateurs;
	}

	public void sauvegarderCollaborateur(Collaborateur collab) {
		listeCollaborateurs.add(collab);
	    LOG.info("Creating collaborateur "+collab.getMatricule());
	}

	public String getMatricule() {
		Boolean notValid;
		String matricule;
		do {
			notValid = false;
			matricule = UUID.randomUUID().toString();
			for (Collaborateur c : listeCollaborateurs) {
				if (c.getMatricule().equals(matricule)) {
					notValid = true;
				}
			}
		} while (notValid);

		return matricule;
	}

	public String getEmail(String nom, String prenom) {
		return prenom + "." + nom + "@" + ResourceBundle.getBundle("application").getString("emailSuffix");
	}
	
	public Optional<Collaborateur> getCollaborateurByMatricule(String matricule){
		return listeCollaborateurs.stream().filter(c -> c.getMatricule().equals(matricule)).findAny();
	}

}
