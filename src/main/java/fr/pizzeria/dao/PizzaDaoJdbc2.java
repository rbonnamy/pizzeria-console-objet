package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author DIGINAMIC
 */
public class PizzaDaoJdbc2 implements IPizzaDao {
	
	/** LOGGER : Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaDaoJdbc2.class);
	
	/** cnx : Connection */
	private Connection cnx;
	
	/**
	 * On charge le driver dans le constructeur
	 */
	public PizzaDaoJdbc2(){
		ResourceBundle props = ResourceBundle.getBundle("jdbc");
		String driverName = props.getString("jdbc.driverClassName");
		String url = props.getString("jdbc.url");
		String user = props.getString("jdbc.user");
		String password = props.getString("jdbc.password");
		
		try {
			Class.forName(driverName);
			this.cnx = DriverManager.getConnection(url, user, password);
		} 
		catch (ClassNotFoundException e) {
			LOGGER.error("Le driver est introuvable", e);
		}
		catch (SQLException e) {
			LOGGER.error("Impossible de se connecter à la base de données", e);
		}
	}

	@Override
	public List<Pizza> findAllPizzas() {
		
		ArrayList<Pizza> pizzas = new ArrayList<>();
		Statement stat = null;
		try {
			stat = this.cnx.createStatement();
			
			ResultSet res = stat.executeQuery("SELECT * FROM PIZZA");
			while (res.next()){
				String code = res.getString("CODE");
				String libelle = res.getString("LIBELLE");
				double prix = res.getDouble("PRIX");
				CategoriePizza categ = CategoriePizza.valueOf(res.getString("CATEG"));
				
				Pizza pizza = new Pizza(code, libelle, categ, prix);
				
				pizzas.add(pizza);
				
			}
			res.close();			
		} catch (SQLException e) {
			LOGGER.error("Une erreur SQL grave s'est produite lors du traitement de la requête", e);
		} finally {
			if (stat!=null){
				try {
					stat.close();
				} catch (SQLException e) {
					LOGGER.error("Impossible de fermer le Statement", e);
				}
			}
		}
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePizza(String codePizza) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean pizzaExists(String code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pizza findPizzaByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

}
