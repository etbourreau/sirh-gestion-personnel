package dev.sgp.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.sgp.entite.VisiteWeb;

public class VisiteService {

	List<VisiteWeb> listeVisites = new ArrayList<>();

	public List<VisiteWeb> listerVisites() {
		return listeVisites;
	}

	public void sauvegarderVisite(VisiteWeb visite) {
		listeVisites.add(visite);
	}

	public int getId() {
		Optional<Integer> idMax = listeVisites.stream().map(VisiteWeb::getId)
				.max(Comparator.comparing(Integer::intValue));
		if (idMax.isPresent()) {
			return idMax.get() + 1;
		} else {
			return 0;
		}
	}

}
