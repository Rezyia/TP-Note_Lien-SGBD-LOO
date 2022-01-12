package modele;

import java.util.List;

import javax.persistence.*;

@Entity
public class Bourse extends TableNumero {
	
	private String destination;
	private Integer nbPostes;
	
	@OneToMany(mappedBy = "bourse")
	public List<Candidature> candidatures;
	
	@OneToOne(mappedBy = "") //TODO Creer 'List<Bourse> copies' dans Enseignant
	private Enseignant respLocal;

	
	/**
	 * @param id
	 * @param destination
	 * @param nbPostes
	 * @param respLocal
	 */
	public Bourse(Integer id, Enseignant respLocal, String destination, Integer nbPostes) {
		super(id);
		this.destination = destination;
		this.nbPostes = nbPostes;
		this.respLocal = respLocal;
	}
	

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getNbPostes() {
		return nbPostes;
	}

	public void setNbPostes(Integer nbPostes) {
		this.nbPostes = nbPostes;
	}

	public Enseignant getRespLocal() {
		return respLocal;
	}

	public void setRespLocal(Enseignant respLocal) {
		this.respLocal = respLocal;
	}


	@Override
	public String toString() {
		return "Bourse [id=" + getNumero() + ", destination=" + destination + ", nbPostes=" + nbPostes + ", respLocal=" + respLocal + "]";
	}

}
