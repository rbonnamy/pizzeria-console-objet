package fr.pizzeria.dao;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.pizza.Pizza;

/** Implémentation mémoire
 * @author R.B.
 *
 */
public class PizzaDaoTableau implements IPizzaDao {
	
	private Pizza[] pizzas;
	
	public PizzaDaoTableau(){
		pizzas = new Pizza[20];
		pizzas[0] = new Pizza("PEP", "Pépéroni", 12.5);
		pizzas[1] = new Pizza("MAR", "Margherita", 14);
		pizzas[2] = new Pizza("REI", "La Reine", 11.5);
		pizzas[3] = new Pizza("FRO", "La 4 fromages", 12);
		pizzas[4] = new Pizza("CAN", "La cannibale", 12.5);
		pizzas[5] = new Pizza("SAV", "La savoyarde", 13);
		pizzas[6] = new Pizza("ORI", "L�orientale", 13.5);
		pizzas[7] = new Pizza("IND", "L�indienne", 14);
	}

	@Override
	public Pizza[] findAllPizzas() {
		return pizzas;
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException {
		int indexInsertion = -1;
		
		for (int index = 0; index < pizzas.length; index++) {
			if (pizzas[index] == null) {
				indexInsertion = index;
				break;
			}
		}
		if (indexInsertion == -1) {
			throw new SavePizzaException("Le tableau est plein");
		} else {
			pizzas[indexInsertion] = pizza;
		}
		return false;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		Pizza[] pizzas = findAllPizzas();
		
		int indexPizzaAModifier = getIndexFromCode(codePizza);
		if (indexPizzaAModifier==-1){
			throw new UpdatePizzaException("Le code de la pizza � modifier n'existe pas");
		}

		pizzas[indexPizzaAModifier].setCode(pizza.getCode());
		pizzas[indexPizzaAModifier].setNom(pizza.getNom());
		pizzas[indexPizzaAModifier].setPrix(pizza.getPrix());
		
		return false;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DeletePizzaException {
		int indexPizzaASupprimer = getIndexFromCode(codePizza);
		if (indexPizzaASupprimer == -1) {
			throw new DeletePizzaException("Le code de la pizza � supprimer n'existe pas");
		} else {
			pizzas[indexPizzaASupprimer] = null;
		}
		return false;
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
		return getIndexFromCode(code)!=-1;
	}
	

}
