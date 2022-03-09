import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperarConexao();
		
		// \/ interface java.sql
		PreparedStatement stm = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
		// comando a partir da aplicação (retorna booleano):
		stm.execute();
		
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
