package fr.pizzeria.services;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/** Factory qui retourne un service de gestion en fonction d'un choix de l'utilisateur
 * @author DIGINAMIC
 */
public class MenuServiceFactory {

	/**
	 * Constructeur
	 */
	public MenuServiceFactory() {
	}
	
	/** Retourne une instance de {@link MenuService} en fonction d'un choix dans le menu de gestion
	 * de la pizzeria. 
	 * La dao passée en paramètre est celle qui sera utilisée pour gérer la persistence des pizzas.
	 * @param dao dao à utiliser pour gérer la persistence des pizzas
	 * @param choix choix de l'utilisateur dans le menu
	 * @return {@link MenuService}
	 * @throws StockageException en cas de non respect d'une règle métier
	 */
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
