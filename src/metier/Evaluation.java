package metier;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.BDD;
import dao.CandidatureDAO;
import dao.EnseignantDAO;
import modele.Candidature;
import modele.Enseignant;

public class Evaluation {

	public static String evalue(Integer idResponsable, Integer idCandidature) {
		String message = "Erreur blablabla";
		
		//chercher responsable selon id
		Enseignant resp = EnseignantDAO.getEnseignantById(idResponsable);
		
		//confirmer nom prenom
		if (resp.getNom().equals("")) ; // confirmation nom / prénom 
		
		//chercher candidature selon id
		Candidature candidature = CandidatureDAO.getCandidatureById(idCandidature);
		
		// Scanner
		Double note = 0.0, score = 0.0; // Valeur à mettre dans changeNote & score à afficher ensuite
		
		//appeler changeNote
		changeNote(candidature, resp, note);
		
		
		//appeler calculerScore si pertinent
		if (true) score = calculerScore(candidature); // <- qu'est-ce qui est pertinent ?
		
		//adapter le message
		message = "Le score de cette candidature est de " + score + "/20";
		
		//retourner le message à la vue
		return message;
	}
	
	
	public static boolean changeNote(Candidature c, Enseignant resp, Double note) {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		try {
			conn.setAutoCommit(false);
			String str = "";
			
			if (c.getRespErasmus().equals(resp)) // Si responsable Erasmus
				str = "UPDATE Candidature" 
						+ " SET noteErasmus = ?"
						+ " WHERE id = ?;";
			else if (c.getRespLocal().equals(resp)) // Si responsable Erasmus
				str = "UPDATE Candidature" 
						+ " SET noteLocal = ?"
						+ " WHERE id = ?;";
			else {
				conn.rollback();
				return false;
			}
			
			PreparedStatement pstmt = conn.prepareStatement(str);
			System.out.println(str);
			pstmt.setBigDecimal(1, BigDecimal.valueOf(note));
			pstmt.setInt(2, c.getId());
			pstmt.execute();
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	public static Double calculerScore(Candidature c) {
		Double score = 0.0;
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		String req = "SELECT moyDernierSemestre, noteLocal, noteErasmus "
				+ "FROM Candidature NATURAL JOIN Etudiant "
				+ "WHERE id = ?;";
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(req);
			pstmt.setInt(1, c.getId());

			ResultSet res = pstmt.executeQuery();
			res.next();
			
			Double noteDS = 0.0;
			Double noteRespLocal = 0.0;
			Double noteRespErasmus = 0.0;
			
			BigDecimal nDS = res.getBigDecimal(1);
			BigDecimal nRL = res.getBigDecimal(2);
			BigDecimal nRE = res.getBigDecimal(3);
			
			if (nDS != null) noteDS = nDS.doubleValue();
			if (nRL != null) noteRespLocal = nRL.doubleValue();
			if (nRE != null) noteRespErasmus = nRE.doubleValue();

			// Si Toutes les valeurs sont non nulles : 
			if (noteDS * noteRespLocal * noteRespErasmus != 0)
				score = (noteDS + noteRespLocal + noteRespErasmus) / 3;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return score;
	}
	
}
