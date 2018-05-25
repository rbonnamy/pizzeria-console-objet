package fr.pizzeria.exception;

public class PrixNegatifException extends StockageException {

	public PrixNegatifException() {
		super("Le prix d'une pizza ne peut pas être négatif.");
	}

}
