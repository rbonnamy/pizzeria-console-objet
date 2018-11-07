package fr.pizzeria.exception;

/** Exception générée si une règle métier est enfreinte lors de la suppression d'une pizza 
 * @author PERSO
 */
public class DeletePizzaException extends StockageException {

	/** Constructeur
	 * @param message message d'erreur
	 */
	public DeletePizzaException(String message) {
		super(message);
	}

}
