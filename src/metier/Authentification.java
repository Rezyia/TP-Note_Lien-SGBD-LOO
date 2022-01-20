package metier;

import java.util.Scanner;

import app.App;
import dao.EnseignantDAO;
import dao.EtudiantDAO;
import modele.Personne;
import vue.MenuEnseignant;
import vue.MenuEtudiant;

public abstract class Authentification {
	
	/**
	 * Confirme la connexion
	 * @param scan Scanner utilis�
	 * @param personne Personne � connecter
	 * @return
	 */
	private static boolean decider(Scanner scan, Personne personne) {
		System.out.println("Etes-vous bien " + personne.getPrenom() + " " + personne.getNom() + " ? (oui / non) ");
		System.out.print("> ");
		String confirmation = scan.nextLine().toLowerCase();
		if ("oui".startsWith(confirmation)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Fonction de contr�le de connexion pour les enseignants
	 * @param menu Menu utilis�
	 * @param id Num�ro de l'enseignant
	 * @return
	 */
	public static boolean controlerEnseignant(MenuEnseignant menu, Integer id) {
		// chercher selon id
		Personne ens = EnseignantDAO.getEnseignantById(id);
		if (ens == null) {
			System.out.println("Erreur : l'identifiant ne correspond � aucun �tudiant.");
			return false;
		}
		if (decider(App.scan, ens)) {
			// confirmation nom / pr�nom
			menu.setUtilisateur(ens.getPrenom() + " " + ens.getNom());
			return true;
		}
		return false;
	}

	
	/**
	 * Fonction de contr�le de connexion pour les �tudiants
	 * @param menu Menu utilis�
	 * @param id Num�ro de l'�tudiant
	 * @return
	 */
	public static boolean controlerEtudiant(MenuEtudiant menu, Integer id) {
		// chercher selon id
		Personne etu = EtudiantDAO.getEtudiantById(id);
		if (decider(App.scan, etu)) {
			// confirmation nom / pr�nom
			menu.setUtilisateur(etu.getPrenom() + " " + etu.getNom());
			return true;
		}
		return false;
	}
	
}
