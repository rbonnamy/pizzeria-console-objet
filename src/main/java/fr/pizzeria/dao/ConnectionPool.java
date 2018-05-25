package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

import fr.pizzeria.utils.JdbcProperties;

public class ConnectionPool {
	
	private static ConnectionPool connectionPool = new ConnectionPool();
	
	private DataSource dataSource;
	
	private ConnectionPool(){
		initPool();
	}
	
	public static ConnectionPool getInstance(){
		return connectionPool;
	}

	private void initPool() {
		try {
			String driverName = JdbcProperties.getInstance().getString("jdbc.driverClassName");
			String url = JdbcProperties.getInstance().getString("jdbc.url");
			String user = JdbcProperties.getInstance().getString("jdbc.username");
			String password = JdbcProperties.getInstance().getString("jdbc.password");
			
			Class.forName(driverName);

			GenericObjectPool gPool = new GenericObjectPool();
			gPool.setMaxActive(5);

			ConnectionFactory cf = new DriverManagerConnectionFactory(url, user, password);
			PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
			gPool.setFactory(pcf);
			
			dataSource = new PoolingDataSource(gPool);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}