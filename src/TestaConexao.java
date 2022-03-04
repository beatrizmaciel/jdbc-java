import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost/Loja_Virtual?useTimezone=true&serverTimezone=UTC", "root", "9153*Mysql");
	
	connection.close();
	}

}
