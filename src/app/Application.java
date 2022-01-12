package app;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import vue.*;

public class Application {

	public static EntityManager em;
	public static Scanner scan;
	
	public static void main(String [] args) {
		System.out.println("Début du programme...");
		
		EntityManagerFactory em_factory = Persistence.createEntityManagerFactory("perUnitTPnote"); 
		Application.em = em_factory.createEntityManager();
		Application.scan = new Scanner(System.in);
		
		// Drop les tables si elles existent déjà puis les recrée et insert des exemples dans la BDD
		// Commenter ou décommenter les fonctions dans le constructeur de la classe app/BDD
		//  pour garder les données entre 2 exécutions de cette application.
		//BDD db = new BDD();
		
		// Appel vue principale ...
		//App accueil = new App();
		
		System.out.println("Fin du programme.");
	}
}