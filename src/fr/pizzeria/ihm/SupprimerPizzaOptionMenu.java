package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.StockageException;

public class SupprimerPizzaOptionMenu extends OptionMenu {

	/** scanner : Scanner */
	private Scanner scanner;
	
	/** Constructeur
	 * @param dao Instance de {@link IPizzaDao} utilisée pour la persistence
	 * @param scanner scanner permettant d'interroger l'utilisateur
	 */
	public SupprimerPizzaOptionMenu(IPizzaDao dao, Scanner scanner) {
		super(dao);
		this.scanner = scanner;
	}

	@Override
	public String getLibelle() {
		return "4. Supprimer une pizza";
	}

	@Override
	public boolean execute() throws StockageException {
		
		System.out.println("Veuillez choisir la pizza � supprimer:");
		String codeASupprimer = scanner.next();
		if (codeASupprimer.trim().isEmpty()){
			throw new DeletePizzaException("Le code de la pizza � supprimer n'est pas renseign�.");
		}

		try {
			dao.deletePizza(codeASupprimer);
		} catch (DeletePizzaException e) {
			throw new DeletePizzaException("Le code de la pizza � supprimer est introuvable.");
		}
		return false;
	}

}
