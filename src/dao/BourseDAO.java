package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import app.BDD;
import modele.Bourse;

public class BourseDAO {
	
	/**
	 * Ajoute une bourse � la BDD
	 * @param liste 
	 */
	public static void addBourse(Bourse b) throws SQLException {
		BDD.em.getTransaction().begin();
		BDD.em.persist(b);
		BDD.em.getTransaction().commit();

	}
	
	
	/**
	 * Retourne la liste des bourses
	 */
	public static List<Bourse> getBourses() {
		Query query = BDD.em.createQuery("from Bourse");
		
		List<Bourse> listeBourses = query.getResultList();
		
		return listeBourses;
	}
	
	
	/**
	 * Retourne un objet Bourse initialis� � partir des donn�es r�cup�r�es depuis la BDD.
	 * @param id
	 * @return Bourse
	 */
	public static Bourse getBourseById(Integer id) {
		Query query = BDD.em.createQuery("from Bourse B where B.id = :b_id"); 
		query.setParameter("b_id", id);
		
		Bourse bourse = (Bourse) query.getSingleResult();
		
		return bourse;
	}
	
}
