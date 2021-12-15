package modele;

public class Etudiant extends Personne {

	private Integer numero;
	private Double moyDS;
	
	
	/**
	 * @param numero
	 * @param nom
	 * @param prenom
	 * @param moyDS
	 */
	public Etudiant(Integer numero, String nom, String prenom, Double moyDS) {
		super(numero, nom, prenom);
		this.numero = numero;
		this.moyDS = moyDS;
	}


	public Integer getNumero() {
		return numero;
	}

	protected void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	protected void setId(Integer id) {
		setNumero(id);
	}

	public Double getMoyDS() {
		return moyDS;
	}

	public void setMoyDS(Double moyDS) {
		this.moyDS = moyDS;
	}
	
}
