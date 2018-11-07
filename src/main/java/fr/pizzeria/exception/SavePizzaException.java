package fr.pizzeria.exception;

/** Exception générée si une règle métier est enfreinte lors de la sauvegarde d'une pizza 
 * @author PERSO
 */
public class SavePizzaException extends StockageException {

	/** Constructeur
	 * @param message message d'erreur
	 */
	public SavePizzaException(String message) {
		super(message);
	}
}
