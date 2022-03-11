import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	public DataSource dataSource;

	// para não abrir e fechar conexões de dados o tempo todo:
	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/Loja_Virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("555!Root");
		
		comboPooledDataSource.setMaxPoolSize(15); //define o numero máximo de conexões

		this.dataSource = comboPooledDataSource; // data source expõe para a aplicação e faz o pool funcionar
	}

	public Connection recuperarConexao() throws SQLException {

		return this.dataSource.getConnection();
	}
}

/*
 * Adicionei as bibliotecas C3P0 (p/ funcionalidades definidas de drivers JDBC)
 * + implementação p/ data source + implementação pool de conexões
 */