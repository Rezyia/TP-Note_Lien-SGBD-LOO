package metier;

import dao.CandidatureDAO;
import dao.EnseignantDAO;
import modele.Candidature;

public abstract class Enregistrement {

	
	/**
	 * 
	 * @param c
	 * @param ensId
	 * @param champ
	 * @return
	 */
	public static boolean updateCandidature(Integer candidatureId, Integer ensId, Champs champ) {
		Candidature c = CandidatureDAO.getCandidatureById(candidatureId);
		
		if (champ == Champs.RESPONSABLE_LOCAL) {
			c.setRespLocal(EnseignantDAO.getEnseignantById(ensId));
		} else if (champ == Champs.RESPONSABLE_ERASMUS) {
			c.setRespErasmus(EnseignantDAO.getEnseignantById(ensId));
		} else return false;
			
		return true;
	}
}
