package vue;

import java.util.Scanner;

public class MenuAcceuil {
	
	private MenuEnseignant menuEns;
	private Scanner scan;

	public MenuAcceuil() {
		this.menuEns = null;
		this.scan = new Scanner(System.in);
		moteur();
	}
	
	
	public void moteur() {
		while (true) {
			try {
				switch (askUtilisateur()) {
				case 1:
					//
					break;
				case 2:
					setMenuEns(new MenuEnseignant(this, askEnseignant()));
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
		+ "2: enseignant");
		return Integer.valueOf(getScan().nextLine());
	}
	
	public Integer askEnseignant() throws NumberFormatException {
		System.out.println("Quel est votre identifiant ?");
		return Integer.valueOf(getScan().nextLine());
	}

	
	public MenuEnseignant getMenuEns() {
		return menuEns;
	}

	public void setMenuEns(MenuEnseignant menuEns) {
		this.menuEns = menuEns;
	}

	public Scanner getScan() {
		return scan;
	}

	public void setScan(Scanner scan) {
		this.scan = scan;
	}
	
}
