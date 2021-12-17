package metier;

import java.util.Scanner;

import dao.EnseignantDAO;
import dao.EtudiantDAO;
import modele.Enseignant;
import modele.Etudiant;
import modele.Personne;

public abstract class Authentification {
	
	private static boolean decider(Scanner scan, Personne resp) {
		System.out.println("Etes-vous bien " + resp.getPrenom() + " " + resp.getNom() + " ? (oui / non) ");
		System.out.print("> ");
		String confirmation = scan.nextLine().toLowerCase();
		System.out.print(System.lineSeparator());
		if (confirmation.equals("o") || confirmation.equals("oui")) {
			return true;
		}
		return false;
	}
	
	
	public static boolean controlerEnseignant(Scanner scan, Integer id) {
		// chercher selon id
		Personne resp = EnseignantDAO.getEnseignantById(id);
		// confirmation nom / prénom
		return decider(scan, resp);
	}

	public static boolean controlerEtudiant(Scanner scan, Integer numero) {
		// chercher selon id
		Personne resp = EtudiantDAO.getEtudiantByNumero(numero);
		// confirmation nom / prénom
		return decider(scan, resp);
	}
	
}
