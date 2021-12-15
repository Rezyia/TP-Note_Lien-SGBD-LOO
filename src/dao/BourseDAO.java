package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.BDD;
import modele.Bourse;
import modele.Etudiant;

public class BourseDAO {
	
	public List<Bourse> getBourses() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		List<Bourse> liste = new ArrayList<>();
		String sql = "SELECT * FROM Bourse;";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Integer id = rs.getInt(1);
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
