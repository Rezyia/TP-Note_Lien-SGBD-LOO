package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import app.App;
import modele.Enseignement;

public class EnseignementDAO {

	/**
	 * Ajoute un Enseignement � la BDD 
	 * @param liste
	 */
	public static void addEnseignement(Enseignement ens) throws SQLException {
		App.em.getTransaction().begin();
		App.em.persist(ens);
		App.em.getTransaction().commit();
	}
	
	
	/**
	 * Retourne une liste d'objets Enseignement initialis�s � partir des donn�es r�cup�r�es par la BDD
	 * @return
	 */
	public static List<Enseignement> getEnseignements() {
		Query query = App.em.createQuery("from Enseignement");
		
		List<Enseignement> listeEnseignements = query.getResultList();
		
		return listeEnseignements;
	}
	
	
	
	/**
	 * Retourne un objets Enseignement associ� � l'id pass� en param�tre
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
	 * Retourne un objets Enseignement associ� � l'intitul� pass� en param�tre
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
