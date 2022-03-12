package br.com.bibs.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.management.RuntimeErrorException;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory factory = new ConnectionFactory();

		try (Connection connection = factory.recuperarConexao()) {

			connection.setAutoCommit(false); // dessa forma, controlamos o momento do auto commit da aplicação

			// \/ abrimos o parênteses (+ chaves) para especificar um AutoCloseable
			try (PreparedStatement stm = connection.prepareStatement(
					"INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);) {
				adicionarVariavel("SmartTV", "45 polegadas", stm);
				adicionarVariavel("Radio", "Radio de bateria", stm);

				connection.commit();

				stm.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK EXECUTADO");
				connection.rollback();
			}
		}
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {

		stm.setString(1, nome);
		stm.setString(2, descricao);

		if (nome.equals("Radio")) {
			throw new RuntimeException("Não foi possível adicionar o produto");
		}

		stm.execute();

		try (ResultSet rs = stm.getGeneratedKeys()) {
			while (rs.next()) {
				Integer id = rs.getInt(1);
				System.out.println("O id criado foi: " + id);
			}
		}
	}
}

/**
 * O PreparedStatement protege o banco de dados, deixando o código menos
 * vulnerável. O método commit() verifica as getGeneratedKeys e vai dar o commit
 * na "transação"
 */
