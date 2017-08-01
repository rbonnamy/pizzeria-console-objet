package fr.pizzeria.dao;

/** Classe spécialisée dans la fourniture d'implémentation de {@link IPizzaDao}
 * @author PERSO
 *
 */
public class PizzaDaoFactory implements IPizzaDaoFactory {

	/** Retourne une implémentation de {@link IPizzaDao} en fonction d'un type (besoin) passé en paramètre
	 * @param type type d'implémentation souhaité
	 * @return {@link IPizzaDao}
	 */
	public IPizzaDao getPizzaDao(PizzaDaoType type) {
		switch (type) {
		case MEM_TAB:
			return new PizzaDaoTableau();
		case MEM_LIST:
			return new PizzaDaoList();
		case FILE:
			return new PizzaDaoFichier();
		case DATABASE:
			return new PizzaDaoBase();
		default:
			return new PizzaDaoTableau();
		}
	}
}
