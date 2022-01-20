package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import app.App;
import modele.Candidature;

public class CandidatureDAO {
	
	/**
	 * Ajoute une candidature à la liste passée en paramètre 
	 * @param liste
	 */
	public static void addCandidature(Candidature c) {
		App.em.getTransaction().begin();
		App.em.persist(c);
		App.em.getTransaction().commit();
	}
		
	
	/**
	 * Retourne la liste statique des candidatures
	 * @return
	 */
	public static List<Candidature> getCandidatures() {
		Query query = App.em.createQuery("from Candidature");
		
		List<Candidature> listeCandidatures = query.getResultList();
		
		return listeCandidatures;
	}
	
	
	
	/**
	 * Retourne un objets Candidature associé à l'id passé en paramètre 
	 * @param id
	 * @return
	 */
	public static Candidature getCandidatureById(Integer id) {
		Query query = App.em.createQuery("from Candidature C where C.id = :c_id"); 
		query.setParameter("c_id", id);
		
		Candidature candidature = (Candidature) query.getSingleResult();
		
		return candidature;
	}
	
	
	
	/**
	 * Retourne une liste d'objets Candidature ayant comme responsable idResp.
	 * @param idResp
	 * @return
	 */
	public static List<Candidature> getCandidaturesByResponsable(Integer idResp) {
		Query query = App.em.createQuery("SELECT c FROM Candidature c WHERE c.respLocal = :resp_id OR c.respErasmus = :resp_id"); 
				query.setParameter("resp_id", idResp);

		List<Candidature> listeCandidatures = query.getResultList();
		
		return listeCandidatures;

	}
	
	
	/**
	 * Retourne une liste d'objets Candidature ayant comme etudiant idEtu.
	 * @param idEtu
	 * @return
	 */
	public static List<Candidature> getCandidaturesByEtudiant(Integer idEtu) {
		Query query = App.em.createQuery("SELECT c FROM Candidature c WHERE c.etudiant = :etu_id"); 
		query.setParameter("etu_id", idEtu);

		List<Candidature> listeCandidatures = query.getResultList();
		
		return listeCandidatures;
	}
	
	
	/**
	 * Retourne une liste d'objets Candidature disponibles pour être affectées à des responsables.
	 * @return
	 */
	public static List<Candidature> getCandidaturesDisponibles() {
		String sql = "FROM Candidature WHERE respLocal IS NULL OR respErasmus IS NULL;";
		Query query = App.em.createQuery(sql); 

		List<Candidature> listeCandidatures = query.getResultList();
		
		return listeCandidatures;
	}
	
}
