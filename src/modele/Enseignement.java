package modele;

import java.util.List;

import javax.persistence.*;

@Entity
public class Enseignement {
	
	@Id
	@Column(length = 50)
	private String intitule;
	
	private Integer credits;
	private Integer volumeH;
	
	@ManyToMany
	private List<Plan> plans;
	
	
	/**
	 * 
	 */
	public Enseignement() {
	}

	/**
	 * @param id
	 * @param intitule
	 * @param credits
	 * @param volumeH
	 */
	public Enseignement(String intitule, Integer credits, Integer volumeH) {
		this.intitule = intitule;
		this.credits = credits;
		this.volumeH = volumeH;
	}


	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
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

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}


	@Override
	public String toString() {
		return "Enseignement [intitule=" + intitule + ", credits=" + credits + ", volumeH=" + volumeH + "]";
	}
	
}
