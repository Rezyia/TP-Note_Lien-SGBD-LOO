package vue;

import java.util.Iterator;
import java.util.List;

import app.App;
import dao.CandidatureDAO;
import metier.Affichage;
import metier.Authentification;
import metier.Champs;
import metier.Evaluation;
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
		+ "1: afficher mes informations" + System.lineSeparator()
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
		//chercher candidature selon id
		System.out.println("Candidatures : ");
		
		// Affichage des candidatures :
		List<Candidature> candidatures = CandidatureDAO.getCandidaturesByEtudiant(idEtudiant);
		Iterator<Candidature> ite = candidatures.iterator();
		if (!ite.hasNext()) {
			System.out.println("Aucune");
		}
		while (ite.hasNext()) {
			Candidature can = ite.next();
			Double score = Evaluation.calculerScore(can);
			String scoreStr = "Au moins une note n'a pas été évaluée";
			if (score != null) {
				scoreStr = score.toString().substring(0,5) + "/20";
			}
			System.out.println(can + "\n\tScore = " + scoreStr);
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
