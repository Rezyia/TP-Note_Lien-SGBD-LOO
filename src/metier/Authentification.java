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
	 * @param scan Scanner utilisé
	 * @param personne Personne à connecter
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
	 * Fonction de contrôle de connexion pour les enseignants
	 * @param menu Menu utilisé
	 * @param id Numéro de l'enseignant
	 * @return
	 */
	public static boolean controlerEnseignant(MenuEnseignant menu, Integer id) {
		// chercher selon id
		Personne ens = EnseignantDAO.getEnseignantById(id);
		if (ens == null) {
			System.out.println("Erreur : l'identifiant ne correspond à aucun étudiant.");
			return false;
		}
		if (decider(App.scan, ens)) {
			// confirmation nom / prénom
			menu.setUtilisateur(ens.getPrenom() + " " + ens.getNom());
			return true;
		}
		return false;
	}

	
	/**
	 * Fonction de contrôle de connexion pour les étudiants
	 * @param menu Menu utilisé
	 * @param id Numéro de l'étudiant
	 * @return
	 */
	public static boolean controlerEtudiant(MenuEtudiant menu, Integer id) {
		// chercher selon id
		Personne etu = EtudiantDAO.getEtudiantById(id);
		if (decider(App.scan, etu)) {
			// confirmation nom / prénom
			menu.setUtilisateur(etu.getPrenom() + " " + etu.getNom());
			return true;
		}
		return false;
	}
	
}
