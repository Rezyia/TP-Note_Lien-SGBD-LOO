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
	
	private static void addEtudiant(ResultSet rs, List<Etudiant> liste) throws SQLException {
		Integer numero = rs.getInt(1);
		String nom = rs.getString(2);
		String prenom = rs.getString(3);
		Double moyDS = rs.getDouble(4);
		
		liste.add(new Etudiant(numero, nom, prenom, moyDS));
	}
	
	private static Etudiant askEtudiant(ResultSet rs) throws SQLException {
		Integer numero = rs.getInt(1);
		String nom = rs.getString(2);
		String prenom = rs.getString(3);
		Double moyDS = rs.getDouble(4);
		
		return new Etudiant(numero, nom, prenom, moyDS);
	}
	
	
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
	
	
	public static Etudiant getEtudiantById(Integer numero) {
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
				etu = askEtudiant(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return etu;
	}
	
}
