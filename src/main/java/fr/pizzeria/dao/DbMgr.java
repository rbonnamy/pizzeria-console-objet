package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.pizzeria.exception.runtime.ArretApplicationException;
import fr.pizzeria.utils.JdbcProperties;

public class DbMgr {

	/** dbMgr : DbMgr */
	private static DbMgr dbMgr = new DbMgr();

	/** driver : String */
	private String driverName;
	/** url : String */
	private String url;
	/** user : String */
	private String user;
	/** password : String */
	private String password;
	/** connection : Connection */
	private Connection connection;

	/**
	 * Constructeur privé invoqué par la méthode getInstance()
	 */
	private DbMgr() {
		driverName = JdbcProperties.getInstance().getString("jdbc.driverClassName");
		url = JdbcProperties.getInstance().getString("jdbc.url");
		user = JdbcProperties.getInstance().getString("jdbc.username");
		password = JdbcProperties.getInstance().getString("jdbc.password");

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			throw new ArretApplicationException(e.getMessage());
		}
	}

	/** Retourne le singleton de DbMgr
	 * @return DbMgr
	 */
	public static DbMgr getInstance() {
		return dbMgr;
	}

	/**
	 * @return
	 */
	public Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				try {
					connection = DriverManager.getConnection(url, user, password);
				} catch (SQLException e) {
					throw new ArretApplicationException(e.getMessage());
				}
			}
			return connection;
		} catch (SQLException e) {
			throw new ArretApplicationException(e.getMessage());
		}
	}
}
