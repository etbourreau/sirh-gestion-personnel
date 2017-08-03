
package dev.sgp.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dev.sgp.entite.CollabEvt;
import dev.sgp.entite.Collaborateur;
import dev.sgp.entite.TypeCollabEvt;

@Stateless
public class CollaborateurService {

	@Inject Event<CollabEvt> collabEvent;
	@PersistenceContext private EntityManager em;

	public List<Collaborateur> listerCollaborateurs() {
		return em.createNamedQuery("collaborateur.findAllCollaborateurs",
				Collaborateur.class)
				.getResultList();
	}

	public void sauvegarderCollaborateur(Collaborateur collab) {
		em.persist(collab);
		collabEvent.fire(new CollabEvt(ZonedDateTime.now(), TypeCollabEvt.CREATION_COLLAB, collab.getMatricule()));
	}

	public void updateCollaborateur(Collaborateur collaborateur) {
		Collaborateur c = getCollaborateurByMatricule(collaborateur.getMatricule()).get();
		c.setActif(collaborateur.getActif());
		c.setAdresse(collaborateur.getAdresse());
		c.setBanque(collaborateur.getBanque());
		c.setBic(collaborateur.getBic());
		c.setIban(collaborateur.getIban());
		c.setTel(collaborateur.getTel());
		c.setIntitulePoste(collaborateur.getIntitulePoste());
		c.setDepartement(collaborateur.getDepartement());
		em.merge(c);
		collabEvent.fire(new CollabEvt(ZonedDateTime.now(), TypeCollabEvt.MODIFICATION_COLLAB, collaborateur.getMatricule()));
	}

	public String getMatricule() {
		Boolean notValid;
		String matricule;
		List<Collaborateur> listeCollaborateurs = em.createNamedQuery("collaborateur.findAllCollaborateur", Collaborateur.class)
				.getResultList();
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
		return prenom.toLowerCase() + "." + nom.toLowerCase() + "@" + ResourceBundle.getBundle("application").getString("emailSuffix");
	}
	
	public Optional<Collaborateur> getCollaborateurByMatricule(String matricule){
		return em.createNamedQuery("collaborateur.getCollaborateurByMatricule", Collaborateur.class)
				.setParameter("matricule", matricule)
				.getResultList().stream().filter(c -> c.getMatricule().equals(matricule)).findAny();
	}

}
