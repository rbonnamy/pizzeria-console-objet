package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Implémentation base de données.
 * 
 * create table pizzas (id int(10) AUTO_INCREMENT NOT NULL PRIMARY KEY, code
 * varchar(3) not null, libelle varchar(50) not null, prix decimal(5,2), id_cat
 * int(10) not null);
 * 
 * @author R.B.
 *
 */
public class PizzaDaoJdbc extends AbstractDaoBase implements IPizzaDao {
	
	private Connection conn;
	
	public PizzaDaoJdbc() {
	}

	@Override
	public List<Pizza> findAllPizzas() {

		ArrayList<Pizza> liste = new ArrayList<>();

		Connection conn = null;
		Statement statement = null;
		ResultSet res = null;
		try {
			statement = conn.createStatement();
			res = statement.executeQuery("SELECT * FROM pizzas");
			
			while (res.next()) {
				int id = res.getInt("ID");
				String code = res.getString("CODE");
				String libelle = res.getString("LIBELLE");
				Double prix = res.getDouble("PRIX");
				CategoriePizza categ = CategoriePizza.valueOf(res.getString("CATEGORIE"));

				Pizza p = new Pizza(id, code, libelle, categ, prix);

				liste.add(p);
			}	
			res.close();
		} catch (SQLException e) {
			manageSqlException(e);
		} finally {
			closeSqlResources(conn, statement);
		}
		return liste;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = DbMgr.getInstance().getConnection();
			statement = conn.prepareStatement("INSERT into pizzas (CODE, LIBELLE, PRIX, CATEGORIE) VALUES (?, ?, ?, ?)");
			statement.setString(1, pizza.getCode());
			statement.setString(2, pizza.getLibelle());
			statement.setDouble(3, pizza.getPrix());
			statement.setString(4, pizza.getCategorie().name());

		} catch (SQLException e) {
			manageSqlException(e);
		} finally {
			closeSqlResources(conn, statement);
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = DbMgr.getInstance().getConnection();
			statement = conn.prepareStatement("UPDATE pizzas SET CODE=?, LIBELLE=?, PRIX=?, CATEGORIE=? WHERE CODE=?");
			statement.setString(1, pizza.getCode());
			statement.setString(2, pizza.getLibelle());
			statement.setDouble(3, pizza.getPrix());
			statement.setString(4, pizza.getCategorie().name());
			statement.setString(5, codePizza);

		} catch (SQLException e) {
			manageSqlException(e);
		} finally {
			closeSqlResources(conn, statement);
		}
	}

	@Override
	public void deletePizza(String codePizza) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = DbMgr.getInstance().getConnection();
			statement = conn.prepareStatement("DELETE FROM pizzas WHERE CODE=?");
			statement.setString(1, codePizza);

		} catch (SQLException e) {
			manageSqlException(e);
		} finally {
			closeSqlResources(conn, statement);
		}
	}

	@Override
	public boolean pizzaExists(String code) {
		throw new NotImplementedException("Not implemented yet !");
	}

	@Override
	public Pizza findPizzaByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
}
