package br.com.bibs.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.bibs.jdbc.dao.CategoriaDAO;
import br.com.bibs.jdbc.dao.ProdutoDAO;
import br.com.bibs.jdbc.model.Categoria;
import br.com.bibs.jdbc.model.Produto;

public class TestaListagemDeCategorias {

	public static void main(String[] args) throws SQLException {
		
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
			CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
			List<Categoria> listaDeCategoria = categoriaDAO.listar();
			listaDeCategoria.stream().forEach(ct -> {
				System.out.println(ct.getNome());
				try {
					for(Produto produto : new ProdutoDAO(connection).buscar(ct)) {
						System.out.println(ct.getNome() + " - " + produto.getNome());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		}
		
		
	}

}
