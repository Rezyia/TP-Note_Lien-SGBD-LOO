package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.BDD;
import modele.Plan;
import modele.Candidature;
import modele.Enseignement;

public class PlanDAO {
	
	/**
	 * 
	 * @param rs
	 * @param liste
	 * @throws SQLException
	 */
	private static void addPlan(ResultSet rs, List<Plan> liste) throws SQLException {
		Candidature candidature = CandidatureDAO.getCandidatureById(rs.getInt(1));
		Enseignement enseignement = EnseignementDAO.getEnseignementByIntitule(rs.getString(2));

		liste.add(new Plan(candidature, enseignement));
	}
	
	
	
	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static Plan newPlan(ResultSet rs) throws SQLException {
		Candidature candidature = CandidatureDAO.getCandidatureById(rs.getInt(1));
		Enseignement enseignement = EnseignementDAO.getEnseignementByIntitule(rs.getString(2));

		return new Plan(candidature, enseignement);
	}
	
	
	
	/**
	 * Retourne une liste d'objets Plan initialisés à partir des données récupérées par la BDD
	 * @return
	 */
	public static List<Plan> getPlans() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		List<Plan> listePlans = new ArrayList<>();
		String sql = "SELECT * FROM Plan;";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				addPlan(rs, listePlans);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return listePlans;
	}
	
	
	
	/**
	 * Retourne un objets Plan associé à l'id passé en paramètre
	 * @param candidature_id
	 * @param enseignement_intitule
	 * @return
	 */
	public static Plan getPlanById(Integer candidature_id, String enseignement_intitule) {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		Plan pla = null;
		String sql = "SELECT * FROM Plan WHERE candidature_id=? and enseignement_intitule=?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, candidature_id);
			ps.setString(2, enseignement_intitule);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				pla = newPlan(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return pla;
	}
	
}
