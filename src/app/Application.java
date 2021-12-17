package app;

import vue.*;

public class Application {
	
	public static void main(String [] args) {
		System.out.println("Début du programme...");
		
		// Drop les tables si elles existent déjà puis les recrée et insert des exemples dans la BDD
		// Commenter ou décommenter les fonctions dans le constructeur de la classe app/BDD
		//  pour garder les données entre 2 exécutions de cette application.
		BDD db = new BDD();
		
		// Appel vue principale ...
		MenuAccueil accueil = new MenuAccueil();
		
		System.out.println("Fin du programme.");
	}
}