package modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Enseignant")
public class Enseignant extends Personne {
	
	@OneToMany(mappedBy = "respLocal")
	private List<Candidature> copies_locales;
	
	@OneToMany(mappedBy = "respErasmus")
	private List<Candidature> copies_erasmus;
	
	@OneToMany(mappedBy = "respLocal")
	private List<Bourse> bourses_locales;
	
	
	/**
	 * @param nom
	 * @param prenom
	 */
	public Enseignant() {
	}

	/**
	 * @param id
	 * @param nom
	 * @param prenom
	 */
	public Enseignant(Integer id, String nom, String prenom) {
		super(id, nom, prenom);
		this.copies_locales = new ArrayList<>();
		this.copies_erasmus = new ArrayList<>();
	}


	public List<Candidature> getCopies_locales() {
		return copies_locales;
	}

	public void setCopies_locales(List<Candidature> copies_locales) {
		this.copies_locales = copies_locales;
	}

	public List<Candidature> getCopies_erasmus() {
		return copies_erasmus;
	}

	public void setCopies_erasmus(List<Candidature> copies_erasmus) {
		this.copies_erasmus = copies_erasmus;
	}


	@Override
	public String toString() {
		return "Enseignant [id=" + getId() + ", nom=" + getNom() + ", prenom=" + getPrenom() + "]";
	}
	
}
