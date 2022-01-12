package modele;

import javax.persistence.*;

@Entity
public class Candidature extends TableNumero {

	@ManyToOne(cascade = CascadeType.ALL)
	private Etudiant etudiant;
	
	@ManyToOne(cascade = CascadeType.ALL) //TODO Creer 'List<Candidature> candidatures' dans Bourse
	private Bourse bourse;
	
	@ManyToOne //TODO Creer 'List<Candidature> copies' dans Enseignant
	private Enseignant respLocal;

	@ManyToOne //TODO Idem
	private Enseignant respErasmus;
	
	private Double noteLocale;
	private Double noteErasmus;
	
	
	/**
	 * @param id
	 * @param etudiant
	 * @param bourse
	 * @param respLocal
	 * @param respErasmus
	 * @param noteLocale
	 * @param noteErasmus
	 */
	public Candidature(Integer id, Etudiant etudiant, Bourse bourse, Enseignant respLocal, Enseignant respErasmus,
			Double noteLocale, Double noteErasmus) {
		super(id);
		this.etudiant = etudiant;
		this.bourse = bourse;
		this.respLocal = respLocal;
		this.respErasmus = respErasmus;
		this.noteLocale = noteLocale;
		this.noteErasmus = noteErasmus;
	}

	
	public Etudiant getEtudiant() {
		return etudiant;
	}
	
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
	public Bourse getBourse() {
		return bourse;
	}
	
	public void setBourse(Bourse bourse) {
		this.bourse = bourse;
	}
	
	public Enseignant getRespLocal() {
		return respLocal;
	}
	
	public void setRespLocal(Enseignant respLocal) {
		this.respLocal = respLocal;
	}
	
	public Enseignant getRespErasmus() {
		return respErasmus;
	}
	
	public void setRespErasmus(Enseignant respErasmus) {
		this.respErasmus = respErasmus;
	}
	
	public Double getNoteLocale() {
		return noteLocale;
	}
	
	public void setNoteLocale(Double noteLocale) {
		this.noteLocale = noteLocale;
	}
	
	public Double getNoteErasmus() {
		return noteErasmus;
	}

	public void setNoteErasmus(Double noteErasmus) {
		this.noteErasmus = noteErasmus;
	}


	@Override
	public String toString() {
		return "Candidature [id=" + getNumero() + ", etudiant=" + etudiant + ", bourse=" + bourse + ", respLocal=" + respLocal
				+ ", respErasmus=" + respErasmus + ", noteLocale=" + noteLocale + ", noteErasmus=" + noteErasmus + "]";
	}

}
