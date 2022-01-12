package modele;

import java.util.List;

import javax.persistence.*;

@Entity
public class Etudiant extends Personne {

	private Double moyDS;
	
	@OneToMany(mappedBy = "etudiant")
	public List<Candidature> candidatures;
	
	
	/**
	 * @param numero
	 * @param nom
	 * @param prenom
	 * @param moyDS
	 */
	public Etudiant(Integer numero, String nom, String prenom, Double moyDS) {
		super(numero, nom, prenom);
		this.moyDS = moyDS;
	}
	

	public Double getMoyDS() {
		return moyDS;
	}

	public void setMoyDS(Double moyDS) {
		this.moyDS = moyDS;
	}


	@Override
	public String toString() {
		return "Etudiant [numero=" + getNumero() + ", nom=" + getNom() + ", prenom=" + getPrenom() + ", moyDS=" + moyDS + "]";
	}
	
}
