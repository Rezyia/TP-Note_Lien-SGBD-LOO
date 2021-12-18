package metier;

import java.util.Iterator;
import java.util.List;

import dao.CandidatureDAO;
import modele.Candidature;

public class Affichage {

	public static boolean afficherCandidatures(Integer id, Champs type) {
		List<Candidature> liste = null;
		
		if (type == Champs.ETUDIANT) {
			liste = CandidatureDAO.getCandidaturesByEtudiant(id);
		} else if (type == Champs.ENSEIGNANT) {
			liste = CandidatureDAO.getCandidaturesByResponsable(id);
		} else {
			return false;
		}		
		
		System.out.println("Liste des candidatures :");
		Iterator<Candidature> ite = liste.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
		
		return true;
	}
	
}
