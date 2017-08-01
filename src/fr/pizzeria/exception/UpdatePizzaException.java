package fr.pizzeria.exception;

/** Exception générée si une règle métier est enfreinte lors de la modification d'une pizza 
 * @author PERSO
 */
public class UpdatePizzaException extends StockageException {

	public UpdatePizzaException(String message) {
		super(message);
	}

}
