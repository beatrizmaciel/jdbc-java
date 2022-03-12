package br.com.bibs.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.bibs.jdbc.dao.ProdutoDAO;
import br.com.bibs.jdbc.model.Produto;

public class TestaInsercaoComProduto {

	public static void main(String[] args) throws SQLException {
		
		Produto comoda = new Produto("Cômoda", "Cômoda vertical");
		
		// aqui perguntamos se tem uma conexão disponivel:
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
			ProdutoDAO produtoDao = new ProdutoDAO(connection);
			produtoDao.salvar(comoda);
			//Lista = persistenciaProduto.listar();
		}
	}

}
