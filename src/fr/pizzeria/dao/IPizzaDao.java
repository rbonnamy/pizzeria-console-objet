package fr.pizzeria.dao;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.pizza.Pizza;

/** Interface définissant les méthodes de gestion d'une pizza dans un espace de stockage
 * @author R.B.
 *
 */
public interface IPizzaDao {
	
	/** Retourne toutes les pizzas stockées (le stockage dépend de l'implémentation de {@link IPizzaDao}
	 * @return Pizza[]
	 */
	Pizza[] findAllPizzas();
	
	/** Sauvegarde une nouvelle pizza
	 * @param pizza nouvelle pizza
	 * @return boolean
	 * @throws SavePizzaException exception levée si la sauvegarde d'une nouvelle pizza échoue
	 */
	boolean saveNewPizza(Pizza pizza) throws SavePizzaException;
	
	/** Mise é jour de la pizza dont le code est passé en paramétre. La pizza passé en second paramétre posséde les attributs servant é modifier la pizza d'origine.
	 * @param codePizza code de la pizza é modifier
	 * @param pizza nouvelle pizza
	 * @return boolean 
	 * @throws UpdatePizzaException exception levée si la modification de la pizza échoue
	 */
	boolean updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException;
	
	/** Suppression e la pizza dont le code est passé en paramétre.
	 * @param codePizza code de la pizza é modifier
	 * @return boolean 
	 * @throws DeletePizzaException exception levée si la suppression de la pizza échoue
	 */
	boolean deletePizza(String codePizza) throws DeletePizzaException;

	/** Vérifie si une pizza avec le code passé en paramètre existe ou non
	 * @param codeAModifier code de la pizza
	 * @return boolean
	 */
	boolean pizzaExists(String code);

}
