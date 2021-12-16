package vue;

import java.util.Scanner;

import dao.CandidatureDAO;
import metier.Authentification;
import metier.Evaluation;

public class MenuEnseignant {
	
	private MenuAcceuil menuAcc;
	
	public MenuEnseignant(MenuAcceuil menuAcc, Integer idEnseignant) {
		this.menuAcc = menuAcc;
		
		if (!Authentification.controlerEnseignant(menuAcc.getScan(), idEnseignant)) {
			System.out.println("Vous n'êtes pas authentifié. Retour au menu...");
		}
		
		moteur(idEnseignant);
	}
	
	
	public void moteur(Integer idEnseignant) {
		String choix = "";
		do {
			System.out.println("Que souhaitez-vous faire ?" + System.lineSeparator()
					+ "1: evaluer mes candidatures enregistrées" + System.lineSeparator()
					+ "2: enregistrer une nouvelle candidature" + System.lineSeparator()
					+ "q: retour à l'acceuil");
			choix = menuAcc.getScan().nextLine();
			try {
				switch (Integer.valueOf(choix)) {
				case 1:
					evaluer(menuAcc.getScan(), idEnseignant);
				case 2:
					//
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		} while (!choix.equals("q"));
		return;
	}

	
	public static void evaluer(Scanner scan, Integer idResponsable) throws NumberFormatException {
		//chercher candidature selon id
		Integer idCandidature = null; Double note = null;
		System.out.print("Quelle candidature souhaitez-vous evaluer ? ");
			idCandidature = Integer.valueOf(scan.nextLine());
		
			System.out.print("Entrez la note (/20) : ");
			note = Double.valueOf(scan.nextLine());
			
			//appeler changeNote
			if (!Evaluation.changeNote(idCandidature, idResponsable, note)) {
				System.out.println("La note n'a pas pu être modifiée..."
						+ "Vérifiez que vous cette candidature est bien enregistrée parmi les vôtres.");
			}
			
			//appeler calculerScore
			Double score = Evaluation.calculerScore(idCandidature);
			System.out.println("Score : " + score + System.lineSeparator());
	}


	public MenuAcceuil getMenuAcc() {
		return menuAcc;
	}

	public void setMenuAcc(MenuAcceuil menuAcc) {
		this.menuAcc = menuAcc;
	}
	
}
