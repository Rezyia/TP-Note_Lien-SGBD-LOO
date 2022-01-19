package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.BDD;
import modele.Etudiant;

public class EtudiantDAO {
	
	/**
	 * 
	 * @param rs
	 * @param liste
	 * @throws SQLException
	 */
	private static void addEtudiant(ResultSet rs, List<Etudiant> liste) throws SQLException {
		Integer id = rs.getInt(1);
		String nom = rs.getString(2);
		String prenom = rs.getString(3);
		Double moyDS = rs.getDouble(4);
		
		liste.add(new Etudiant(id, nom, prenom, moyDS));
	}
	
	
	
	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static Etudiant newEtudiant(ResultSet rs) throws SQLException {
		Integer id = rs.getInt(1);
		String nom = rs.getString(2);
		String prenom = rs.getString(3);
		Double moyDS = rs.getDouble(4);
		
		return new Etudiant(id, nom, prenom, moyDS);
	}
	
	
	
	/**
	 * Retourne une liste d'objets Etudiant initialisés à partir des données récupérées par la BDD
	 * @return
	 */
	public static List<Etudiant> getEtudiants() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		List<Etudiant> listeEtudiants = new ArrayList<>();
		String sql = "SELECT * FROM Etudiant;";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				addEtudiant(rs, listeEtudiants);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return listeEtudiants;
	}
	

	
	/**
	 * Retourne un objets Etudiant associé à l'id passé en paramètre
	 * @param id
	 * @return
	 */
	public static Etudiant getEtudiantById(Integer id) {
		return getEtudiantByNumero(id);
	}
	
	public static Etudiant getEtudiantByNumero(Integer numero) {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		Etudiant etu = null;
		String sql = "SELECT * FROM Etudiant WHERE numero=?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, numero);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				etu = newEtudiant(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return etu;
	}
	
}
