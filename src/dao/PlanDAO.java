package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import app.BDD;
import modele.Plan;

public class PlanDAO {
	
	/**
	 * Ajoute un plan � la liste pass�e en param�tre 
	 * @param liste
	 */
	public static void addPlan(Plan p) throws SQLException {
		BDD.em.getTransaction().begin();
		BDD.em.persist(p);
		BDD.em.getTransaction().commit();
	}
		
	
	/**
	 * Retourne une liste d'objets Plan initialis�s � partir des donn�es r�cup�r�es par la BDD
	 * @return
	 */
	public static List<Plan> getPlans() {
		Query query = BDD.em.createQuery("from Plan");
		
		List<Plan> listePlans = query.getResultList();
		
		return listePlans;

	}
	
	
	
	/**
	 * Retourne un objets Plan associ� � l'id pass� en param�tre
	 * @param candidature_id
	 * @param enseignement_intitule
	 * @return
	 */
	public static Plan getPlanById(Integer candidature_id, String intitule) {
		Query query = BDD.em.createQuery("from Plan P where P.idCandidature = :c_id AND P.enseignement = :e_id"); 
		query.setParameter("c_id", candidature_id);
		query.setParameter("intitule", intitule);
		
		Plan ens = (Plan) query.getSingleResult();
		
		return ens;
	}
	
}
