package modele;

import java.util.List;

import javax.persistence.*;

@Entity
@IdClass(CompositeCandidatureId.class)
public class Candidature {
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	private Etudiant etudiant;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	private Bourse bourse;
	
	@Id
	@OneToMany(mappedBy = "candidature")
	private List<Plan> plans;
	
	@ManyToOne
	private Enseignant respLocal;
	
	private Double noteLocale;

	@ManyToOne
	private Enseignant respErasmus;
	
	private Double noteErasmus;
	
	
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

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
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

abstract class CompositeCandidatureId {

	@SuppressWarnings("unused")
	private Etudiant etudiant;
	@SuppressWarnings("unused")
	private Bourse bourse;
	@SuppressWarnings("unused")
	private List<Plan> plans;
	
	
	/**
	 * 
	 */
	public CompositeCandidatureId() {
	}
	
}
