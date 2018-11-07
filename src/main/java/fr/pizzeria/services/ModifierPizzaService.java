package fr.pizzeria.services;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.PrixNegatifException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/** Service de mise à jour d'une pizza
 * @author DIGINAMIC
 */
@Service
public class ModifierPizzaService extends MenuService {

	/** Constructeur
	 * @param dao dao servant à gérer la persistence des données
	 */
	public ModifierPizzaService(IPizzaDao dao) {
		this.dao = dao;
	}

	@Override
	public void executeUC(Scanner scanner) throws StockageException {

		System.out.println("Veuillez choisir la pizza à modifier:");
		String codeAModifier = scanner.next();

		if (dao.pizzaExists(codeAModifier)) {

			System.out.println("Veuillez saisir le nouveau code :");
			String code = scanner.next();
			
			System.out.println("Veuillez saisir le nouveau libellé:");
			String libelle = scanner.next();
			
			System.out.println("Veuillez saisir une catégorie (1=VIANDE, 2=SANS_VIANDE, 3=POISSON):");
			String categStr = scanner.next();
			CategoriePizza categorie = CategoriePizza.get(Integer.parseInt(categStr));
			
			System.out.println("Veuillez saisir le nouveau prix:");
			String prixStr = scanner.next();

			double prix = Double.parseDouble(prixStr);
			
			// Vérification des saisies de l'utilisateur
			if (prix <=0){
				throw new PrixNegatifException();
			}
			
			Pizza pizza = new Pizza(code, libelle, categorie, prix);
			
			dao.updatePizza(codeAModifier, pizza);
		}
		else {
			throw new StockageException("la pizza dont le code est "+codeAModifier+" n'existe pas");
		}
	}

}
