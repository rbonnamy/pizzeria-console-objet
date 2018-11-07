package fr.pizzeria.exception;

/** Exception lancée lorsque l'utilisateur saisit un prix de pizza négatif
 * @author DIGINAMIC
 */
public class PrixNegatifException extends StockageException {

	/** MESSAGE */
	private static final String MESSAGE = "Le prix d'une pizza ne peut pas être négatif.";

	/** Constructeur
	 * @param message message d'erreur
	 */
	public PrixNegatifException() {
		super(MESSAGE);
	}

}
