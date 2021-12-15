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
import modele.Enseignant;

public class BourseDAO {
	
	private static List<Bourse> listeBourses = null;
	
	
	public static List<Bourse> getBourses() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		if (listeBourses == null) // Si la liste n'a pas �t� initialis�e
			listeBourses = new ArrayList<>();
		String sql = "SELECT * FROM Bourse";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Integer id = rs.getInt(1);
				Integer idRespLocal = rs.getInt(2);
				String destination = rs.getString(3);
				Integer nbPostes = rs.getInt(4);
				
				Enseignant respLocal = EnseignantDAO.getEnseignant(idRespLocal);
				
				listeBourses.add(new Bourse(id , respLocal, destination, nbPostes));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeBourses;
	}
	
	
	public static Bourse getBourse(Integer id) {
		if (listeBourses == null) getBourses();
		return (Bourse) ToolBox.getObject(listeBourses, id);
	}
	
}	
