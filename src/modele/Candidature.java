package modele;

public class Candidature extends Table {

	private Etudiant etudiant;
	private Bourse bourse;
	private Enseignant respLocal;
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

}
