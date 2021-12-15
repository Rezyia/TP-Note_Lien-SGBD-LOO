package dao;

import java.util.List;

import app.BDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import modele.Etudiant;

public class EtudiantDAO {
	
	private static List<Etudiant> listeEtudiant = null;
	
	
	public static List<Etudiant> getEtudiants() {
		if (!BDD.isConnected()) BDD.connect();
		Connection conn = BDD.getConnection();
		
		if (!BDD.isConnected()) {
			return null;
		}
		
		if (listeEtudiant == null) // Si la liste n'a pas été initialisée
			listeEtudiant = new ArrayList<>();
		String sql = "SELECT * FROM Etudiant";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Integer numero = rs.getInt(1);
				String nom = rs.getString(2);
				String prenom = rs.getString(3);
				Double moyDS = rs.getDouble(4);
				
				listeEtudiant.add(new Etudiant(numero, nom, prenom, moyDS));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listeEtudiant;
	}
	
	public static Etudiant getEtudiant(Integer numero) {
		if (listeEtudiant == null) getEtudiants();
		Etudiant etu = null;
		boolean fin = false;
		
		Iterator<Etudiant> ite = listeEtudiant.iterator();
		while (ite.hasNext() && !fin) {
			etu = ite.next();
			if (etu.getNumero() == numero) fin = true;
		}
		if (fin == false) etu = null;
		
		return etu;
	}
	
}
