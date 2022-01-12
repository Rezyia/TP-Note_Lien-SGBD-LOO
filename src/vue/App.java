package vue;

import java.util.Scanner;

import javax.persistence.*;

import app.Application;

public class App {
	
	private MenuEnseignant menuEns;
	private MenuEtudiant menuEtu;
	

	
	/**
	 * Constructeur
	 */
	public App() {
		this.menuEns = null;
		this.menuEtu = null;
		moteur();
	}
	
	
	/**
	 * 
	 */
	public void moteur() {
		String choix = "";
		do {
			choix = askUtilisateur();
			try {
				switch (Integer.valueOf(choix)) {
				case 1:
					setMenuEtu(new MenuEtudiant(askID()));
					break;
				case 2:
					setMenuEns(new MenuEnseignant(askID()));
					break;
				}
			} catch (NumberFormatException e) {
				// pas de problème
			}
		} while (!choix.equals("q"));
		return;
	}
	
	
	/**
	 * 
	 * @return 1: étudiant, 2: enseignant, q: quitter 
	 */
	public String askUtilisateur() {
		System.out.println("Quel type d'utilisateur êtes-vous ?" + System.lineSeparator()
		+ "1: etudiant" + System.lineSeparator()
		+ "2: enseignant" + System.lineSeparator()
		+ "q: quitter");
		System.out.print("> ");
		return Application.scan.nextLine();
	}
	
	
	/**
	 * 
	 * @return Integer de l'id entré en input
	 * @throws NumberFormatException
	 */
	public Integer askID() throws NumberFormatException {
		System.out.println("Quel est votre identifiant ?");
		System.out.print("> ");
		return Integer.valueOf(Application.scan.nextLine());
	}

	
	/**
	 * 
	 * @return
	 */
	public MenuEnseignant getMenuEns() {
		return menuEns;
	}

	/**
	 * 
	 * @param menuEns
	 */
	public void setMenuEns(MenuEnseignant menuEns) {
		this.menuEns = menuEns;
	}
	
	/**
	 * 
	 * @return
	 */
	public MenuEtudiant getMenuEtu() {
		return menuEtu;
	}
	
	public void setMenuEtu(MenuEtudiant menuEtu) {
		this.menuEtu = menuEtu;
	}
	
}
