package app;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import vue.*;

public class App {

	public static Scanner scan;
	
	public static void main(String [] args) {
		System.out.println("D?but du programme...");
		
		EntityManagerFactory em_factory = Persistence.createEntityManagerFactory("perUnitTPnote"); 
		BDD.em = em_factory.createEntityManager();
		App.scan = new Scanner(System.in);
		
		
		// Drop les tables si elles existent d?j? puis les recr?e et insert des exemples dans la BDD
		// Commenter ou d?commenter les fonctions dans le constructeur de la classe app/BDD
		//  pour garder les donn?es entre 2 ex?cutions de cette application.
		BDD db = new BDD();
		
		
		// Appel vue principale ...
		MenuApp accueil = new MenuApp();
		
		
		App.scan.close();
		BDD.em.close();
		em_factory.close();
		
		System.out.println("Fin du programme.");
	}
}