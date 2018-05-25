package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoList;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.ihm.MenuServiceFactory;

/**
 * Application de gestion d'une pizzeria
 * 
 * @author R.B.
 *
 */
public class PizzeriaAdminConsoleApp {
	
	/**
	 * Point d'entrée de l'application
	 * 
	 * @param args
	 *            paramètres
	 */
	public static void main(String[] args) throws Exception {
						
		Scanner scanner = new Scanner(System.in);
		IPizzaDao dao = new PizzaDaoList();
		
		boolean continuer = true;
		while (continuer) {

			// Affichage du menu
			afficher();

			// Poser une question à l'utilisateur
			String choixMenu = scanner.next();
			
			// Conversion du choix utilisateur en int
			int choix = Integer.parseInt(choixMenu);
			
			// On exécute l'option correspondant au choix de l'utilisateur
			try {
				MenuServiceFactory.getMenuService(dao, choix).executeUC(scanner);
			}
			catch (StockageException e){
				System.out.println(e.getMessage());				
			}
		}
		
		scanner.close();
	}
	
	private static void afficher() {
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("99. Sortir");
	}
}
