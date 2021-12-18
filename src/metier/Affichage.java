package metier;

import java.util.Iterator;
import java.util.List;

import dao.CandidatureDAO;
import dao.EtudiantDAO;
import modele.Candidature;
import modele.Etudiant;

public class Affichage {

	public static List<Candidature> afficherCandidatures(Integer id, Champs type) {
		List<Candidature> liste = null;
		
		if (type == Champs.ETUDIANT) {
			liste = CandidatureDAO.getCandidaturesByEtudiant(id);
		} else if (type == Champs.ENSEIGNANT) {
			liste = CandidatureDAO.getCandidaturesByResponsable(id);
		} else {
			return null;
		}
		
		return liste;
	}
	
	public static String afficherEtudiant(Integer numero) {
		Etudiant etu = EtudiantDAO.getEtudiantByNumero(numero);
		return etu.toString();
	}
	
}
