package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSource {
	
	private static BasicDataSource dataSource;

	public static Connection getConnection(){
		
		BasicDataSource dataSource = getInstance();
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private static BasicDataSource getInstance() {
		if (dataSource==null){
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("");
			dataSource.setUrl("jdbc:mariadb://localhost:3306/essai");
			dataSource.setMaxActive(10);
			dataSource.setMaxIdle(5);
			dataSource.setInitialSize(5);
			dataSource.setValidationQuery("SELECT 1");
		}
		return dataSource;
	}
}
