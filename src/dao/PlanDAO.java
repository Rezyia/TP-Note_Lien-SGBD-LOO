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
	
	private static void addPlan(ResultSet rs, List<Plan> liste) throws SQLException {
		Candidature candidature = CandidatureDAO.getCandidatureById(rs.getInt(1));
		Enseignement enseignement = EnseignementDAO.getEnseignementById(rs.getInt(2));

		liste.add(new Plan(candidature, enseignement));
	}
	
	private static Plan askPlan(ResultSet rs) throws SQLException {
		Candidature candidature = CandidatureDAO.getCandidatureById(rs.getInt(1));
		Enseignement enseignement = EnseignementDAO.getEnseignementById(rs.getInt(2));

		return new Plan(candidature, enseignement);
	}
	
	
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
	
	
	public static Plan getPlanById(Integer idCandidature, Integer idEnseignement) {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		Plan pla = null;
		String sql = "SELECT * FROM Plan WHERE idCandidature=? and idEnseignement=?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idCandidature);
			ps.setInt(2, idEnseignement);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				pla = askPlan(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return pla;
	}
	
}
