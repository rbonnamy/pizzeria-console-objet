package fr.pizzeria.dao;

/** Représente tous les types de {@link IPizzaDao} gérés par l'application
 * @author PERSO
 *
 */
public enum PizzaDaoType {

	/** Type implémentation mémoire sous forme de tableau */
	MEM_TAB,
	/** Type implémentation mémoire sous forme de liste */
	/** MEM_LIST : PizzaDaoType */
	MEM_LIST,
	/** Type implémentation fichier */
	FILE, 
	/** Type implémentation base de données */
	DATABASE;
}
