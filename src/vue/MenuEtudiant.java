package vue;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import dao.CandidatureDAO;
import metier.Affichage;
import metier.Authentification;
import metier.Champs;
import metier.Evaluation;
import modele.Candidature;

public class MenuEtudiant {
	
	private MenuAccueil menuAcc;
	
	public MenuEtudiant(MenuAccueil menuAcc, Integer idEtudiant) {
		this.menuAcc = menuAcc;
		
		if (!Authentification.controlerEtudiant(menuAcc.getScan(), idEtudiant)) {
			System.out.println("Vous n'êtes pas authentifié. Retour au menu...");
		} else {
			moteur(idEtudiant);
		}
	}
	
	
	public void moteur(Integer idEtudiant) {
		String choix = "";
		do {
			System.out.println("Que souhaitez-vous faire ?" + System.lineSeparator()
					+ "1: Afficher mes informations" + System.lineSeparator()
					+ "2: afficher mes candidatures" + System.lineSeparator()
					+ "q: retour à l'acceuil");
			choix = menuAcc.getScan().nextLine();
			try {
				switch (Integer.valueOf(choix)) {
				case 1:
					break;
				case 2:
					Affichage.afficherCandidatures(idEtudiant, Champs.ETUDIANT);
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		} while (!choix.equals("q"));
		return;
	}


	public MenuAccueil getMenuAcc() {
		return menuAcc;
	}

	public void setMenuAcc(MenuAccueil menuAcc) {
		this.menuAcc = menuAcc;
	}
	
}
