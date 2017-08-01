package fr.pizzeria.ihm;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.pizza.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {

	/** Constructeur
	 * @param pizzas
	 */
	public ListerPizzasOptionMenu(IPizzaDao dao) {
		super(dao);
	}

	@Override
	public String getLibelle() {
		return "1. Lister les pizzas";
	}

	@Override
	public boolean execute() {
		
		Pizza[] pizzas = dao.findAllPizzas();
		
		for (int i=0; i<pizzas.length; i++){
			if (pizzas[i]!=null){
				System.out.println("Pizza id="+pizzas[i].getId()+" : "+pizzas[i].getCode()+" "+pizzas[i].getNom()+" "+pizzas[i].getPrix());
			}
		}
		return true;
	}
}
