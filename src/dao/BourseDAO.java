package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import app.App;
import modele.Bourse;

public class BourseDAO {
	
	/**
	 * Ajoute une bourse à la BDD
	 * @param liste 
	 */
	public static void addBourse(Bourse b) throws SQLException {
		App.em.getTransaction().begin();
		App.em.persist(b);
		App.em.getTransaction().commit();

	}
	
	
	/**
	 * Retourne la liste des bourses
	 */
	public static List<Bourse> getBourses() {
		Query query = App.em.createQuery("from Bourse");
		
		List<Bourse> listeBourses = query.getResultList();
		
		return listeBourses;
	}
	
	
	/**
	 * Retourne un objet Bourse initialisé à partir des données récupérées depuis la BDD.
	 * @param id
	 * @return Bourse
	 */
	public static Bourse getBourseById(Integer id) {
		Query query = App.em.createQuery("from Bourse B where B.id = :b_id"); 
		query.setParameter("b_id", id);
		
		Bourse bourse = (Bourse) query.getSingleResult();
		
		return bourse;
	}
	
}
