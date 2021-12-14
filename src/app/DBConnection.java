package app;
import java.sql.*;
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
			//dbTableInit();
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
		String ctX = "CREATE TABLE IF NOTE EXISTS X ("
				+ ""
				+ ");"; 
		
		try {
			Statement s = connection.createStatement();
			s.executeUpdate(ctX);			
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