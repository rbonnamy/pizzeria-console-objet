package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoFactory;
import fr.pizzeria.dao.PizzaDaoList;
import fr.pizzeria.dao.PizzaDaoType;
import fr.pizzeria.ihm.Menu;

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
	public static void main(String[] args) {
		
		// Une factory est une classe qui fournit une implémentation
		PizzaDaoFactory factory = new PizzaDaoFactory();
		IPizzaDao pizzaDao = factory.getPizzaDao(PizzaDaoType.MEM_LIST);
		
		// Instanciation d'un scanner pour poser des questions à l'utilisateur
		Scanner scanner = new Scanner(System.in);
		
		/* Instanciation du menu qui centralise l'exécution de tous les scenarios 
		d'utilisation de l'application*/
		Menu menu = new Menu(pizzaDao, scanner);
		menu.manage();

		// Fermeture du scanner après utilisation
		scanner.close();
	}
}
