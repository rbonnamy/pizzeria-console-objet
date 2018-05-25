package fr.pizzeria.services;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public class MenuServiceFactory {

	public MenuServiceFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static MenuService getMenuService(IPizzaDao dao, int choix) throws StockageException {
		switch (choix){
		case 1: return new ListerPizzasService(dao);
		case 2: return new AjouterPizzaService(dao);
		case 3: return new ModifierPizzaService(dao);
		case 4: return new SupprimerPizzaService(dao);
		case 99: return new ExitOptionService(dao);
		default:
			throw new StockageException("Choix inexistant");
		}
	}

}
