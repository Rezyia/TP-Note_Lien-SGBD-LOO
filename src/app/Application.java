package app;

public class Application {
	
	
	public static void main(String [] args) {
		System.out.println("D�but du programme...");
		DataBase db = new DataBase();
		db.dbTuplesInit();
		System.out.println("Fin du programme.");
	}
}
