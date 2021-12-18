package vue;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import dao.CandidatureDAO;
import metier.Authentification;
import metier.Champs;
import metier.Enregistrement;
import metier.Evaluation;
import modele.Candidature;

public class MenuEnseignant {
	
	private MenuAccueil menuAcc;
	private String utilisateur;
	
	/**
	 * Constructeur
	 * @param menuAcc
	 * @param idEnseignant
	 */
	public MenuEnseignant(MenuAccueil menuAcc, Integer idEnseignant) {
		this.menuAcc = menuAcc;
		this.utilisateur = "";
		
		if (!Authentification.controlerEnseignant(this, idEnseignant)) {
			System.out.println("Vous n'êtes pas authentifié. Retour au menu...");
		}
		else {
			moteur(idEnseignant);
		}
	}
	
	
	/**
	 * 
	 * @param idEnseignant
	 */
	public void moteur(Integer idEnseignant) {
		String choix = "";
		do {
			choix = askAction();
			try {
				switch (Integer.valueOf(choix)) {
				case 1:
					evaluer(menuAcc.getScan(), idEnseignant, getUtilisateur());
					break;
				case 2:
					enregistrer(menuAcc.getScan(), idEnseignant, getUtilisateur());
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Retour au menu...");
			}
		} while (!choix.equals("q"));
		return;
	}
	
	
	public String askAction() {
		System.out.println("Que souhaitez-vous faire ?" + System.lineSeparator()
		+ "1: evaluer mes candidatures enregistrées" + System.lineSeparator()
		+ "2: enregistrer une nouvelle candidature" + System.lineSeparator()
		+ "q: retour à l'acceuil");
		System.out.print(getUtilisateur() + "> ");
		return getMenuAcc().getScan().nextLine();
	}

	
	/**
	 * 
	 * @param scan
	 * @param idResponsable
	 * @throws NumberFormatException
	 */
	public static void evaluer(Scanner scan, Integer idResponsable, String login) throws NumberFormatException {
		//chercher candidature selon id
		Integer idCandidature = null; Double note = null;
		System.out.println("Quelle candidature souhaitez-vous evaluer ? ");
		
		// Affichage des candidatures évaluables :
		List<Candidature> candidatures = CandidatureDAO.getCandidaturesByResponsable(idResponsable);
		Iterator<Candidature> ite = candidatures.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
		
		System.out.println("ID candidature : ");
		System.out.print(login+"> ");
		idCandidature = Integer.valueOf(scan.nextLine());
	
		System.out.println("Entrez la note (/20) :");
		System.out.print(login+"> ");
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

	
	/**
	 * 
	 * @param scan
	 * @param idResponsable
	 * @throws NumberFormatException
	 */
	public static void enregistrer(Scanner scan, Integer idResponsable, String login) throws NumberFormatException {
		//chercher candidature selon id
		Integer idCandidature = null; Double note = null;
		System.out.println("Candidatures disponibles : ");
		
		// Affichage des candidatures évaluables :
		List<Candidature> candidatures = CandidatureDAO.getCandidaturesDisponibles();
		Iterator<Candidature> ite = candidatures.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
		
		System.out.println("ID candidature :");
		System.out.print(login+"> ");
		idCandidature = Integer.valueOf(scan.nextLine());
		
		System.out.println("Champ (local ou erasmus) :");
		System.out.print(login+"> ");
		String champ = scan.nextLine();
		
		boolean executionOK = true;
		if ("local".startsWith(champ.toLowerCase()))
			executionOK = Enregistrement.updateCandidature(idCandidature, idResponsable, Champs.RESPONSABLE_LOCAL);
		else if ("erasmus".startsWith(champ.toLowerCase()))
			executionOK = Enregistrement.updateCandidature(idCandidature, idResponsable, Champs.RESPONSABLE_LOCAL);
		else {
			System.out.println("Erreur d'input, opération annulée.");
		}
		
		if (executionOK) System.out.println("L'enregistrement a bien été effectuée.");
	}
	
	
	/**
	 * 
	 * @return
	 */
	public MenuAccueil getMenuAcc() {
		return menuAcc;
	}

	/**
	 * 
	 * @param menuAcc
	 */
	public void setMenuAcc(MenuAccueil menuAcc) {
		this.menuAcc = menuAcc;
	}
	
	public String getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}
	
}
