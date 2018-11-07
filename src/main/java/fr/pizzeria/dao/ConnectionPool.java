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

/** Pool de connexion JDBC
 * @author DIGINAMIC
 */
public class ConnectionPool {
	
	/** connectionPool */
	private static ConnectionPool connectionPool = new ConnectionPool();
	
	/** dataSource */
	private DataSource dataSource;
	
	/**
	 * Constructeur privé
	 */
	private ConnectionPool(){
		initPool();
	}
	
	/** Retourne le pool de connexions
	 * @return {@link ConnectionPool}
	 */
	public static ConnectionPool getInstance(){
		return connectionPool;
	}

	/**
	 * Initialise le pool de connexions
	 */
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

	/** Retourne une connexion JDBC
	 * @return {@link Connection}
	 */
	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}