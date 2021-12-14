package modele;

public class Plan extends Table {

	private Candidature candidature;
	private Enseignement enseignement;
	
	
	/**
	 * @param id
	 * @param candidature
	 * @param enseignement
	 */
	public Plan(Integer id, Candidature candidature, Enseignement enseignement) {
		super(id);
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
		
}
