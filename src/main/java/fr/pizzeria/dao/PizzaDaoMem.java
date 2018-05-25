package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/** Implémentation mémoire de {@link IPizzaDao}
 * @author R.B.
 *
 */
public class PizzaDaoMem implements IPizzaDao {
	
	/** pizzas : List<Pizza> */
	private List<Pizza> pizzas;
	
	/**
	 * Constructeur
	 */
	public PizzaDaoMem(){
		pizzas = new ArrayList<Pizza>();
		pizzas.add(new Pizza("PEP", "Pépéroni", CategoriePizza.VIANDE, 12.5));
		pizzas.add(new Pizza("MAR", "Margherita", CategoriePizza.SANS_VIANDE, 14));
		pizzas.add(new Pizza("REI", "La Reine", CategoriePizza.POISSON, 11.5));
		pizzas.add(new Pizza("FRO", "La 4 fromages", CategoriePizza.SANS_VIANDE, 12));
		pizzas.add(new Pizza("CAN", "La cannibale", CategoriePizza.VIANDE, 12.5));
		pizzas.add(new Pizza("SAV", "La savoyarde", CategoriePizza.SANS_VIANDE, 13));
		pizzas.add(new Pizza("ORI", "L'orientale", CategoriePizza.VIANDE, 13.5));
		pizzas.add(new Pizza("IND", "L'indienne", CategoriePizza.VIANDE, 14));
	}

	@Override
	public List<Pizza> findAllPizzas() {
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		if (pizza!=null){
			pizzas.add(pizza);
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		
		if (codePizza==null){
			throw new IllegalArgumentException("Il est interdit de passer en paramètre un codePizza null");
		}
		if (pizza==null){
			throw new IllegalArgumentException("Il est interdit de passer en paramètre une Pizza null");
		}
		
		if (pizzas!=null){
			Pizza pizzaAModifier = findPizzaByCode(codePizza);
			
			pizzaAModifier.setCode(pizza.getCode());
			pizzaAModifier.setLibelle(pizza.getLibelle());
			pizzaAModifier.setPrix(pizza.getPrix());
		}
	}

	@Override
	public void deletePizza(String codePizza) {
		Pizza pizza = findPizzaByCode(codePizza);
		if (pizza!=null){
			pizzas.remove(pizza);
		}
	}

	@Override
	public Pizza findPizzaByCode(String code) {
		for (Pizza pizza: pizzas){
			if (code!=null && code.equals(pizza.getCode())){
				return pizza;
			}
		}
		return null;
	}

	@Override
	public boolean pizzaExists(String code) {

		return findPizzaByCode(code)!=null;
	}

}
