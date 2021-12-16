package metier;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.BDD;
import dao.EnseignantDAO;
import modele.Candidature;
import modele.Enseignant;

public class Evaluation {

	public static String evalue(String idResponsable, String idCandidature) {
		String message = "Erreur blablabla";
		
		//chercher responsable selon id
		//confirmer nom prenom
		//chercher candidature selon id
		//appeler changeNote
		//appeler calculerScore si pertinent
		//adapter le message
		//retourner le message à la vue
		
		return message;
	}
	
	
	public static boolean changeNote(Candidature c, Enseignant resp, BigDecimal note) {
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
			pstmt.setBigDecimal(1, note);
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
