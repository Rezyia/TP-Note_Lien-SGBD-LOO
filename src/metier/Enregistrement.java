package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.BDD;

public abstract class Enregistrement {

	
	/**
	 * 
	 * @param c
	 * @param ensId
	 * @param champ
	 * @return
	 */
	public static boolean updateCandidature(Integer candidatureId, Integer ensId, Champs champ) {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		try {
			String sql = "";
			
			if (champ == Champs.RESPONSABLE_LOCAL) {
				sql = "UPDATE Candidature set respLocal=? WHERE id=?";
			} else if (champ == Champs.RESPONSABLE_ERASMUS) {
				sql = "UPDATE Candidature set respErasmus=? WHERE id=?";				
			} else return false;
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ensId);
			pstmt.setInt(2, candidatureId);
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
