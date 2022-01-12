package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.BDD;
import modele.Enseignant;

public class EnseignantDAO {
	
	/**
	 * 
	 * @param rs
	 * @param liste
	 * @throws SQLException
	 */
	private static void addEnseignant(ResultSet rs, List<Enseignant> liste) throws SQLException {
		String nom = rs.getString(2);
		String prenom = rs.getString(3);
		
		liste.add(new Enseignant(nom, prenom));
	}
	
	
	
	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static Enseignant askEnseignant(ResultSet rs) throws SQLException {
		String nom = rs.getString(2);
		String prenom = rs.getString(3);

		return new Enseignant(nom, prenom);
	}
	
	
	
	/**
	 * Retourne une liste d'objets Enseignant initialisés à partir des données récupérées par la BDD
	 * @return
	 */
	public static List<Enseignant> getEnseignants() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		List<Enseignant> listeEnseignants = new ArrayList<>();
		String sql = "SELECT * FROM Enseignant;";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				addEnseignant(rs, listeEnseignants);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return listeEnseignants;
	}
	
	
	
	/**
	 * Retourne un objets Enseignant associé à l'id passé en paramètre
	 * @param id
	 * @return
	 */
	public static Enseignant getEnseignantById(Integer id) {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		Enseignant ens = null;
		String sql = "SELECT * FROM Enseignant WHERE id=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				ens = askEnseignant(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return ens;
	}
	
}
