package vue;

import java.util.Iterator;
import java.util.List;

import app.App;
import dao.CandidatureDAO;
import metier.Affichage;
import metier.Authentification;
import metier.Champs;
import metier.Enregistrement;
import metier.Evaluation;
import modele.Candidature;

public class MenuEnseignant {
	
	private String utilisateur;
	

	public MenuEnseignant(Integer idEnseignant) {
		this.utilisateur = "";
		
		if (!Authentification.controlerEnseignant(this, idEnseignant)) {
			System.out.println("Vous n'êtes pas authentifié. Retour au menu...");
		}
		else {
			moteur(idEnseignant);
		}
	}
	
	
	/**
	 * Moteur du menu des enseignants
	 * @param idEnseignant
	 */
	public void moteur(Integer idEnseignant) {
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
					evaluer(idEnseignant, getUtilisateur());
					break;
				case 2:
					enregistrer(idEnseignant, getUtilisateur());
					break;
				case 3:
					afficher(idEnseignant);
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Annulation...");
			}
		} while (!choix.equals("q"));
		return;
	}
	
	/**
	 * Menu d'actions des enseignants
	 * @return
	 */
	public String askAction() {
		System.out.println("Que souhaitez-vous faire ?" + System.lineSeparator()
		+ "1: evaluer mes candidatures enregistrées" + System.lineSeparator()
		+ "2: enregistrer une nouvelle candidature" + System.lineSeparator()
		+ "3: afficher mes candidatures" + System.lineSeparator()
		+ "q: retour à l'acceuil");
		System.out.print(getUtilisateur() + "> ");
		return App.scan.nextLine();
	}

	
	/**
	 * Evalue une candidature parmi celles associées à l'enseignant connecté
	 * @param idResponsable
	 * @param utilisateur
	 * @throws NumberFormatException
	 */
	public void evaluer(Integer idResponsable, String utilisateur) throws NumberFormatException {
		//chercher candidature selon id
		Integer idCandidature = null; Double note = null;
		System.out.println("Quelle candidature souhaitez-vous evaluer ? ");
		
		// Affichage des candidatures évaluables :
		List<Candidature> candidatures = CandidatureDAO.getCandidaturesByResponsable(idResponsable);
		Iterator<Candidature> ite = candidatures.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
		
		System.out.println("Selectionner l'ID de la candidature à évaluer : ");
		System.out.print(utilisateur+"> ");
		idCandidature = Integer.valueOf(App.scan.nextLine());
	
		System.out.println("Entrez la note (/20) :");
		System.out.print(utilisateur+"> ");
		note = Double.valueOf(App.scan.nextLine());
		
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
	 * Enregiste l'enseignant connecté à l'une des candidatures disponibles 
	 * @param idResponsable
	 * @param utilisateur
	 * @throws Exception
	 */
	public  void enregistrer(Integer idResponsable, String utilisateur) throws Exception {
		//chercher candidature selon id
		Integer idCandidature = null;
		System.out.println("Candidatures disponibles : ");
		
		// Affichage des candidatures évaluables :
		List<Candidature> candidatures = CandidatureDAO.getCandidaturesDisponibles();
		Iterator<Candidature> ite = candidatures.iterator();
		while (ite.hasNext()) {
			System.out.println(ite.next());
		}
		
		System.out.println("Selectionner l'ID de la candidature à enregistrer : ");
		System.out.print(utilisateur+"> ");
		idCandidature = Integer.valueOf(App.scan.nextLine());
		
		boolean candidatureTrouvee = false;
		Iterator<Candidature> itecheck1 = candidatures.iterator();
		while (itecheck1.hasNext() && !candidatureTrouvee) {
			if (itecheck1.next().getId() == idCandidature) {
				candidatureTrouvee = true;
			}
		}
		if (!candidatureTrouvee) {
			throw new Exception("ID non reconnu");
		}
			
		System.out.println("Champ (local ou erasmus) :");
		System.out.print(utilisateur+"> ");
		String champ = App.scan.nextLine();
		
		boolean executionOK = true;
		if ("local".startsWith(champ.toLowerCase()))
			executionOK = Enregistrement.updateCandidature(idCandidature, idResponsable, Champs.RESPONSABLE_LOCAL);
		else if ("erasmus".startsWith(champ.toLowerCase()))
			executionOK = Enregistrement.updateCandidature(idCandidature, idResponsable, Champs.RESPONSABLE_ERASMUS);
		else {
			throw new Exception("Champ non reconnu");
		}
		
		if (executionOK) {
			System.out.println("L'enregistrement a bien été effectuée.");
		}
		return;
	}
	
	
	/**
	 * Affiche la liste des candidatures associées à l'enseignant connecté
	 * @param idResponsable
	 */
	public  void afficher(Integer idResponsable) {
		//chercher candidature selon id
		System.out.println("Candidatures enregistrées : ");
		
		// Affichage des candidatures évaluables :
		List<Candidature> candidatures = Affichage.afficherCandidatures(idResponsable, Champs.ENSEIGNANT);
		Iterator<Candidature> ite = candidatures.iterator();
		if (!ite.hasNext()) {
			System.out.println("Aucune");
		}
		while (ite.hasNext()) {
			Candidature can = ite.next();
			Double score = Evaluation.calculerScore(can.getId());
			String scoreStr = "Au moins une note n'a pas été évaluée";
			if (score != null && score.toString().length() >= 5) {
				scoreStr = score.toString().substring(0,5) + "/20";
			}
			System.out.print(can + "\n\tScore = " + scoreStr);
			System.out.println();
		}
	}
	
	
	public String getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}
	
}
