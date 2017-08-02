package dev.sgp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

import dev.sgp.entite.Collaborateur;

public class CollaborateurService {

	List<Collaborateur> listeCollaborateurs = new ArrayList<>();

	public List<Collaborateur> listerCollaborateurs() {
		return listeCollaborateurs;
	}

	public void sauvegarderCollaborateur(Collaborateur collab) {
		listeCollaborateurs.add(collab);
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

}
