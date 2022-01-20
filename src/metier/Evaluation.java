package metier;

import dao.CandidatureDAO;
import dao.EnseignantDAO;
import dao.EtudiantDAO;
import modele.Candidature;
import modele.Enseignant;
import modele.Etudiant;

public abstract class Evaluation {
	
	/**
	 * Change la note d'une candidature lié au responsable passé en paramètre 
	 * @param idCandidature
	 * @param idResponsable
	 * @param note
	 * @return
	 */
	public static boolean changeNote(Integer idCandidature, Integer idResponsable, Double note) {
		Candidature c = CandidatureDAO.getCandidatureById(idCandidature);
		Enseignant resp = EnseignantDAO.getEnseignantById(idResponsable);

		Enseignant era = c.getRespErasmus();
		Enseignant local = c.getRespLocal();
		if (era != null && era.getId() == resp.getId()) { // Si responsable Erasmus
			c.setNoteErasmus(note);
		}
		else if (local != null && local.getId() == resp.getId()) { // Si responsable Local
			c.setNoteLocale(note);
		}
		else {
			return false;
		}
		
		CandidatureDAO.addCandidature(c);
		
		return true;
	}
	
	
	/**
	 * Calcule le score d'une candidature passée en paramètre
	 * @param idCandidature
	 * @return null si le score n'a pas pu être calculé, un Double de la moyenne des 3 notes sinon
	 */
	public static Double calculerScore(Candidature c) {
		Double noteDS = c.getEtudiant().getMoyDS();
		Double noteRespLocal = c.getNoteLocale();
		Double noteRespErasmus = c.getNoteErasmus();
		Double score = null;
		if (noteDS != null && noteRespLocal != null && noteRespErasmus != null)
			score = (noteDS + noteRespLocal + noteRespErasmus) / 3;
			
		return score;
	}
	
}
