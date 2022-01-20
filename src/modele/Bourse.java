package modele;

import java.util.List;

import javax.persistence.*;

@Entity
public class Bourse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String destination;
	private Integer nbPostes;
	
	@OneToMany(mappedBy = "bourse")
	private List<Candidature> candidatures;
	
	@ManyToOne
	private Enseignant respLocal;

	
	/**
	 * 
	 */
	public Bourse() {
	}

	/**
	 * @param id
	 * @param destination
	 * @param nbPostes
	 * @param respLocal
	 */
	public Bourse(Enseignant respLocal, String destination, Integer nbPostes) {
		this.destination = destination;
		this.nbPostes = nbPostes;
		this.respLocal = respLocal;
	}

	
	public Integer getId() {
		return id;
	}
	
	protected void setId(Integer id) {
		this.id = id;
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

	public List<Candidature> getCandidatures() {
		return candidatures;
	}

	public void setCandidatures(List<Candidature> candidatures) {
		this.candidatures = candidatures;
	}

	public Enseignant getRespLocal() {
		return respLocal;
	}

	public void setRespLocal(Enseignant respLocal) {
		this.respLocal = respLocal;
	}


	@Override
	public String toString() {
		return "Bourse [id=" + getId() + ", destination=" + destination + ", nbPostes=" + nbPostes + ", respLocal=" + respLocal + "]";
	}

}
