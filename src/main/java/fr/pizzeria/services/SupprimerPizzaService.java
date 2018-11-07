package fr.pizzeria.services;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.StockageException;

/** Service de suppression d'une pizza
 * @author DIGINAMIC
 */
@Service
public class SupprimerPizzaService extends MenuService {
	
	/** Constructeur
	 * @param dao dao servant à gérer la persistence des données
	 */
	public SupprimerPizzaService(IPizzaDao dao) {
		this.dao = dao;
	}

	@Override
	public void executeUC(Scanner scanner) throws StockageException {
		
		System.out.println("Veuillez choisir la pizza à supprimer:");
		String codeASupprimer = scanner.next();
		
		if (!dao.pizzaExists(codeASupprimer)){
			throw new DeletePizzaException("Cette pizza n'existe pas.");
		}
	
		dao.deletePizza(codeASupprimer);
		
	}

}
