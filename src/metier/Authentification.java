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
	 * @param resp Personne � connecter
	 * @return
	 */
	private static boolean decider(Scanner scan, Personne resp) {
		System.out.println("Etes-vous bien " + resp.getPrenom() + " " + resp.getNom() + " ? (oui / non) ");
		System.out.print("> ");
		String confirmation = scan.nextLine().toLowerCase();
		if ("oui".startsWith(confirmation)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Fonction de contr�le de connexion pour les enseignants
	 * @param scan Scanner utilis�
	 * @param id Num�ro de l'enseignant
	 * @return
	 */
	public static boolean controlerEnseignant(MenuEnseignant menu, Integer id) {
		// chercher selon id
		Personne resp = EnseignantDAO.getEnseignantById(id);
		if (decider(App.scan, resp)) {
			// confirmation nom / pr�nom
			menu.setUtilisateur(resp.getPrenom() + " " + resp.getNom());
			return true;
		}
		return false;
	}

	
	/**
	 * Fonction de contr�le de connexion pour les �tudiants
	 * @param scan Scanner utilis�
	 * @param id Num�ro de l'�tudiant
	 * @return
	 */
	public static boolean controlerEtudiant(MenuEtudiant menu, Integer id) {
		// chercher selon id
		Personne resp = EtudiantDAO.getEtudiantById(id);
		if (decider(App.scan, resp)) {
			// confirmation nom / pr�nom
			menu.setUtilisateur(resp.getPrenom() + " " + resp.getNom());
			return true;
		}
		return false;
	}
	
}
