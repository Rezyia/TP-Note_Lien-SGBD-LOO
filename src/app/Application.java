package app;

import vue.*;

public class Application {
	
	public static void main(String [] args) {
		System.out.println("D�but du programme...");
		
		// Drop les tables si elles existent d�j� puis les recr�e et insert des exemples dans la BDD
		// Commenter ou d�commenter les fonctions dans le constructeur de la classe BDD
		//  pour garder les donn�es entre 2 ex�cutions de cette application.
		BDD db = new BDD();
		
		// Appel vue principale ...
		MenuAccueil accueil = new MenuAccueil();
		
		/*System.out.println(CandidatureDAO.getCandidatureById(1));
		System.out.println(EnseignantDAO.getEnseignantById(1));
		System.out.println(EtudiantDAO.getEtudiants());
		System.out.println(PlanDAO.getPlans());
		
		
		Evaluation.changeNote(1, 2, 10.25);
		Evaluation.changeNote(2, 4, 16.33);
		Double score = Evaluation.calculerScore(1);
		System.out.println("Score = " + score);*/
		System.out.println("Fin du programme.");
	}
}