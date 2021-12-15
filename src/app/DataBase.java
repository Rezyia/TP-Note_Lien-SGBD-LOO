package app;
import java.sql.*;
import java.util.Properties;

public class DataBase {

	public static Connection connection;
	private static Properties properties;
	
	public static boolean connected = false;
	
	private static String user = "root";
	private static String pwd = "root";
	
	
	public DataBase() {
		connected = connect();
		if (connected) {
			dbDropTables();
			dbTableInit();
			//dbTuplesInit();
		}
	}
	
	
	/**
	 * Connects connection to database
	 * @implNote connection is set to null if couldn't connect to the server.
	 * @return true if connection successful, false otherwise.
	 * @throws SQLException
	 */
	public static boolean connect() {
		try {
			System.out.println("Connecting to database...");
		
			properties = new Properties();
			properties.setProperty("user", user);
			properties.setProperty("password", pwd);
					
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tpnote", properties);
	
			System.out.println("Connection successful.");
			return true;
			
		} catch (SQLException eSQL) {
			System.out.println("Couldn't connect to database :");
			eSQL.printStackTrace();
			return false;
		}
	}
	

	private void dbDropTables() {
		String dropTables = "DROP TABLE IF EXISTS Plan, Candidature, Bourse, Enseignement, Etudiant, Enseignant CASCADE;";
		
		try {
			Statement stmt = connection.createStatement();
			stmt.execute(dropTables);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Create tables in the DB
	 * @throws SQLException
	 */
	private void dbTableInit() {		
		String ctEtudiant = "CREATE TABLE IF NOT EXISTS Etudiant ("
				+ "numero INT NOT NULL AUTO_INCREMENT,"
				+ "nom VARCHAR(30) NOT NULL,"
				+ "prenom VARCHAR(30) NOT NULL,"
				+ "moyDernierSemestre DECIMAL(4,2),"
				+ "PRIMARY KEY (numero)"
				+ ");";
		
		String ctEnseignant = "CREATE TABLE IF NOT EXISTS Enseignant ("
				+ "id INT NOT NULL AUTO_INCREMENT,"
				+ "nom VARCHAR(30) NOT NULL,"
				+ "prenom VARCHAR(30) NOT NULL,"
				+ "PRIMARY KEY (id)"
				+ ");";
		
		String ctEnseignement = "CREATE TABLE IF NOT EXISTS Enseignement("
				+ "id INT NOT NULL AUTO_INCREMENT,"
				+ "intitule VARCHAR(50),"
				+ "credits INT DEFAULT 0,"
				+ "volumeHeures INT,"
				+ "PRIMARY KEY (id)"
				+ ");";
		
		String ctBourse = "CREATE TABLE IF NOT EXISTS Bourse("
				+ "id INT NOT NULL AUTO_INCREMENT,"
				+ "idResponsable INT NOT NULL,"
				+ "destination VARCHAR(50),"
				+ "nbPostes INT,"
				+ "PRIMARY KEY (id),"
				+ "FOREIGN KEY (idResponsable) REFERENCES Enseignant(id)"
				+ ");";
		
		String ctCandidature = "CREATE TABLE IF NOT EXISTS Candidature ("
				+ "id INT NOT NULL AUTO_INCREMENT,"
				+ "etudiant INT NOT NULL,"
				+ "bourse INT NOT NULL,"
				+ "respLocal int NOT NULL,"
				+ "respErasmus int NOT NULL,"
				+ "noteLocal DOUBLE(2,2),"
				+ "noteErasmus DOUBLE(2,2),"
				+ "PRIMARY KEY (id),"
				+ "FOREIGN KEY (etudiant) references Etudiant(numero),"
				+ "FOREIGN KEY (bourse) references Bourse(id),"
				+ "FOREIGN KEY (respLocal) references Enseignant(id),"
				+ "FOREIGN KEY (respErasmus) references Enseignant(id)"
				+ ");";
		
		String ctPlan = "CREATE TABLE IF NOT EXISTS Plan("
				+ "idCandidature INT NOT NULL,"
				+ "idEnseignement INT NOT NULL,"
				+ "PRIMARY KEY (idCandidature, idEnseignement),"
				+ "FOREIGN KEY (idCandidature) REFERENCES Candidature(id),"
				+ "FOREIGN KEY (idEnseignement) REFERENCES Enseignement(id)"
				+ ");";
		
		try {
			Statement s = connection.createStatement();
			s.executeUpdate(ctEtudiant);
			s.executeUpdate(ctEnseignant);			
			s.executeUpdate(ctEnseignement);
			s.executeUpdate(ctBourse);			
			s.executeUpdate(ctCandidature);
			s.executeUpdate(ctPlan);		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Insert tuples in the DB tables 
	 * @throws SQLException
	 */
	public void dbTuplesInit() {
		String iEtudiant = "INSERT INTO Etudiant(nom, prenom, moyDernierSemestre) VALUES"
				+ "('Boulanger', 'Léo', 15.66),"	// id = 1
				+ "('Boisseau', 'Théo', 20.00);";	// id = 2
		
		String iEnseignant = "INSERT INTO Enseignant(nom, prenom) VALUES"
				+ "('Conte', 'Donatello'),"		// id=1
				+ "('Ragot', 'Nicolas'),"		// id=2
				+ "('Monmarché', 'Nicolas'),"	// id=3
				+ "('Motoaki', 'Tanigo');";		// id=4
		
		String iEnseignement = "INSERT INTO Enseignement(intitule, credits, volumeHeures) VALUES"
				+ "('Informatique', 4, 2000),"									// id = 1
				+ "('Électronique et génie électrique', 2, 1900),"				// id = 2
				+ "('Génie de l aménagement et de l environnement', 1, 1800),"	// id = 3
				+ "('Mécanique et conception des systèmes', 3, 2000);";			// id = 4
		
		String iBourse = "INSERT INTO Bourse(idResponsable, destination, nbPostes) VALUES"
				+ "(2, 'Montréal', 29),"	// id = 1
				+ "(3, 'Akita', 42);";		// id = 2
		
		
		String iCandidature = "INSERT INTO Candidature(etudiant, bourse, respLocal, respErasmus) VALUES" 
				+ "();"; // Fait manuellement lors de l'exécution du programme 
		
		String iPlan = "INSERT INTO  VALUES"
				+ "();"; // Fait manuellement lors de l'exécution du programme
		


		try {
			Statement s = connection.createStatement();
			s.executeUpdate(iEtudiant);
			s.executeUpdate(iEnseignant);
			s.executeUpdate(iEnseignement);
			s.executeUpdate(iBourse);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}