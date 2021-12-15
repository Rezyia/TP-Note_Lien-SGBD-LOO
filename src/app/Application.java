package app;

import dao.EnseignantDAO;
import dao.EtudiantDAO;

public class Application {
	
	
	public static void main(String [] args) {
		System.out.println("Début du programme...");
		BDD db = new BDD();
		db.dbTuplesInit();
		System.out.println(EnseignantDAO.getEnseignants());
		System.out.println(EtudiantDAO.getEtudiants());
		System.out.println("Fin du programme.");
	}
}
