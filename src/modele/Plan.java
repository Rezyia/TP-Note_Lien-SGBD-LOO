package modele;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@IdClass(CompositePlanId.class)
public class Plan implements Serializable {

	@Id
	@ManyToOne
	private Candidature candidature;
	
	@Id
	@ManyToMany(mappedBy = "plans")
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

abstract class CompositePlanId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3078812878198156555L;
	private int candidature;
	private int enseignements;
	
	
	/**
	 * 
	 */
	public CompositePlanId() {
	}

	
	/**
	 * 
	 */
	public CompositePlanId(int candidature, int enseignements) {
		this.candidature = candidature;
		this.enseignements = enseignements;
	}
	
	
	public void setEnseignements(int enseignements) {
		this.enseignements = enseignements;
	}
	
	public int getCandidature() {
		return candidature;
	}
	
	public void setCandidature(int candidature) {
		this.candidature = candidature;
	}
	
	public int getEnseignements() {
		return enseignements;
	}
	
	public void setEnseignement(int enseignements) {
		this.enseignements = enseignements;
	}
	
}
