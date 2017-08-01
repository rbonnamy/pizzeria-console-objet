package fr.pizzeria.dao;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.pizza.Pizza;

/** Implémentation base de données
 * @author R.B.
 *
 */
public class PizzaDaoBase implements IPizzaDao {

	@Override
	public Pizza[] findAllPizzas() {
		return null;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException {
		return false;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		return false;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DeletePizzaException {
		return false;
	}

	@Override
	public boolean pizzaExists(String code) {
		// TODO Auto-generated method stub
		return false;
	}

}
