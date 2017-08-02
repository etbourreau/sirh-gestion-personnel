package dev.sgp.service;

import java.util.ArrayList;
import java.util.List;

import dev.sgp.entite.Departement;
import dev.sgp.exceptions.InvalidDepartementException;

public class DepartementService {

	List<Departement> listeDepartements = new ArrayList<>();
	
	public DepartementService(){
		listeDepartements.add(new Departement(0, "Comptabilité"));
		listeDepartements.add(new Departement(1, "Ressources Humaines"));
		listeDepartements.add(new Departement(2, "Informatique"));
		listeDepartements.add(new Departement(3, "Administratif"));
	}

	public List<Departement> listerDepartements() {
		return listeDepartements;
	}

	public Departement getDepartementById(int idDepartement) {
		return listeDepartements.stream().filter(d -> d.getId()==idDepartement).findAny().orElseThrow(InvalidDepartementException::new);
	}

}
