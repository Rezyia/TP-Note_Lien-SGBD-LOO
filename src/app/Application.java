package app;

import vue.*;

public class Application {
	
	public static void main(String [] args) {
		System.out.println("D�but du programme...");
		
		// Drop les tables si elles existent d�j� puis les recr�e et insert des exemples dans la BDD
		// Commenter ou d�commenter les fonctions dans le constructeur de la classe app/BDD
		//  pour garder les donn�es entre 2 ex�cutions de cette application.
		BDD db = new BDD();
		
		// Appel vue principale ...
		MenuAccueil accueil = new MenuAccueil();
		
		System.out.println("Fin du programme.");
	}
}