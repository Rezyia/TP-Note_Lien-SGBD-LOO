package modele;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Etudiant")
public class Etudiant extends Personne {

	private Double moyDS;
	
	@OneToMany(mappedBy = "etudiant")
	private List<Candidature> candidatures;
	
	
	/**
	 * @param numero
	 * @param nom
	 * @param prenom
	 * @param moyDS
	 */
	public Etudiant(String nom, String prenom, Double moyDS) {
		super(nom, prenom);
		this.moyDS = moyDS;
	}
	

	public Double getMoyDS() {
		return moyDS;
	}

	public void setMoyDS(Double moyDS) {
		this.moyDS = moyDS;
	}

	public List<Candidature> getCandidatures() {
		return candidatures;
	}

	public void setCandidatures(List<Candidature> candidatures) {
		this.candidatures = candidatures;
	}


	@Override
	public String toString() {
		return "Etudiant [numero=" + getId() + ", nom=" + getNom() + ", prenom=" + getPrenom() + ", moyDS=" + moyDS + "]";
	}
	
}
