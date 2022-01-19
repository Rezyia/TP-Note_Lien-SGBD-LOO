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
	 * 
	 * @param scan
	 * @param resp
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
	 * 
	 * @param scan
	 * @param id
	 * @return
	 */
	public static boolean controlerEnseignant(MenuEnseignant menu, Integer id) {
		// chercher selon id
		Personne resp = EnseignantDAO.getEnseignantById(id);
		if (decider(App.scan, resp)) {
			// confirmation nom / prénom
			menu.setUtilisateur(resp.getPrenom() + " " + resp.getNom());
			return true;
		}
		return false;
	}

	
	/**
	 * 
	 * @param scan
	 * @param numero
	 * @return
	 */
	public static boolean controlerEtudiant(MenuEtudiant menu, Integer numero) {
		// chercher selon id
		Personne resp = EtudiantDAO.getEtudiantByNumero(numero);
		if (decider(App.scan, resp)) {
			// confirmation nom / prénom
			menu.setUtilisateur(resp.getPrenom() + " " + resp.getNom());
			return true;
		}
		return false;
	}
	
}
