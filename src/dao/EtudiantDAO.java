package dao;

import java.util.List;

import app.BDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modele.Etudiant;

public class EtudiantDAO {
	
	public List<Etudiant> getEtudiants() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		List<Etudiant> liste = new ArrayList<>();
		String sql = "SELECT * FROM Etudiant";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Integer numero = rs.getInt(1);
				String nom = rs.getString(2);
				String prenom = rs.getString(3);
				Double moyDS = rs.getDouble(4);
				
				liste.add(new Etudiant(numero, nom, prenom, moyDS));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return liste;
	}
	
}
