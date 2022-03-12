package br.com.bibs.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.bibs.jdbc.dao.ProdutoDAO;
import br.com.bibs.jdbc.model.Produto;

public class TestaInsercaoEListagemComProduto {

	public static void main(String[] args) throws SQLException {

		// construtor
		Produto comoda = new Produto("Cômoda", "Cômoda vertical");

		// aqui perguntamos se tem uma conexão disponivel:
		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			ProdutoDAO produtoDao = new ProdutoDAO(connection);
			produtoDao.salvar(comoda);

			List<Produto> listaDeProdutos = produtoDao.listar();
			listaDeProdutos.stream().forEach(lp -> System.out.println(lp));
		}
	}

}
