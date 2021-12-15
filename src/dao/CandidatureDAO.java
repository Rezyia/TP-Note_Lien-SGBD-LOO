package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.BDD;
import modele.Bourse;
import modele.Candidature;
import modele.Enseignant;
import modele.Etudiant;

public class CandidatureDAO {

	private static List<Candidature> listeCandidatures = null;
	
	
	public static List<Candidature> getCandidatures() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		if (listeCandidatures == null) // Si la liste n'a pas été initialisée
			listeCandidatures = new ArrayList<>();
		String sql = "SELECT * FROM Candidature";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Integer id = rs.getInt(1);
				Etudiant etudiant = EtudiantDAO.getEtudiant(rs.getInt(2));
				Bourse bourse = BourseDAO.getBourse(rs.getInt(3));
				Enseignant respLocal = EnseignantDAO.getEnseignant(rs.getInt(4));
				Enseignant respErasmus = EnseignantDAO.getEnseignant(rs.getInt(5));
				Double noteLocale = rs.getDouble(6);
				Double noteErasmus = rs.getDouble(7);
				
				listeCandidatures.add(new Candidature(id, etudiant, bourse, respLocal,
						respErasmus, noteLocale, noteErasmus));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeCandidatures;
	}
	
	
	public static Candidature getCandidature(Integer id) {
		if (listeCandidatures == null) getCandidatures();
		return (Candidature) ToolBox.getObject(listeCandidatures, id);
	}
	
}
