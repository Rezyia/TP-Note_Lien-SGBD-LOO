package modele;

public class Bourse extends TableWithId {
	
	private String destination;
	private Integer nbPostes;
	private Enseignant respLocal;

	
	/**
	 * @param id
	 * @param destination
	 * @param nbPostes
	 * @param respLocal
	 */
	public Bourse(Integer id, String destination, Integer nbPostes, Enseignant respLocal) {
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
		return "Bourse [id=" + getId() + ", destination=" + destination + ", nbPostes=" + nbPostes + ", respLocal=" + respLocal + "]";
	}

}
