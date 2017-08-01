package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.pizza.Pizza;

public class AjouterPizzaOptionMenu extends OptionMenu {

	/** scanner : Scanner */
	private Scanner scanner;
	
	/** Constructeur
	 * @param dao Instance de {@link IPizzaDao} utilisée pour la persistence
	 * @param scanner scanner permettant d'interroger l'utilisateur
	 */
	public AjouterPizzaOptionMenu(IPizzaDao dao, Scanner scanner) {
		super(dao);
		this.scanner = scanner;
	}

	@Override
	public String getLibelle() {
		return "2. Ajouter une pizza";
	}

	@Override
	public boolean execute() throws StockageException {

		System.out.println("Veuillez saisir un code:");
		String code = scanner.next();
		if (code.trim().isEmpty()) {
			throw new SavePizzaException("Le code de la nouvelle pizza est non renseigné.");
		}

		System.out.println("Veuillez saisir un libellé:");
		String libelle = scanner.next();
		if (libelle.trim().isEmpty()) {
			throw new SavePizzaException("Le libellé de la nouvelle pizza est non renseigné.");
		}
		System.out.println("Veuillez saisir un prix:");
		String prixStr = scanner.next();
		if (prixStr.trim().isEmpty()) {
			throw new SavePizzaException("Le prix de la nouvelle pizza est non renseigné.");
		}
		double prix = 0.0;
		try {
			prix = Double.parseDouble(prixStr);
		} catch (NumberFormatException e) {
			throw new SavePizzaException("Le prix de la nouvelle pizza n'est pas un nombre.");
		}
		Pizza pizza = new Pizza(code, libelle, prix);

		try {
			dao.saveNewPizza(pizza);
		} catch (SavePizzaException e) {
			System.out.println("Tableau plein");
		}

		return false;
	}

}
