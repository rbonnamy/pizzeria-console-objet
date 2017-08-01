package fr.pizzeria.dao;

/** Interface définissant la structure que doit posséder une Factory fournissant des instances de {@link IPizzaDao}
 * @author PERSO
 *
 */
public interface IPizzaDaoFactory {

	/** Méthode retournant une {@link IPizzaDao} en fonction d'un type
	 * @param type type de DAO
	 * @return {@link IPizzaDao}
	 */
	IPizzaDao getPizzaDao(PizzaDaoType type);
}
