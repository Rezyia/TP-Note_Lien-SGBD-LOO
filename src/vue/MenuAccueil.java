package vue;

import java.util.Scanner;

public class MenuAccueil {
	
	private MenuEnseignant menuEns;
	private Scanner scan;

	
	/**
	 * Constructeur
	 */
	public MenuAccueil() {
		this.menuEns = null;
		this.scan = new Scanner(System.in);
		moteur();
	}
	
	
	/**
	 * 
	 */
	public void moteur() {
		boolean running = true;
		while (running) {
			try {
				switch (askUtilisateur()) {
				case 1:
					//
					break;
				case 2:
					setMenuEns(new MenuEnseignant(this, askEnseignant()));
					break;
				case 3:
					running = false;
					break;
				default:
					//
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
	/**
	 * 
	 * @return 1: étudiant, 2: enseignant, 3: quitter 
	 */
	public Integer askUtilisateur() {
		System.out.println("Quel type d'utilisateur êtes-vous ?" + System.lineSeparator()
		+ "1: etudiant" + System.lineSeparator()
		+ "2: enseignant" + System.lineSeparator()
		+ "3: quitter");
		System.out.print("> ");
		return Integer.valueOf(getScan().nextLine());
	}
	
	
	/**
	 * 
	 * @return Integer de l'id entré en input
	 * @throws NumberFormatException
	 */
	public Integer askEnseignant() throws NumberFormatException {
		System.out.println("Quel est votre identifiant ?");
		System.out.print("> ");
		return Integer.valueOf(getScan().nextLine());
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
	public Scanner getScan() {
		return scan;
	}

	/**
	 * 
	 * @param scan
	 */
	public void setScan(Scanner scan) {
		this.scan = scan;
	}
	
}
