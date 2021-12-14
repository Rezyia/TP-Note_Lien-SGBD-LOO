package modele;

public class Enseignant extends Personne {
	
	private Integer id;


	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 */
	public Enseignant(Integer id, String nom, String prenom) {
		super(nom, prenom);
		this.id = id;
	}


	public Integer getId() {
		return id;
	}
	
	private void setId(Integer id) {
		this.id = id;
	}
	
}
