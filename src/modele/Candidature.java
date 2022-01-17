package modele;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@IdClass(CompositeCandidatureId.class)
public class Candidature implements Serializable {
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	private Etudiant etudiant;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	private Bourse bourse;
	
	@Id
	@OneToOne(mappedBy = "candidature")
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

class CompositeCandidatureId implements Serializable {

	private Etudiant etudiant;
	private Bourse bourse;
	private Plan plan;
	
	
	/**
	 * 
	 */
	public CompositeCandidatureId() {
	}
	
	
	/**
	 * 
	 */
	public CompositeCandidatureId(Etudiant etudiantId, Bourse bourseId, Plan planID) {
		this.etudiant = etudiantId;
		this.bourse = bourseId;
		this.plan = planID;
	}
	
	
	public Etudiant getEtudiant() {
		return etudiant;
	}
	
	public void setEtudiant(Etudiant etudiantId) {
		this.etudiant = etudiantId;
	}
	
	public Bourse getBourse() {
		return bourse;
	}
	
	public void setBourse(Bourse bourseId) {
		this.bourse = bourseId;
	}

	public Plan getplan() {
		return plan;
	}

	public void setplan(Plan planID) {
		this.plan = planID;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bourse == null) ? 0 : bourse.hashCode());
		result = prime * result + ((etudiant == null) ? 0 : etudiant.hashCode());
		result = prime * result + ((plan == null) ? 0 : plan.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeCandidatureId other = (CompositeCandidatureId) obj;
		if (bourse == null) {
			if (other.bourse != null)
				return false;
		} else if (!bourse.equals(other.bourse))
			return false;
		if (etudiant == null) {
			if (other.etudiant != null)
				return false;
		} else if (!etudiant.equals(other.etudiant))
			return false;
		if (plan == null) {
			if (other.plan != null)
				return false;
		} else if (!plan.equals(other.plan))
			return false;
		return true;
	}
	
}
