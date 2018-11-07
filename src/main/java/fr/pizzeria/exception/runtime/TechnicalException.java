package fr.pizzeria.exception.runtime;

/** Exception technique grave
 * @author DIGINAMIC
 */
public class TechnicalException extends RuntimeException {

	/** Constructeur
	 * @param message message d'erreur
	 */
	public TechnicalException(String message) {
		super(message);
	}

	/** Constructeur
	 * @param message message d'erreur
	 * @param cause cause racine
	 */
	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}
}
