package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionPool {
	
	private DataSource dataSource;

	public ConnectionPool() {
		MysqlDataSource pool = new MysqlDataSource();
		pool.setURL("jdbc:mysql://localhost:3306/lojavirtual");
		pool.setUser("root");
		pool.setPassword("root");
		this.dataSource = pool;
	}
	
	public Connection getConnection() throws SQLException {
		//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lojavirtual", "root", "root");
		Connection connection = dataSource.getConnection();
		System.out.println("Abrindo conexão...");
		return connection;
		
	}
}
