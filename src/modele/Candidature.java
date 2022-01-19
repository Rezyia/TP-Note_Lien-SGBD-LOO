package modele;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Candidature {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	public Candidature(Integer id, Etudiant etudiant, Bourse bourse, Enseignant respLocal, Enseignant respErasmus,
			Double noteLocale, Double noteErasmus) {
		this.id = id;
		this.etudiant = etudiant;
		this.bourse = bourse;
		this.respLocal = respLocal;
		this.respErasmus = respErasmus;
		this.noteLocale = noteLocale;
		this.noteErasmus = noteErasmus;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
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
		return "Candidature [id=" + id + ", etudiant=" + etudiant + ", bourse=" + bourse + ", plan=" + plan
				+ ", respLocal=" + respLocal + ", noteLocale=" + noteLocale + ", respErasmus=" + respErasmus
				+ ", noteErasmus=" + noteErasmus + "]";
	}

}
