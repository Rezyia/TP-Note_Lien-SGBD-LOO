package modele;

public abstract class Personne extends Table {

	private String nom;
	private String prenom;
	
	
	/**
	 * @param nom
	 * @param prenom
	 */
	public Personne(Integer id, String nom, String prenom) {
		super(id);
		this.nom = nom;
		this.prenom = prenom;
	}
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
}
