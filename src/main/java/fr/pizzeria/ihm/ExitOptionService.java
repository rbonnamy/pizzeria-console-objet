package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public class ExitOptionService extends MenuService {

	public ExitOptionService(IPizzaDao dao) {
	}

	@Override
	public void executeUC(Scanner scanner) throws StockageException {
		System.out.println("Au revoir.");
	}
}
