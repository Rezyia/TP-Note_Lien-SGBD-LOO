package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.BDD;
import modele.Bourse;
import modele.Enseignant;

public class BourseDAO {
	
	/**
	 * 
	 * @param rs
	 * @param liste
	 * @throws SQLException
	 */
	private static void addBourse(ResultSet rs, List<Bourse> liste) throws SQLException {
		Integer id = rs.getInt(1);
		Integer idRespLocal = rs.getInt(2);
		String destination = rs.getString(3);
		Integer nbPostes = rs.getInt(4);

		Enseignant respLocal = EnseignantDAO.getEnseignantById(idRespLocal);

		liste.add(new Bourse(id , respLocal, destination, nbPostes));
	}
	
	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static Bourse askBourse(ResultSet rs) throws SQLException {
		Integer id = rs.getInt(1);
		Integer idRespLocal = rs.getInt(2);
		String destination = rs.getString(3);
		Integer nbPostes = rs.getInt(4);

		Enseignant respLocal = EnseignantDAO.getEnseignantById(idRespLocal);

		return new Bourse(id , respLocal, destination, nbPostes);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static List<Bourse> getBourses() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		List<Bourse> listeBourses = new ArrayList<>();
		String sql = "SELECT * FROM Bourse;";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				addBourse(rs, listeBourses);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return listeBourses;
	}
	
	
	/**
	 * Retourne un objet Bourse initialisé à partir des données récupérées depuis la BDD.
	 * @param id
	 * @return
	 */
	public static Bourse getBourseById(Integer id) {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		Bourse bou = null;
		String sql = "SELECT * FROM Bourse WHERE id=?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				bou = askBourse(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return bou;
	}
	
}
