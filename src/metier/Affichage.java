package metier;

import java.util.List;

import dao.CandidatureDAO;
import dao.EtudiantDAO;
import modele.Candidature;
import modele.Etudiant;

public class Affichage {

	/**
	 * Retourne la liste des Candidatures à afficher de la personne correspondante
	 * @param id Identifiant de la personne 
	 * @param type Type de la personne (Champs.ETUDIANT ou Champs.ENSEIGNANT)
	 * @return
	 */
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
	
	
	/**
	 * Retourne une chaîne de caractères correspondant à l'étudiant
	 * @param id Numéro de l'étudiant 
	 * @return
	 */
	public static String afficherEtudiant(Integer id) {
		Etudiant etu = EtudiantDAO.getEtudiantById(id);
		return etu.toString();
	}
	
}
