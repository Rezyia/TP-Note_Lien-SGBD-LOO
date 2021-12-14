package modele;

public class Enseignement {

	private Integer id;
	private String nom;
	private Integer credits;
	private Integer volumeH;


	/**
	 * @param id
	 * @param nom
	 * @param credits
	 * @param volumeH
	 */
	public Enseignement(Integer id, String nom, Integer credits, Integer volumeH) {
		this.id = id;
		this.nom = nom;
		this.credits = credits;
		this.volumeH = volumeH;
	}

	
	public Integer getId() {
		return id;
	}
	
	private void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public Integer getVolumeH() {
		return volumeH;
	}

	public void setVolumeH(Integer volumeH) {
		this.volumeH = volumeH;
	}
	
}
