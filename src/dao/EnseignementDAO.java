package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.BDD;
import modele.Enseignement;

public class EnseignementDAO {

	private static List<Enseignement> listeEnseignement = null;
	
	public static List<Enseignement> getEnseignements() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		if (listeEnseignement == null) // Si la liste n'a pas été initialisée
			listeEnseignement = new ArrayList<>();
		
		String sql = "SELECT * FROM Enseignement";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Integer id = rs.getInt(1);
				String intitule = rs.getString(2);
				Integer credits = rs.getInt(3);
				Integer volumeHeures = rs.getInt(4);
				
				listeEnseignement.add(new Enseignement(id, intitule, credits, volumeHeures));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeEnseignement;
	}
	
	
}
