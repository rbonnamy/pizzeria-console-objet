package fr.pizzeria.services;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

/** Service permettant de lister les pizzas
 * @author DIGINAMIC
 */
@Service
public class ListerPizzasService extends MenuService {

	/** Constructeur
	 * @param dao dao servant à gérer la persistence des données
	 */
	public ListerPizzasService(IPizzaDao dao) {
		this.dao = dao;
	}

	@Override
	public void executeUC(Scanner scanner) throws StockageException {
		
		List<Pizza> pizzas = dao.findAllPizzas();
		
		for (int i=0; pizzas!=null && i<pizzas.size(); i++) {
			System.out.println(pizzas.get(i).toString());	
		}
	}
}
