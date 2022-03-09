import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		
		String nome = "Mouse";
		String descricao = "Mouse sem fio";
		
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.recuperarConexao();
		
		PreparedStatement stm = 
				connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
		
		stm.setString(1, nome); // (posicao, valor)
		stm.setString(2, descricao);
		
		stm.execute();
		
		ResultSet rs = stm.getGeneratedKeys();
		
		while(rs.next()) {
			Integer id = rs.getInt(1);
			System.out.println("O id criado foi: " + id);
		}
	}
}

/**
 * O PreparedStatement protege o banco de dados, deixando o código menos vulnerável.
 */
