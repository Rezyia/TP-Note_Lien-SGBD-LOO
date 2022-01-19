package modele;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@IdClass(CompositePlanId.class)
public class Plan {

	@Id
	@ManyToOne
	private Candidature candidature;
	
	@Id
	@ManyToOne
	private Enseignement enseignement;
	
	
	/**
	 * 
	 */
	public Plan() {
	}
	
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
	
	public Enseignement getenseignement() {
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

class CompositePlanId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3616455865265704144L;
	private Integer candidature;
	private Integer enseignement;
	
	
	/**
	 * 
	 */
	public CompositePlanId() {
	}

	
	/**
	 * 
	 */
	public CompositePlanId(Integer candidature, Integer enseignement) {
		this.candidature = candidature;
		this.enseignement = enseignement;
	}
	
	
	public Integer getCandidature() {
		return candidature;
	}
	
	public void setCandidature(Integer candidature) {
		this.candidature = candidature;
	}
	
	public Integer getenseignement() {
		return enseignement;
	}
	
	public void setEnseignement(Integer enseignement) {
		this.enseignement = enseignement;
	}
	
}
