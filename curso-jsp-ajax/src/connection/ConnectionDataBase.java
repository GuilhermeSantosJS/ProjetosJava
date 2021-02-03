package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDataBase {

	
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "123789";
	private static String user = "postgres";
	private static Connection connection = null;
	
	
	static {
		conectar();
	}
	
	public ConnectionDataBase() {
		conectar();
	}
	
	private static void  conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
			}
			
		}catch(Exception exception) {
			throw new RuntimeException("Erro ao conectar com o banco de dados" + exception.getMessage());
		}
		
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
