package vue;


import app.App;

public class MenuApp {
	
	private MenuEnseignant menuEns;
	private MenuEtudiant menuEtu;
	

	public MenuApp() {
		this.menuEns = null;
		this.menuEtu = null;
		moteur();
	}
	
	
	/**
	 * Moteur du menu de l'application
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
	 * Menu de type de connexion
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
	 * Affiche le menu de connexion par ID
	 * @return Integer de l'id entré en input
	 * @throws NumberFormatException
	 */
	public Integer askID() throws NumberFormatException {
		System.out.println("Quel est votre identifiant ?");
		System.out.print("> ");
		return Integer.valueOf(App.scan.nextLine());
	}

	
	public MenuEnseignant getMenuEns() {
		return menuEns;
	}

	public void setMenuEns(MenuEnseignant menuEns) {
		this.menuEns = menuEns;
	}
	
	public MenuEtudiant getMenuEtu() {
		return menuEtu;
	}
	
	public void setMenuEtu(MenuEtudiant menuEtu) {
		this.menuEtu = menuEtu;
	}
	
}
