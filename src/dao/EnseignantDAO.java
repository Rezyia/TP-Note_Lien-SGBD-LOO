package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import app.App;
import modele.Enseignant;

public class EnseignantDAO {
	
	/**
	 * Ajoute un enseignant � la BDD
	 * @throws SQLException
	 */
	public static void addEnseignant(Enseignant ens) throws SQLException {
		App.em.getTransaction().begin();
		App.em.persist(ens);
		App.em.getTransaction().commit();
	}	
	
	
	/**
	 * Retourne une liste d'objets Enseignant initialis�s � partir des donn�es r�cup�r�es par la BDD
	 * @return
	 */
	public static List<Enseignant> getEnseignants() {
		Query query = App.em.createQuery("from Enseignant");
				
		List<Enseignant> listeEnseignants = query.getResultList();
		
		return listeEnseignants;
	}
	
	
	
	/**
	 * Retourne un objets Enseignant depuis la BDD associ� � l'id pass� en param�tre
	 * @param id
	 * @return
	 */
	public static Enseignant getEnseignantById(Integer id) {
		Query query = App.em.createQuery("from Enseignant E where E.id = :ens_id"); 
		query.setParameter("ens_id", id);
		
		Enseignant ens = (Enseignant) query.getSingleResult();
		
		return ens;
	}
	
}
