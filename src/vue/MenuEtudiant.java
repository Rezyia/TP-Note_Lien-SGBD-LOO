package vue;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import dao.CandidatureDAO;
import metier.Authentification;
import metier.Evaluation;
import modele.Candidature;

public class MenuEtudiant {
	
	private MenuAccueil menuAcc;
	
	public MenuEtudiant(MenuAccueil menuAcc, Integer idEtudiant) {
		this.menuAcc = menuAcc;
		
		if (!Authentification.controlerEtudiant(menuAcc.getScan(), idEtudiant)) {
			System.out.println("Vous n'êtes pas authentifié. Retour au menu...");
		}
		
		moteur(idEtudiant);
	}
	
	
	public void moteur(Integer idEtudiant) {
		String choix = "";
		do {
			System.out.println("Que souhaitez-vous faire ?" + System.lineSeparator()
					+ "1: Afficher mes informations" + System.lineSeparator()
					+ "2: Afficher mes candidature" + System.lineSeparator()
					+ "q: retour à l'acceuil");
			choix = menuAcc.getScan().nextLine();
			try {
				switch (Integer.valueOf(choix)) {
				case 1:
					evaluer(menuAcc.getScan(), idEtudiant);
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
		System.out.println("Quelle candidature souhaitez-vous evaluer ? ");
		
		// Affichage des candidatures évaluables :
		List<Candidature> candidatures = CandidatureDAO.getCandidaturesByResponsable(idResponsable);
		Iterator<Candidature> ite = candidatures.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
		
		System.out.print("ID candidature : ");
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


	public MenuAccueil getMenuAcc() {
		return menuAcc;
	}

	public void setMenuAcc(MenuAccueil menuAcc) {
		this.menuAcc = menuAcc;
	}
	
}
