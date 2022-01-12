package vue;

import java.util.Iterator;
import java.util.List;

import app.Application;
import metier.Affichage;
import metier.Authentification;
import metier.Champs;
import modele.Candidature;

public class MenuEtudiant {
	
	public MenuEtudiant(Integer idEtudiant) {
		if (!Authentification.controlerEtudiant(Application.scan, idEtudiant)) {
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
			choix = Application.scan.nextLine();
			try {
				switch (Integer.valueOf(choix)) {
				case 1:
					afficherInfos(idEtudiant);
					break;
				case 2:
					afficherCandidatures(idEtudiant);
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		} while (!choix.equals("q"));
		return;
	}
	
	
	public  void afficherCandidatures(Integer idEtudiant) {
		// Affichage des candidatures évaluables :
		List<Candidature> candidatures = Affichage.afficherCandidatures(idEtudiant, Champs.ETUDIANT);
		Iterator<Candidature> ite = candidatures.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
	}
	
	
	public  void afficherInfos(Integer idEtudiant) {
		System.out.println(Affichage.afficherEtudiant(idEtudiant));
	}
	
}
