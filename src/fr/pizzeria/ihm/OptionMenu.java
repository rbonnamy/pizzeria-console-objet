package fr.pizzeria.ihm;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/** Représente une option de menu et centralise attributs et méthodes communes
 * @author DIGINAMIC
 */
public abstract class OptionMenu {
	
	/** libelle : String */
	protected String libelle;
	
	/** dao : IPizzaDao */
	protected IPizzaDao dao;

	/** Constructeur d'une option de menu
	 * @param dao implémentation de {@link IPizzaDao}
	 */
	public OptionMenu(IPizzaDao dao) {
		super();
		this.dao = dao;
	}
	
	/** Méthode générique que chaque classe fille doit implémenter
	 * @return boolean
	 * @throws StockageException exception qui est générée lorsque l'utilisateur ne respecte pas les règles métier.
	 */
	public abstract boolean execute() throws StockageException;
	
	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

}
