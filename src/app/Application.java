package app;

import java.math.BigDecimal;

import dao.*;

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
		System.out.println(EtudiantDAO.getEtudiants());
		//System.out.println(PlanDAO.getPlans());
		//System.out.println(System.lineSeparator());
		//System.out.println(EnseignantDAO.getEnseignant(1));
		
		ToolBox.changeNote(CandidatureDAO.getCandidature(1), EnseignantDAO.getEnseignant(2), BigDecimal.valueOf(10.25));
		ToolBox.changeNote(CandidatureDAO.getCandidature(2), EnseignantDAO.getEnseignant(4), BigDecimal.valueOf(16.33));
		Double score = ToolBox.calculerScore(CandidatureDAO.getCandidature(1));
		System.out.println("Score = " + score);
		System.out.println("Fin du programme.");
	}
}