package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/** Service permettant de quitter l'application
 * @author DIGINAMIC
 */
public class ExitOptionService extends MenuService {

	/** Constructeur
	 * @param dao dao servant à gérer la persistence des données
	 */
	public ExitOptionService(IPizzaDao dao) {
	}

	@Override
	public void executeUC(Scanner scanner) throws StockageException {
		System.out.println("Au revoir.");
	}
}
