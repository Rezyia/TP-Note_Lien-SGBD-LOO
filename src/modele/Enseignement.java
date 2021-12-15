package modele;

public class Enseignement extends TableWithId {

	private String nom;
	private Integer credits;
	private Integer volumeH;

	/**
	 * @param id
	 * @param nom
	 * @param credits
	 * @param volumeH
	 */
	public Enseignement(Integer id, String intitule, Integer credits, Integer volumeH) {
		super(id);
		this.nom = nom;
		this.credits = credits;
		this.volumeH = volumeH;
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


	@Override
	public String toString() {
		return "Enseignement [id=" + getId() + ", nom=" + nom + ", credits=" + credits + ", volumeH=" + volumeH + "]";
	}
	
}
