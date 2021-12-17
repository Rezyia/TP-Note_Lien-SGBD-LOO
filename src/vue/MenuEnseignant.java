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
	
	/**
	 * Constructeur
	 * @param menuAcc
	 * @param idEnseignant
	 */
	public MenuEnseignant(MenuAccueil menuAcc, Integer idEnseignant) {
		this.menuAcc = menuAcc;
		
		if (!Authentification.controlerEnseignant(menuAcc.getScan(), idEnseignant)) {
			System.out.println("Vous n'�tes pas authentifi�. Retour au menu...");
		}
		
		moteur(idEnseignant);
	}
	
	
	/**
	 * 
	 * @param idEnseignant
	 */
	public void moteur(Integer idEnseignant) {
		String choix = "";
		do {
			System.out.println("Que souhaitez-vous faire ?" + System.lineSeparator()
					+ "1: evaluer mes candidatures enregistr�es" + System.lineSeparator()
					+ "2: enregistrer une nouvelle candidature" + System.lineSeparator()
					+ "q: retour � l'acceuil");
			System.out.print("> ");
			choix = menuAcc.getScan().nextLine();
			try {
				switch (Integer.valueOf(choix)) {
				case 1:
					evaluer(menuAcc.getScan(), idEnseignant);
				case 2:
					enregistrer(menuAcc.getScan(), idEnseignant);
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		} while (!choix.equals("q"));
		return;
	}

	
	/**
	 * 
	 * @param scan
	 * @param idResponsable
	 * @throws NumberFormatException
	 */
	public static void evaluer(Scanner scan, Integer idResponsable) throws NumberFormatException {
		//chercher candidature selon id
		Integer idCandidature = null; Double note = null;
		System.out.println("Quelle candidature souhaitez-vous evaluer ? ");
		
		// Affichage des candidatures �valuables :
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
			System.out.println("La note n'a pas pu �tre modifi�e..."
					+ "V�rifiez que vous cette candidature est bien enregistr�e parmi les v�tres.");
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
	public static void enregistrer(Scanner scan, Integer idResponsable) throws NumberFormatException {
		//chercher candidature selon id
		Integer idCandidature = null; Double note = null;
		System.out.println("Candidatures disponibles : ");
		
		// Affichage des candidatures �valuables :
		List<Candidature> candidatures = CandidatureDAO.getCandidaturesDisponibles();
		Iterator<Candidature> ite = candidatures.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
		
		System.out.print("ID candidature : ");
		idCandidature = Integer.valueOf(scan.nextLine());
		
		System.out.print("Champ (local ou erasmus) : ");
		String champ = scan.nextLine();
		
		if (champ.toLowerCase().equals("local") )
			Enregistrement.updateCandidature(idCandidature, idResponsable, Champs.RESPONSABLE_LOCAL);
		else if (champ.toLowerCase().equals("erasmus") )
			Enregistrement.updateCandidature(idCandidature, idResponsable, Champs.RESPONSABLE_LOCAL);
		else {
			System.out.println("Erreur d'input, op�ration annul�e.");
		}
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
	
}
