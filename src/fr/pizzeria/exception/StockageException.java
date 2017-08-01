package fr.pizzeria.exception;

/** Classe mère de toutes les exceptions rencontrées dans le cadre de la gestion des pizzas 
 * @author PERSO
 */
public class StockageException extends Exception {

	public StockageException(String message) {
		super(message);
	}
}
