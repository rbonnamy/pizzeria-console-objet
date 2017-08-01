package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.pizza.Pizza;

/** Implémentation mémoire
 * @author R.B.
 *
 */
public class PizzaDaoList implements IPizzaDao {
	
	private List<Pizza> pizzas;
	
	public PizzaDaoList(){
		pizzas = new ArrayList<Pizza>();
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.5));
		pizzas.add(new Pizza("MAR", "Margherita", 14));
		pizzas.add(new Pizza("REI", "La Reine", 11.5));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.5));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13));
		pizzas.add(new Pizza("ORI", "L'orientale", 13.5));
		pizzas.add(new Pizza("IND", "L'indienne", 14));
	}

	@Override
	public Pizza[] findAllPizzas() {
		
		Pizza[] tab = new Pizza[pizzas.size()];
		return pizzas.toArray(tab);
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException {

		pizzas.add(pizza);
		return false;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		
		for (Pizza pizzaupdate : pizzas){
			if (pizzaupdate.getCode().equals(codePizza)){
				pizzaupdate.setCode(pizza.getCode());
				pizzaupdate.setNom(pizza.getNom());
				pizzaupdate.setPrix(pizza.getPrix());
			}
		}
		return false;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DeletePizzaException {
		Pizza pizza = getPizzaFromCode(codePizza);
		if (pizza==null){
			throw new DeletePizzaException("Le code de la pizza à supprimer n'existe pas");
		}
		pizzas.remove(pizza);
		return false;
	}
	
	/**
	 * Retourne l'index dans le tableau de la pizza dont le code est passé en
	 * paramètre
	 * 
	 * @param pizzas
	 *            tableaux de pizza
	 * @param code
	 *            code de la pizza recherch�e
	 * @return int
	 */
	protected Pizza getPizzaFromCode(String code) {
		for (int index = 0; index < pizzas.size(); index++) {
			if (pizzas.get(index).getCode().equals(code)) {
				return pizzas.get(index);
			}
		}
		return null;
	}
	
	@Override
	public boolean pizzaExists(String code) {
		return getPizzaFromCode(code)!=null;
	}

}
