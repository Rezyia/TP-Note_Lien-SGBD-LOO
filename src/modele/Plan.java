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

	private Candidature candidature;
	private Enseignement enseignement;
	
	
	/**
	 * 
	 */
	public CompositePlanId() {
	}

	
	/**
	 * 
	 */
	public CompositePlanId(Candidature candidature, Enseignement enseignement) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidature == null) ? 0 : candidature.hashCode());
		result = prime * result + ((enseignement == null) ? 0 : enseignement.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositePlanId other = (CompositePlanId) obj;
		if (candidature == null) {
			if (other.candidature != null)
				return false;
		} else if (!candidature.equals(other.candidature))
			return false;
		if (enseignement == null) {
			if (other.enseignement != null)
				return false;
		} else if (!enseignement.equals(other.enseignement))
			return false;
		return true;
	}
	
}
