package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public class Menu {

	private OptionMenu[] optionMenus;
	private Scanner scanner;
	
	public Menu(IPizzaDao dao, Scanner scanner){
		
		this.scanner = scanner;
		
		optionMenus = new OptionMenu[4];
		optionMenus[0] = new ListerPizzasOptionMenu(dao);
		optionMenus[1] = new AjouterPizzaOptionMenu(dao, scanner);
		optionMenus[2] = new ModifierPizzaOptionMenu(dao, scanner);
		optionMenus[3] = new SupprimerPizzaOptionMenu(dao, scanner);
	}
	
	public void afficher(){
		System.out.println("***** Pizzeria Administration *****");
		
		for (int i=0; i<optionMenus.length; i++){
			System.out.println(optionMenus[i].getLibelle());
		}
		
		System.out.println("99. Sortie.");
	}


	public void manage() {
		
		int choixMenu = 0;
		while (choixMenu != 99) {

			// Affichage du menu
			afficher();

			// Poser une question
			choixMenu = scanner.nextInt();

			// Analyse de la r�ponse � la question
			try {
				switch (choixMenu) {
				case 1:
					optionMenus[0].execute();
					break;
				case 2:
					optionMenus[1].execute();
					break;
				case 3:
					optionMenus[0].execute();
					optionMenus[2].execute();
					
					break;
				case 4:
					optionMenus[0].execute();
					optionMenus[3].execute();
					break;
				default:
					break;
				}
			} catch (StockageException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	
	public OptionMenu[] getOptionMenus() {
		return optionMenus;
	}
}
