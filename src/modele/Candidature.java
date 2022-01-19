package modele;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Candidature implements Serializable {
	
	@Id
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Etudiant etudiant;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Bourse bourse;
	
	@ManyToOne
	private Plan plan;
	
	@ManyToOne
	private Enseignant respLocal;
	
	private Double noteLocale;

	@ManyToOne
	private Enseignant respErasmus;
	
	private Double noteErasmus;
	
	
	/**
	 * 
	 */
	public Candidature() {
	}
	
	/**
	 * @param etudiant
	 * @param bourse
	 * @param respLocal
	 * @param respErasmus
	 * @param noteLocale
	 * @param noteErasmus
	 */
	public Candidature(Etudiant etudiant, Bourse bourse, Enseignant respLocal, Enseignant respErasmus,
			Double noteLocale, Double noteErasmus) {
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

	public Plan getplan() {
		return plan;
	}

	public void setplan(Plan plan) {
		this.plan = plan;
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
		return "Candidature [etudiant=" + etudiant + ", bourse=" + bourse + ", respLocal=" + respLocal
				+ ", respErasmus=" + respErasmus + ", noteLocale=" + noteLocale + ", noteErasmus=" + noteErasmus + "]";
	}

}
