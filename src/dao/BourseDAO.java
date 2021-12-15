package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.BDD;
import modele.Bourse;
import modele.Etudiant;

public class BourseDAO {
	
	/*
	public List<Bourse> getBourses() {
		if (!BDD.isConnected()) BDD.connect();
		if (!BDD.isConnected()) return null;
		
		Connection conn = BDD.getConnection();
		
		List<Bourse> liste = new ArrayList<>();
		String sql = "SELECT * FROM Bourse;";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Integer id = rs.getInt(1);
				Integer idResponsable = rs.getInt(2);
				String destination = rs.getString(3);
				Integer nbPoste = rs.getInt(4);
				
				
				List<Enseignant> ens = EnseignantDAO.getEnseignants();
				Enseignant e = null;
				for (e : ens)
				
				liste.add(new Bourse(id, idResponsable, destination, nbPoste));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return liste;
		
	}
	*/
	

	
}
