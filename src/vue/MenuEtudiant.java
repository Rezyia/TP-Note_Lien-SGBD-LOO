package vue;

import java.util.Iterator;
import java.util.List;

import app.App;
import metier.Affichage;
import metier.Authentification;
import metier.Champs;
import modele.Candidature;

public class MenuEtudiant {
	
	private String utilisateur;
	
	
	public MenuEtudiant(Integer idEtudiant) {
		this.utilisateur = "";
		
		if (!Authentification.controlerEtudiant(this, idEtudiant)) {
			System.out.println("Vous n'êtes pas authentifié. Retour au menu...");
		} else {
			moteur(idEtudiant);
		}
	}
	
	
	/**
	 * Moteur du menu des étudiants
	 * @param idEtudiant
	 */
	public void moteur(Integer idEtudiant) {
		String choix = "";
		do {
			choix = askAction();
			Integer choixNum = 0;
			try {
				choixNum = Integer.valueOf(choix);
			} catch (NumberFormatException e) {
				System.out.println("Retour au menu...");
				System.out.println();
			}
			try {
				switch (choixNum) {
				case 1:
					afficherInfos(idEtudiant);
					break;
				case 2:
					afficherCandidatures(idEtudiant);
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				System.out.println("Annulation...");
			}
		} while (!choix.equals("q"));
		return;
	}
	
	
	/**
	 * Menu d'actions des étudiants
	 * @return
	 */
	public String askAction() {
		System.out.println("Que souhaitez-vous faire ?" + System.lineSeparator()
		+ "1: Afficher mes informations" + System.lineSeparator()
		+ "2: afficher mes candidatures" + System.lineSeparator()
		+ "q: retour à l'acceuil");
		System.out.print(getUtilisateur() + "> ");
		return App.scan.nextLine();
	}
	
	
	/**
	 * Affiche la liste des candidatures associées à l'étudiant connecté
	 * @param idEtudiant
	 */
	public  void afficherCandidatures(Integer idEtudiant) {
		// Affichage des candidatures évaluables :
		List<Candidature> candidatures = Affichage.afficherCandidatures(idEtudiant, Champs.ETUDIANT);
		Iterator<Candidature> ite = candidatures.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
	}
	
	
	/**
	 * Affiche les informations de l'étudiant connecté
	 * @param idEtudiant
	 */
	public  void afficherInfos(Integer idEtudiant) {
		System.out.println(Affichage.afficherEtudiant(idEtudiant));
	}
	
	
	public String getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}
	
}
