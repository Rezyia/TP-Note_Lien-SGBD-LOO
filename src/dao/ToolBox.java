package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	
	public static void calculerScore() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		
	}
	
}
