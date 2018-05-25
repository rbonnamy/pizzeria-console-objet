package fr.pizzeria.ihm;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@Service
public class AjouterPizzaService extends MenuService {
	
	/** Constructeur
	 * @param dao Instance de {@link IPizzaDao} utilisée pour la persistence
	 * @param scanner scanner permettant d'interroger l'utilisateur
	 */
	public AjouterPizzaService(IPizzaDao dao) {
		this.dao = dao;
	}

	@Override
	public void executeUC(Scanner scanner) throws StockageException {

		System.out.println("Veuillez saisir un code:");
		String code = scanner.next();
	
		System.out.println("Veuillez saisir un libellé:");
		String libelle = scanner.next();
		
		System.out.println("Veuillez saisir une catégorie (1=VIANDE, 2=SANS_VIANDE, 3=POISSON):");
		String categStr = scanner.next();
		
		CategoriePizza categ = CategoriePizza.get(Integer.parseInt(categStr));
		
		System.out.println("Veuillez saisir un prix:");
		String prixStr = scanner.next();
		
		double prix = Double.parseDouble(prixStr);
		if (prix <=0){
			throw new StockageException("Vous ne pouvez pas saisir un prix négatif");
		}
		
		Pizza pizza = new Pizza(code, libelle, categ, prix);

		dao.saveNewPizza(pizza);
	}

}
