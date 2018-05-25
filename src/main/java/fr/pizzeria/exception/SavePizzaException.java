package fr.pizzeria.exception;

/** Exception générée si une règle métier est enfreinte lors de la sauvegarde d'une pizza 
 * @author PERSO
 */
public class SavePizzaException extends StockageException {

	public SavePizzaException(String message) {
		super(message);
	}
	
	public SavePizzaException(Exception e) {
		super(e);
	}

}