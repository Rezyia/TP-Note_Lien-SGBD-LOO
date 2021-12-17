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

public abstract class Evaluation {
	
	/**
	 * Change la note d'une candidature li� au responsable pass� en param�tre 
	 * @param idCandidature
	 * @param idResponsable
	 * @param note
	 * @return
	 */
	public static boolean changeNote(Integer idCandidature, Integer idResponsable, Double note) {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		try {
			conn.setAutoCommit(false);
			Candidature c = CandidatureDAO.getCandidatureById(idCandidature);
			Enseignant resp = EnseignantDAO.getEnseignantById(idResponsable);
			String str = "";

			Enseignant era = c.getRespErasmus();
			Enseignant local = c.getRespLocal();
			if (era != null && era.getId() == resp.getId()) { // Si responsable Erasmus
				str = "UPDATE Candidature" 
						+ " SET noteErasmus=?"
						+ " WHERE id=?;";
			}
			else if (local != null && local.getId() == resp.getId()) { // Si responsable Local
				str = "UPDATE Candidature" 
						+ " SET noteLocal=?"
						+ " WHERE id=?;";
			}
			else {
				conn.rollback();
				return false;
			}
			
			PreparedStatement pstmt = conn.prepareStatement(str);
			pstmt.setBigDecimal(1, BigDecimal.valueOf(note));
			pstmt.setInt(2, c.getId());
			pstmt.executeUpdate();
			
			conn.commit();
			conn.setAutoCommit(true);
			return true;
		} catch (NullPointerException e) {
			System.out.println("Candidature ou enseignant non trouv� : " + e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 * Calcule le score d'une candidature pass�e en param�tre
	 * @param idCandidature
	 * @return null si le score n'a pas pu �tre calcul�, un Double de la moyenne des 3 notes sinon
	 */
	public static Double calculerScore(Integer idCandidature) {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		Candidature c = CandidatureDAO.getCandidatureById(idCandidature);
		String req = "SELECT moyDernierSemestre, noteLocal, noteErasmus "
				+ "FROM Candidature NATURAL JOIN Etudiant "
				+ "WHERE id = ?;";
		Double score = null;
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(req);
			pstmt.setInt(1, c.getId());

			ResultSet res = pstmt.executeQuery();
			res.next();
			
			BigDecimal nDS = res.getBigDecimal(1);
			BigDecimal nRL = res.getBigDecimal(2);
			BigDecimal nRE = res.getBigDecimal(3);
			
			if (nDS != null && nRL != null && nRE != null) {
				Double noteDS = nDS.doubleValue();
				Double noteRespLocal = nRL.doubleValue();
				Double noteRespErasmus = nRE.doubleValue();
				score = (noteDS + noteRespLocal + noteRespErasmus) / 3;
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return score;
	}
	
}
