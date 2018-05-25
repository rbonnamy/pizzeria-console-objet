package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/** Représente une option de menu et centralise attributs et méthodes communes
 * @author DIGINAMIC
 */
public abstract class MenuService {
	
	protected IPizzaDao dao;
	
	
	/** Constructeur d'une option de menu
	 * @param dao implémentation de {@link IPizzaDao}
	 */
	public MenuService() {
		super();		
	}
	
	/** Méthode générique que chaque classe fille doit implémenter
	 * @return boolean
	 * @throws StockageException exception qui est générée lorsque l'utilisateur ne respecte pas les règles métier.
	 */
	public abstract void executeUC(Scanner scanner) throws StockageException;

}
