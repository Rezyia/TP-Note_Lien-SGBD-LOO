package app;

import dao.CandidatureDAO;
import dao.EnseignantDAO;
import dao.EnseignementDAO;
import dao.EtudiantDAO;

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
		
		System.out.println(CandidatureDAO.getCandidatures());
		
		System.out.println(EtudiantDAO.getEtudiant(1));
		System.out.println("Fin du programme.");
	}
}