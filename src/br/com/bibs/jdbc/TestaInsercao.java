package br.com.bibs.jdbc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.recuperarConexao();
		
		Statement stm = connection.createStatement();
		//			\/ inserir cláusula (insert, select, from, where, etc). Aspas simples para a String do SQL.
		stm.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('Mouse', 'Mouse sem fio')"
				, Statement.RETURN_GENERATED_KEYS); // para pegar o id do produto
		
		ResultSet rs = stm.getGeneratedKeys();
		
		// a cada novo teste ele cria um novo produto
		while(rs.next()) {
			Integer id = rs.getInt(1);
			System.out.println("O id criado foi: " + id);
		}
	}
}

/**
 * Para executar cláusulas SQL a partir de uma aplicação precisamos usar um Statement.
 * 
 * O método .execute sempre retorna um booleano que vai ser true se o retorno da cláusula for uma 
 * lista ou false se não for.
 */