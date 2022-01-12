package modele;

import javax.persistence.*;

@Entity
public class Enseignement extends TableNumero {

	private String intitule;
	private Integer credits;
	private Integer volumeH;

	
	/**
	 * @param id
	 * @param intitule
	 * @param credits
	 * @param volumeH
	 */
	public Enseignement(Integer id, String intitule, Integer credits, Integer volumeH) {
		super(id);
		this.intitule = intitule;
		this.credits = credits;
		this.volumeH = volumeH;
	}


	public String getNom() {
		return intitule;
	}

	public void setNom(String nom) {
		this.intitule = nom;
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
		return "Enseignement [id=" + getNumero() + ", intitule=" + intitule + ", credits=" + credits + ", volumeH=" + volumeH + "]";
	}
	
}
