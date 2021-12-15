package app;

import dao.EnseignantDAO;
import dao.EnseignementDAO;
import dao.EtudiantDAO;
import modele.Bourse;

public class Application {
	
	
	public static void refreshLocal() {
		EtudiantDAO.getEtudiants();
		EnseignantDAO.getEnseignants();
		EnseignementDAO.getEnseignements();
	}
	
	public static void main(String [] args) {
		System.out.println("Début du programme...");
		
		// Drop les tables si elles existent déjà puis les recrée et insert des exemples dans la BDD
		BDD db = new BDD();
		System.out.println(EnseignantDAO.getEnseignants());
		System.out.println(EtudiantDAO.getEtudiants());
		
		System.out.println(EtudiantDAO.getEtudiant(1));
		System.out.println("Fin du programme.");
	}
}
