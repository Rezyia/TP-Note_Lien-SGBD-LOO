package app;

import java.math.BigDecimal;

import dao.*;
import metier.*;

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
		
		// Appel vue principale ...
		
		System.out.println(CandidatureDAO.getCandidatureById(1));
		System.out.println(EnseignantDAO.getEnseignantById(1));
		System.out.println(EtudiantDAO.getEtudiants());
		System.out.println(PlanDAO.getPlans());
		
		
		Evaluation.changeNote(CandidatureDAO.getCandidatureById(1), EnseignantDAO.getEnseignantById(2), 10.25);
		Evaluation.changeNote(CandidatureDAO.getCandidatureById(2), EnseignantDAO.getEnseignantById(4), 16.33);
		Double score = Evaluation.calculerScore(CandidatureDAO.getCandidatureById(1));
		System.out.println("Score = " + score);
		System.out.println("Fin du programme.");
	}
}