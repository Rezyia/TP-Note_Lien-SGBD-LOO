package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import app.App;
import modele.Enseignement;

public class EnseignementDAO {

	/**
	 * Ajoute un Enseignement à la BDD 
	 * @param liste
	 */
	public static void addEnseignement(Enseignement ens) throws SQLException {
		App.em.getTransaction().begin();
		App.em.persist(ens);
		App.em.getTransaction().commit();
	}
	
	
	/**
	 * Retourne une liste d'objets Enseignement initialisés à partir des données récupérées par la BDD
	 * @return
	 */
	public static List<Enseignement> getEnseignements() {
		Query query = App.em.createQuery("from Enseignement");
		
		List<Enseignement> listeEnseignements = query.getResultList();
		
		return listeEnseignements;
	}
	
	
	
	/**
	 * Retourne un objets Enseignement associé à l'id passé en paramètre
	 * @param id
	 * @return
	 */
	public static Enseignement getEnseignementById(String id) {
		Query query = App.em.createQuery("from Enseignement E where E.id = :ens_id"); 
		query.setParameter("ens_id", id);
		
		Enseignement ens = (Enseignement) query.getSingleResult();
		
		return ens;
	}
	
	/**
	 * Retourne un objets Enseignement associé à l'intitulé passé en paramètre
	 * @param intitule
	 * @return
	 */
	public static Enseignement getEnseignementByIntitule(String intitule) {
		Query query = App.em.createQuery("from Enseignement E where E.intitule = :intitule"); 
		query.setParameter("intitule", intitule);
		
		Enseignement ens = (Enseignement) query.getSingleResult();
		
		return ens;
	}
	
}
