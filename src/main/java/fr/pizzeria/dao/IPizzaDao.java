package fr.pizzeria.dao;

import java.util.List;
import java.util.Optional;

import fr.pizzeria.model.Pizza;

/** Interface définissant les méthodes de gestion d'une pizza dans un espace de stockage
 * @author R.B.
 *
 */
public interface IPizzaDao {
	
	/** Retourne toutes les pizzas stockées (le stockage dépend de l'implémentation de {@link IPizzaDao}
	 * @return Pizza[]
	 */
	List<Pizza> findAllPizzas();
	
	/** Sauvegarde une nouvelle pizza
	 * @param pizza nouvelle pizza
	 * @return boolean
	 */
	void saveNewPizza(Pizza pizza);
	
	/** Mise é jour de la pizza dont le code est passé en paramétre. La pizza passé en second paramétre posséde les attributs servant é modifier la pizza d'origine.
	 * @param codePizza code de la pizza é modifier
	 * @param pizza nouvelle pizza
	 * @return boolean 
	 */
	void updatePizza(String codePizza, Pizza pizza);
	
	/** Suppression e la pizza dont le code est passé en paramétre.
	 * @param codePizza code de la pizza é modifier
	 * @return boolean 
	 */
	void deletePizza(String codePizza);

	/**
	 * Retourne la pizza dont le code est passé en paramètre
	 * 
	 * @param code code de la pizza recherchée
	 * @return {@link Optional}
	 */
	Pizza findPizzaByCode(String code);
	
	/** Vérifie si la pizza existe ou non à partir de son code
	 * @param code code de la pizza recherchée
	 * @return boolean
	 */
	boolean pizzaExists(String code);

}
