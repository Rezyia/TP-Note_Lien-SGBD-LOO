package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import app.App;
import modele.Etudiant;

public class EtudiantDAO {
	
	/**
	 * Ajoute un etudiant à la BDD 
	 * @param etu
	 */
	public static void addEtudiant(Etudiant etu) throws SQLException {
		App.em.getTransaction().begin();
		App.em.persist(etu);
		App.em.getTransaction().commit();
	}
		
	
	/**
	 * Retourne une liste d'objets Etudiant initialisés à partir des données récupérées par la BDD
	 * @return
	 */
	public static List<Etudiant> getEtudiants() {
		Query query = App.em.createQuery("from Etudiant");
		
		List<Etudiant> listeEtudiants = query.getResultList();
		
		return listeEtudiants;
	}
	

	
	/**
	 * Retourne un objets Etudiant associé à l'id passé en paramètre
	 * @param id
	 * @return
	 */
	public static Etudiant getEtudiantById(Integer id) {
		Query query = App.em.createQuery("from Etudiant E where E.id = :etu_id"); 
		query.setParameter("etu_id", id);
		
		Etudiant etu = (Etudiant) query.getSingleResult();
		
		return etu;

	}
	
}
