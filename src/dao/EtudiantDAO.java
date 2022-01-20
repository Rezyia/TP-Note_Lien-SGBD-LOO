package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import app.BDD;
import modele.Etudiant;

public class EtudiantDAO {
	
	/**
	 * Ajoute un etudiant � la BDD 
	 * @param etu
	 */
	public static void addEtudiant(Etudiant etu) throws SQLException {
		BDD.em.getTransaction().begin();
		BDD.em.persist(etu);
		BDD.em.getTransaction().commit();
	}
		
	
	/**
	 * Retourne une liste d'objets Etudiant initialis�s � partir des donn�es r�cup�r�es par la BDD
	 * @return
	 */
	public static List<Etudiant> getEtudiants() {
		Query query = BDD.em.createQuery("from Etudiant");
		
		List<Etudiant> listeEtudiants = query.getResultList();
		
		return listeEtudiants;
	}
	

	
	/**
	 * Retourne un objets Etudiant associ� � l'id pass� en param�tre
	 * @param id
	 * @return
	 */
	public static Etudiant getEtudiantById(Integer id) {
		Query query = BDD.em.createQuery("from Etudiant E where E.id = :etu_id"); 
		query.setParameter("etu_id", id);
		
		Etudiant etu = (Etudiant) query.getSingleResult();
		
		return etu;

	}
	
}
