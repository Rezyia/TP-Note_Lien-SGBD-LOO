package modele;

public class Enseignant extends Personne {
	
	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 */
	public Enseignant(Integer id, String nom, String prenom) {
		super(id, nom, prenom);
	}

	@Override
	public String toString() {
		return "Enseignant [id=" + getId() + ", nom=" + getNom() + ", prenom=" + getPrenom() + "]";
	}
	
}
