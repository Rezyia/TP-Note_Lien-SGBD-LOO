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
	

	
	/**
	 * DB tables initializer
	 * @throws SQLException
	 */
	private void dbTableInit() {		
		String ctEtudiant = "CREATE TABLE IF NOT EXISTS Etudiant ("
				+ "numero INT NOT NULL AUTO_INCREMENT,"
				+ "nom VARCHAR(30) NOT NULL,"
				+ "prenom VARCHAR(30) NOT NULL,"
				+ "moyDernierSemestre DOUBLE(2,2),"
				+ "PRIMARY KEY (numero)"
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
		
		String ctBourse = "CREATE TABLE IF NOT EXISTS Bourse("
				+ "id INT NOT NULL AUTO_INCREMENT,"
				+ "idResponsable INT NOT NULL,"
				+ "destination VARCHAR(50),"
				+ "nbPostes INT,"
				+ "PRIMARY KEY (id),"
				+ "FOREIGN KEY (idResponsable) REFERENCES Enseignant(id)"
				+ ");";
		
		String ctEnseignant = "CREATE TABLE IF NOT EXISTS Enseignant ("
				+ "id INT NOT NULL AUTO_INCREMENT,"
				+ "nom VARCHAR(30) NOT NULL,"
				+ "prenom VARCHAR(30) NOT NULL,"
				+ "PRIMARY KEY (id)"
				+ ");";
		
		
		String ctEnseignement = "CREATE TABLE IF NOT EXISTS Enseignement("
				+ "id INT NOT NULL AUTO_INCREMENT,"
				+ "intitule VARCHAR(30),"
				+ "credits INT DEFAULT 0,"
				+ "volumeHeures INT,"
				+ "PRIMARY KEY (id)"
				+ ");";
		
		
		try {
			Statement s = connection.createStatement();
			s.executeUpdate(ctEtudiant);
			s.executeUpdate(ctEnseignant);			
			s.executeUpdate(ctEnseignement);
			s.executeUpdate(ctBourse);			
			s.executeUpdate(ctPlan);		
			s.executeUpdate(ctCandidature);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * DB tuples initializer
	 * @throws SQLException
	 */
	private void dbTuplesInit() {
		String iX = "INSERT INTO X VALUES"
				+ "()";

		try {
			Statement s = connection.createStatement();
			s.executeUpdate(iX);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}