package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import app.App;
import modele.Plan;

public class PlanDAO {
	
	/**
	 * Ajoute un plan à la liste passée en paramètre 
	 * @param liste
	 */
	public static void addPlan(Plan p) throws SQLException {
		App.em.getTransaction().begin();
		App.em.persist(p);
		App.em.getTransaction().commit();
	}
		
	
	/**
	 * Retourne une liste d'objets Plan initialisés à partir des données récupérées par la BDD
	 * @return
	 */
	public static List<Plan> getPlans() {
		Query query = App.em.createQuery("from Plan");
		
		List<Plan> listePlans = query.getResultList();
		
		return listePlans;

	}
	
	
	
	/**
	 * Retourne un objets Plan associé à l'id passé en paramètre
	 * @param candidature_id
	 * @param enseignement_intitule
	 * @return
	 */
	public static Plan getPlanById(Integer candidature_id, String intitule) {
		Query query = App.em.createQuery("from Plan P where P.idCandidature = :c_id AND P.enseignement = :e_id"); 
		query.setParameter("c_id", candidature_id);
		query.setParameter("intitule", intitule);
		
		Plan ens = (Plan) query.getSingleResult();
		
		return ens;
	}
	
}
