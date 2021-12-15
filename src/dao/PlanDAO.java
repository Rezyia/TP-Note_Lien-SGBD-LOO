package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.BDD;
import modele.Candidature;
import modele.Enseignement;
import modele.Plan;

public class PlanDAO {

	private static List<Plan> listePlans = null;
	
	
	public static List<Plan> getPlans() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		if (listePlans == null) // Si la liste n'a pas été initialisée
			listePlans = new ArrayList<>();
		String sql = "SELECT * FROM Plan";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Candidature candidature = CandidatureDAO.getCandidature(rs.getInt(1));
				Enseignement enseignement = EnseignementDAO.getEnseignement(rs.getInt(2));
				
				listePlans.add(new Plan(candidature, enseignement));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listePlans;
	}
	
	
	public static Plan getEnseignement(Integer idCandidature, Integer idEnseignement) {
		if (listePlans == null) getPlans();
		Plan pl = null;
		boolean fin = false;
		
		Iterator<Plan> ite = listePlans.iterator();
		while (ite.hasNext() && !fin) {
			pl = ite.next();
			if (pl.getCandidature().getId() == idCandidature && pl.getEnseignement().getId() == idEnseignement) {
				fin = true;
			}
		}
		if (fin == false) pl = null;
		
		return pl;
	}
	
}
