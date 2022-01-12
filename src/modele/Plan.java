package modele;

import java.util.List;

import javax.persistence.*;

@Entity
@IdClass(CompositePlanId.class)
public class Plan {

	@Id
	@ManyToOne
	private Candidature candidature;
	
	@Id
	@ManyToMany(mappedBy = "") //TODO Creer attribut Plan dans Enseignement
	private List<Enseignement> enseignements;
	
	
	/**
	 * @param candidature
	 * @param enseignement
	 */
	public Plan(Candidature candidature, List<Enseignement> enseignements) {
		this.candidature = candidature;
		this.enseignements = enseignements;
	}
	
	
	public Candidature getCandidature() {
		return candidature;
	}
	
	public void setCandidature(Candidature candidature) {
		this.candidature = candidature;
	}
	
	public List<Enseignement> getEnseignements() {
		return enseignements;
	}
	
	public void setEnseignement(List<Enseignement> enseignements) {
		this.enseignements = enseignements;
	}


	@Override
	public String toString() {
		return "Plan [candidature=" + candidature + ", enseignement=" + enseignements + "]";
	}
		
}


abstract class CompositePlanId {

	@SuppressWarnings("unused")
	private Candidature candidature;
	@SuppressWarnings("unused")
	private List<Enseignement> enseignements;
	
	
	/**
	 * 
	 */
	public CompositePlanId() {
	}
	
}
