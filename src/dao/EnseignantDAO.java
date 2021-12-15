package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.BDD;
import modele.Enseignant;

public class EnseignantDAO {
	
	private static List<Enseignant> listeEnseignant = null;
	
	
	public static List<Enseignant> getEnseignants() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		if (listeEnseignant == null) // Si la liste n'a pas été initialisée
			listeEnseignant = new ArrayList<>();
		String sql = "SELECT * FROM Enseignant";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Integer numero = rs.getInt(1);
				String nom = rs.getString(2);
				String prenom = rs.getString(3);
				
				listeEnseignant.add(new Enseignant(numero, nom, prenom));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeEnseignant;
	}
	
}
