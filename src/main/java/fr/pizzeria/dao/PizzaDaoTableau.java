package fr.pizzeria.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Implémentation mémoire
 * 
 * @author R.B.
 *
 */
@Service(value = "pizzaDaoTableau")
public class PizzaDaoTableau implements IPizzaDao {

	private Pizza[] pizzas;

	public PizzaDaoTableau() {
		pizzas = new Pizza[20];
		pizzas[0] = new Pizza("PEP", "Pépéroni", CategoriePizza.VIANDE, 12.5);
		pizzas[1] = new Pizza("MAR", "Margherita", CategoriePizza.SANS_VIANDE, 14);
		pizzas[2] = new Pizza("REI", "La Reine", CategoriePizza.POISSON, 11.5);
		pizzas[3] = new Pizza("FRO", "La 4 fromages", CategoriePizza.SANS_VIANDE, 12);
		pizzas[4] = new Pizza("CAN", "La cannibale", CategoriePizza.VIANDE, 12.5);
		pizzas[5] = new Pizza("SAV", "La savoyarde", CategoriePizza.SANS_VIANDE, 13);
		pizzas[6] = new Pizza("ORI", "L'orientale", CategoriePizza.VIANDE, 13.5);
		pizzas[7] = new Pizza("IND", "L'indienne", CategoriePizza.VIANDE, 14);
	}

	@Override
	public List<Pizza> findAllPizzas() {
		return Arrays.asList(pizzas);
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		int indexInsertion = -1;

		for (int index = 0; index < pizzas.length; index++) {
			if (pizzas[index] == null) {
				indexInsertion = index;
				break;
			}
		}
		if (indexInsertion != -1) {
			pizzas[indexInsertion] = pizza;
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {

		int indexPizzaAModifier = getIndexFromCode(codePizza);
		if (indexPizzaAModifier == -1) {
			pizzas[indexPizzaAModifier].setCode(pizza.getCode());
			pizzas[indexPizzaAModifier].setLibelle(pizza.getLibelle());
			pizzas[indexPizzaAModifier].setPrix(pizza.getPrix());
		}
	}

	@Override
	public void deletePizza(String codePizza) {
		int indexPizzaASupprimer = getIndexFromCode(codePizza);
		pizzas[indexPizzaASupprimer] = null;
	}

	/**
	 * Retourne l'index dans le tableau de la pizza dont le code est pass� en
	 * param�tre
	 * 
	 * @param pizzas
	 *            tableaux de pizza
	 * @param code
	 *            code de la pizza recherch�e
	 * @return int
	 */
	protected int getIndexFromCode(String code) {
		int indexPizzaAModifier = -1;
		for (int index = 0; index < pizzas.length; index++) {
			if (pizzas[index] != null && pizzas[index].getCode().equals(code)) {
				return index;
			}
		}
		return indexPizzaAModifier;
	}

	@Override
	public boolean pizzaExists(String code) {
		return getIndexFromCode(code) != -1;
	}

	@Override
	public Pizza findPizzaByCode(String code) {
		for (int index = 0; index < pizzas.length; index++) {
			if (pizzas[index] != null && pizzas[index].getCode().equals(code)) {
				return pizzas[index];
			}
		}
		return null;
	}

}
