package metier;

import java.util.Scanner;

import app.Application;
import dao.EnseignantDAO;
import dao.EtudiantDAO;
import modele.Personne;
import vue.MenuEnseignant;

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
		System.out.print(System.lineSeparator());
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
		menu.setUtilisateur(resp.getPrenom() + " " + resp.getNom());
		// confirmation nom / prénom
		return decider(Application.scan, resp);
	}

	
	/**
	 * 
	 * @param scan
	 * @param numero
	 * @return
	 */
	public static boolean controlerEtudiant(Scanner scan, Integer numero) {
		// chercher selon id
		Personne resp = EtudiantDAO.getEtudiantByNumero(numero);
		// confirmation nom / prénom
		return decider(scan, resp);
	}
	
}
