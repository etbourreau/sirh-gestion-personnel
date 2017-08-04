package dev.sgp.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dev.sgp.entite.Departement;

@Stateless
public class DepartementService {

	@PersistenceContext private EntityManager em;

	public void sauvegarderDepartement(Departement departement) {
		em.persist(departement);
	}

	public List<Departement> listerDepartements() {
		return em.createNamedQuery("departement.findAllDepartements",
				Departement.class)
				.getResultList();
	}

	public Departement getDepartementById(int idDepartement) {
		return em.createNamedQuery("departement.getDepartementById",
				Departement.class)
				.setParameter("id", idDepartement)
				.getSingleResult();
	}

}
