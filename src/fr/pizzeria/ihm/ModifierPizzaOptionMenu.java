package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.pizza.Pizza;

public class ModifierPizzaOptionMenu extends OptionMenu {

	/** scanner : Scanner */
	private Scanner scanner;
	
	/** Constructeur
	 * @param dao Instance de {@link IPizzaDao} utilisée pour la persistence
	 * @param scanner scanner permettant d'interroger l'utilisateur
	 */
	public ModifierPizzaOptionMenu(IPizzaDao dao, Scanner scanner) {
		super(dao);
		this.scanner = scanner;
	}

	@Override
	public String getLibelle() {
		return "3. Modifier une pizza";
	}

	@Override
	public boolean execute() throws StockageException {
		
		System.out.println("Veuillez choisir la pizza à modifier:");
		String codeAModifier = scanner.next();
		if (codeAModifier.trim().isEmpty()) {
			throw new UpdatePizzaException("Le code de la pizza à modifier n'est pas renseign�.");
		}
		
		if (dao.pizzaExists(codeAModifier)){

			System.out.println("Veuillez saisir un code:");
			String code = scanner.next();
			if (code.trim().isEmpty()) {
				throw new UpdatePizzaException("Le code de la pizza n'est pas renseigné.");
			}
	
			System.out.println("Veuillez saisir un libellé:");
			String libelle = scanner.next();
			if (libelle.trim().isEmpty()) {
				throw new UpdatePizzaException("Le libellé de la pizza n'est pas renseigné.");
			}
	
			System.out.println("Veuillez saisir un prix:");
			String prixStr = scanner.next();
			if (prixStr.trim().isEmpty()) {
				throw new UpdatePizzaException("Le prix de la pizza n'est pas renseigné.");
			}
	
			double prix = 0.0;
			try {
				prix = Double.parseDouble(prixStr);
			} catch (NumberFormatException e) {
				throw new SavePizzaException("Le prix de la pizza n'est pas un nombre.");
			}
			Pizza pizza = new Pizza(code, libelle, prix);
	
			try {
				dao.updatePizza(codeAModifier, pizza);
			} catch (UpdatePizzaException e) {
				System.out.println("Le code de la pizza à modifier est introuvable.");
			}
			return true;
		}
		System.out.println("Le code de la pizza à modifier est introuvable.");
		return false;
	}

}
