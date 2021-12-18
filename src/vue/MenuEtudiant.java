package vue;

import java.util.Iterator;
import java.util.List;

import metier.Affichage;
import metier.Authentification;
import metier.Champs;
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
	
	
	public static void afficherCandidatures(Integer idEtudiant) {
		// Affichage des candidatures évaluables :
		List<Candidature> candidatures = Affichage.afficherCandidatures(idEtudiant, Champs.ETUDIANT);
		Iterator<Candidature> ite = candidatures.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
	}
	
	
	public static void afficherInfos(Integer idEtudiant) {
		System.out.println(Affichage.afficherEtudiant(idEtudiant));
	}


	public MenuAccueil getMenuAcc() {
		return menuAcc;
	}

	public void setMenuAcc(MenuAccueil menuAcc) {
		this.menuAcc = menuAcc;
	}
	
}
