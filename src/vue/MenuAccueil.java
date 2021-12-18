package vue;

import java.util.Scanner;

public class MenuAccueil {
	
	private MenuEnseignant menuEns;
	private MenuEtudiant menuEtu;
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
		String choix = "";
		do {
			choix = askUtilisateur();
			try {
				switch (Integer.valueOf(choix)) {
				case 1:
					setMenuEtu(new MenuEtudiant(this, askID()));
					break;
				case 2:
					setMenuEns(new MenuEnseignant(this, askID()));
					break;
				}
			} catch (NumberFormatException e) {
				// pas de probl�me
			}
		} while (!choix.equals("q"));
		return;
	}
	
	
	/**
	 * 
	 * @return 1: �tudiant, 2: enseignant, q: quitter 
	 */
	public String askUtilisateur() {
		System.out.println("Quel type d'utilisateur �tes-vous ?" + System.lineSeparator()
		+ "1: etudiant" + System.lineSeparator()
		+ "2: enseignant" + System.lineSeparator()
		+ "q: quitter");
		System.out.print("> ");
		return getScan().nextLine();
	}
	
	
	/**
	 * 
	 * @return Integer de l'id entr� en input
	 * @throws NumberFormatException
	 */
	public Integer askID() throws NumberFormatException {
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
	
	
	public void setMenuEtu(MenuEtudiant menuEtu) {
		this.menuEtu = menuEtu;
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
