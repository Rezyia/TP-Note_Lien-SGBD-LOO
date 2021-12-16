package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import app.BDD;
import modele.TableWithId;
import modele.Enseignant;
import modele.Candidature;

public abstract class ToolBox {

	public static Object getObject(Object liste, Integer id) {
		TableWithId elem = null;
		boolean fin = false;
		
		Iterator<TableWithId> ite = ((List<TableWithId>) liste).iterator();
		while (ite.hasNext() && !fin) {
			elem = ite.next();
			if (elem.getId() == id) fin = true;
		}
		if (fin == false) elem = null;
		
		return elem;
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
