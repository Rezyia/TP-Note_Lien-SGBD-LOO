package vue;

import java.util.Scanner;

import javax.persistence.*;

import app.App;

public class MenuApp {
	
	private MenuEnseignant menuEns;
	private MenuEtudiant menuEtu;
	

	
	/**
	 * Constructeur
	 */
	public MenuApp() {
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
			Integer choixNum = 0;
			try {
				choixNum = Integer.valueOf(choix);
			} catch (NumberFormatException e) {
				System.out.println();
			}
			try {
				switch (choixNum) {
				case 1:
					setMenuEtu(new MenuEtudiant(askID()));
					break;
				case 2:
					setMenuEns(new MenuEnseignant(askID()));
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
	 * 
	 * @return 1: étudiant, 2: enseignant, q: quitter 
	 */
	public String askUtilisateur() {
		System.out.println("Quel type d'utilisateur êtes-vous ?" + System.lineSeparator()
		+ "1: etudiant" + System.lineSeparator()
		+ "2: enseignant" + System.lineSeparator()
		+ "q: quitter");
		System.out.print("> ");
		return App.scan.nextLine();
	}
	
	
	/**
	 * 
	 * @return Integer de l'id entré en input
	 * @throws NumberFormatException
	 */
	public Integer askID() throws NumberFormatException {
		System.out.println("Quel est votre identifiant ?");
		System.out.print("> ");
		return Integer.valueOf(App.scan.nextLine());
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
