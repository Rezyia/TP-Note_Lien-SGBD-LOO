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
import modele.Candidature;
import modele.Enseignant;
import modele.Etudiant;

public class CandidatureDAO {
	
	private static void addCandidature(ResultSet rs, List<Candidature> liste) throws SQLException {
		Integer id = rs.getInt(1);
		Etudiant etudiant = EtudiantDAO.getEtudiantByNumero(rs.getInt(2));
		Bourse bourse = BourseDAO.getBourseById(rs.getInt(3));
		Enseignant respLocal = EnseignantDAO.getEnseignantById(rs.getInt(4));
		Enseignant respErasmus = EnseignantDAO.getEnseignantById(rs.getInt(5));
		Double noteLocale = rs.getDouble(6);
		Double noteErasmus = rs.getDouble(7);
		
		liste.add(new Candidature(id, etudiant, bourse, respLocal,
				respErasmus, noteLocale, noteErasmus));
	}
	
	private static Candidature askCandidature(ResultSet rs) throws SQLException {
		Integer id = rs.getInt(1);
		Etudiant etudiant = EtudiantDAO.getEtudiantByNumero(rs.getInt(2));
		Bourse bourse = BourseDAO.getBourseById(rs.getInt(3));
		Enseignant respLocal = EnseignantDAO.getEnseignantById(rs.getInt(4));
		Enseignant respErasmus = EnseignantDAO.getEnseignantById(rs.getInt(5));
		Double noteLocale = rs.getDouble(6);
		Double noteErasmus = rs.getDouble(7);

		return new Candidature(id, etudiant, bourse, respLocal,
				respErasmus, noteLocale, noteErasmus);
	}
	
	
	public static List<Candidature> getCandidatures() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		List<Candidature> listeCandidatures = new ArrayList<>();
		String sql = "SELECT * FROM Candidature;";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				addCandidature(rs, listeCandidatures);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return listeCandidatures;
	}
	
	
	public static Candidature getCandidatureById(Integer id) {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		Candidature can = null;
		String sql = "SELECT * FROM Candidature WHERE id=?;";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				can = askCandidature(rs);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return can;
	}
	
}
