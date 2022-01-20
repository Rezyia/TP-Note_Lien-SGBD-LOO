package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import app.BDD;
import modele.Enseignant;

public class EnseignantDAO {
	
	/**
	 * Ajoute un enseignant à la BDD
	 * @throws SQLException
	 */
	public static void addEnseignant(Enseignant ens) throws SQLException {
		BDD.em.getTransaction().begin();
		BDD.em.persist(ens);
		BDD.em.getTransaction().commit();
	}	
	
	
	/**
	 * Retourne une liste d'objets Enseignant initialisés à partir des données récupérées par la BDD
	 * @return
	 */
	public static List<Enseignant> getEnseignants() {
		Query query = BDD.em.createQuery("from Enseignant");
				
		List<Enseignant> listeEnseignants = query.getResultList();
		
		return listeEnseignants;
	}
	
	
	
	/**
	 * Retourne un objets Enseignant depuis la BDD associé à l'id passé en paramètre
	 * @param id
	 * @return
	 */
	public static Enseignant getEnseignantById(Integer id) {
		Query query = BDD.em.createQuery("from Enseignant E where E.id = :ens_id"); 
		query.setParameter("ens_id", id);
		
		Enseignant ens = (Enseignant) query.getSingleResult();
		
		return ens;
	}
	
}
