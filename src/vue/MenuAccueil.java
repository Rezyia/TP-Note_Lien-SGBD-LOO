package vue;

import java.util.Scanner;

public class MenuAccueil {
	
	private MenuEtudiant menuEns;
	private Scanner scan;

	public MenuAccueil() {
		this.menuEns = null;
		this.scan = new Scanner(System.in);
		moteur();
	}
	
	
	public void moteur() {
		boolean running = true;
		while (running) {
			try {
				switch (askUtilisateur()) {
				case 1:
					//
					break;
				case 2:
					setMenuEns(new MenuEtudiant(this, askEnseignant()));
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
	
	public Integer askUtilisateur() {
		System.out.println("Quel type d'utilisateur êtes-vous ?" + System.lineSeparator()
		+ "1: etudiant" + System.lineSeparator()
		+ "2: enseignant" + System.lineSeparator()
		+ "3: quitter");
		return Integer.valueOf(getScan().nextLine());
	}
	
	public Integer askEnseignant() throws NumberFormatException {
		System.out.println("Quel est votre identifiant ?");
		return Integer.valueOf(getScan().nextLine());
	}

	
	public MenuEtudiant getMenuEns() {
		return menuEns;
	}

	public void setMenuEns(MenuEtudiant menuEns) {
		this.menuEns = menuEns;
	}

	public Scanner getScan() {
		return scan;
	}

	public void setScan(Scanner scan) {
		this.scan = scan;
	}
	
}
