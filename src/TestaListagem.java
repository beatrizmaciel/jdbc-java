import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost/Loja_Virtual?useTimezone=true&serverTimezone=UTC", "root", "555!Root");
		
		// \/ interface java.sql
		Statement stm = connection.createStatement();
		// comando a partir da aplicação (retorna booleano):
		stm.execute("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
		
		// \/ interface java.sql
		ResultSet rst = stm.getResultSet();
		
		// .next() é um método do java.sql
		while(rst.next()) {
			//						\/ nome na tabela mysql
			Integer id = rst.getInt("ID");
			System.out.println(id);
			String nome = rst.getString("NOME");
			System.out.println(nome);
			String descricao = rst.getString("DESCRICAO");
			System.out.println(descricao);
		}
		
	connection.close();
	}

}
