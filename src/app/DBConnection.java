package app;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class DBConnection {

	public static Connection connection;
	private static Properties properties;
	
	public static boolean connected = false;
	
	private static String user = "root";
	private static String pwd = "root";
	
	
	public DBConnection() {
		connected = connect();
		if (connected) {
			dbTableInit();
			dbTuplesInit();	
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
		String ctLivre = "if not exists create table Livre("
				+ "id int not null,"
				+ "titre varchar(40),"
				+ "auteur int,"
				+ "dateParution Date,"
				+ "primary key (id),"
				+ "foreign key (id) references Auteur(id);"; 
		
		String ctExemplaire = "if not exists create table Exemplaire("
				+ "id int not null,"
				+ "livre int,"
				+ "texte varchar(2048),"
				+ "primary key (id)),"
				+ "foreign key (livre) references Livre(id);";
		
		String ctAuteur = "if not exists create table Auteur("
				+ "id int not null,"
				+ "nom varchar(30),"
				+ "prenom varchar(30),"
				+ "primary key (id));";
		
		String ctAssocie = "if not exists create table Associe("
				+ "id int not null,"
				+ "nom varchar(30),"
				+ "prenom varchar(30),"
				+ "primary key (id));";
		
		String ctPret = "if not exists create table Pret("
				+ "id int not null,"
				+ "exemplaire int not null,"
				+ "associe int not null,"
				+ "dateEmprunt Date not null,"
				+ "dateRestitution Date,"
				+ "dureeMax int not null," // Durée max de l'emprunt en jours
				+ "primary key (id),"
				+ "foreign key (exemplaire) references Exemplaire(id),"
				+ "foreign key (associe) references Associe(id));";
		
		try {
			Statement s = connection.createStatement();
			s.executeUpdate(ctLivre);
			s.executeUpdate(ctExemplaire);
			s.executeUpdate(ctAuteur);
			s.executeUpdate(ctAssocie);
			s.executeUpdate(ctPret);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * DB tuples initializer
	 * @throws SQLException
	 */
	private void dbTuplesInit() {
		String iLivre = "insert into Livre values"
				+ "(1, 'Livre1', 1, " + Date.valueOf(LocalDate.now()) +"),"
				+ "(2, 'Livre2', 1, " + Date.valueOf(LocalDate.of(2000, 11, 22)) +"),"
				+ "(3, 'Le temps des tempêtes', 3, " + Date.valueOf(LocalDate.of(2021, 7, 16)) +");";
			
		String iExemplaire = "insert into Exemplaire values"
				+ "(1, 2, 'blablabla Livre 2'),"
				+ "(2, 2, 'blabla Livre 2'),"
				+ "(3, 1, 'blablabla Livre 1');";
		
		String iAuteur = "insert into Auteur values"
				+ "(1, 'Descartes', 'Rene'),"
				+ "(2, 'Aristote', null),"
				+ "(3, 'Mano', 'Aloe');";
		
		String iAssocie = "insert into Associe values"
				+ "(1, 'Tokoyami', 'Towa'),"
				+ "(2, 'Nakiri', 'Ayame'),"
				+ "(3, 'Motoaki', 'Tanigo');";
				
		try {
			Statement s = connection.createStatement();
			s.executeUpdate(iLivre);
			s.executeUpdate(iExemplaire);
			s.executeUpdate(iAuteur);
			s.executeUpdate(iAssocie);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}