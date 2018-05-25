package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.TechnicalException;

public class AbstractDaoBase {
	
	/** LOGGER : Logger */
	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractDaoBase.class);
	
	/** Fermeture des requêtes SQL
	 * @param conn connexion
	 * @param statement statement
	 * @param res resultSet
	 */
	protected void closeSqlResources(Connection conn, Statement statement, ResultSet res) {
		if (res!=null){
			try {
				res.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				throw new TechnicalException(e.getMessage());
			}
		}
		
		if (statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				throw new TechnicalException(e.getMessage());
			}
		}
		
		if (conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				throw new TechnicalException(e.getMessage());
			}
		}
	}
	
	protected void closeSqlResources(Connection conn, Statement statement) {
		closeSqlResources(conn, statement, null);
	}
	
	protected void manageSqlException(SQLException e) {
		LOGGER.error(e.getMessage());
		throw new TechnicalException(e.getMessage());
	}

}
