package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.BDD;
import modele.Enseignement;

public class EnseignementDAO {
	
	private static void addEnseignement(ResultSet rs, List<Enseignement> liste) throws SQLException {
		Integer id = rs.getInt(1);
		String intitule = rs.getString(2);
		Integer credits = rs.getInt(3);
		Integer volumeHeures = rs.getInt(4);
		
		liste.add(new Enseignement(id, intitule, credits, volumeHeures));
	}
	
	private static Enseignement askEnseignement(ResultSet rs) throws SQLException {
		Integer id = rs.getInt(1);
		String intitule = rs.getString(2);
		Integer credits = rs.getInt(3);
		Integer volumeHeures = rs.getInt(4);
		
		return new Enseignement(id, intitule, credits, volumeHeures);
	}
	
	
	public static List<Enseignement> getEnseignements() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		List<Enseignement> listeEnseignements = new ArrayList<>();
		String sql = "SELECT * FROM Enseignement;";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				addEnseignement(rs, listeEnseignements);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return listeEnseignements;
	}
	
	
	public static Enseignement getEnseignementById(Integer id) {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		Enseignement ens = null;
		String sql = "SELECT * FROM Enseignement WHERE id=?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				ens = askEnseignement(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return ens;
	}
	
}
