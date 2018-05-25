package fr.pizzeria.ihm;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

@Service
public class SupprimerPizzaService extends MenuService {
	
	/** Constructeur
	 * @param dao Instance de {@link IPizzaDao} utilisée pour la persistence
	 * @param scanner scanner permettant d'interroger l'utilisateur
	 */
	public SupprimerPizzaService(IPizzaDao dao) {
		this.dao = dao;
	}

	@Override
	public void executeUC(Scanner scanner) throws StockageException {
		
		System.out.println("Veuillez choisir la pizza à supprimer:");
		String codeASupprimer = scanner.next();
	
		dao.deletePizza(codeASupprimer);
		
	}

}
