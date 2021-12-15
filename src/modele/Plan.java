package modele;

public class Plan {

	private Candidature candidature;
	private Enseignement enseignement;
	
	
	/**
	 * @param candidature
	 * @param enseignement
	 */
	public Plan(Candidature candidature, Enseignement enseignement) {
		this.candidature = candidature;
		this.enseignement = enseignement;
	}
	
	
	public Candidature getCandidature() {
		return candidature;
	}
	
	public void setCandidature(Candidature candidature) {
		this.candidature = candidature;
	}
	
	public Enseignement getEnseignement() {
		return enseignement;
	}
	
	public void setEnseignement(Enseignement enseignement) {
		this.enseignement = enseignement;
	}


	@Override
	public String toString() {
		return "Plan [candidature=" + candidature + ", enseignement=" + enseignement + "]";
	}
		
}
