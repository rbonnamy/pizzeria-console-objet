package fr.pizzeria.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoListTest {

	public static List<Pizza> list() {
		ArrayList<Pizza> pizzas = new ArrayList<>();
		pizzas.add(new Pizza("VEG", "Vegetarien", CategoriePizza.SANS_VIANDE, 12.0));
		pizzas.add(new Pizza("BBB", "Bulle", CategoriePizza.VIANDE, 12.0));

		Pizza p = new Pizza("VEG", "Vegetarien", CategoriePizza.SANS_VIANDE, 12.0);
		boolean verif = pizzas.contains(p);
		System.out.println(verif);

		return pizzas;
	}

	@Test
	public void testFindAllPizzas() {
		IPizzaDao dao = new PizzaDaoList();
		List<Pizza> pizzas = dao.findAllPizzas();
		assertEquals(8, pizzas.size());

	}

	@Test
	public void testSaveNewPizza() {

		IPizzaDao dao = new PizzaDaoList();
		dao.saveNewPizza(null);

		assertEquals(9, dao.findAllPizzas().size());

	}

	@Test
	public void testUpdatePizzaNominal() {
		IPizzaDao dao = new PizzaDaoList();
		
		dao.saveNewPizza(null);
		dao.saveNewPizza(new Pizza(null, null, null, -1000.0));
		
		dao.updatePizza("PEP", new Pizza("POP", "Popop", CategoriePizza.VIANDE, 15.6));
		dao.updatePizza("ICI", new Pizza(null, null, null, -1000.0));
		dao.updatePizza("REI", null);

		assertEquals("POP", dao.findAllPizzas().get(0).getCode());
		assertEquals("Popop", dao.findAllPizzas().get(0).getLibelle());
		assertEquals(CategoriePizza.VIANDE, dao.findAllPizzas().get(0).getCategorie());
		assertEquals(15.6, dao.findAllPizzas().get(0).getPrix(), 0.0);

	}
	
	@Test(expected=UpdatePizzaException.class)
	public void testUpdatePizzaCodePizzaNull() throws UpdatePizzaException {
		
		new PizzaDaoList().updatePizza(null, new Pizza("POP", "Popop", CategoriePizza.VIANDE, 15.6));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUpdatePizzaPizzaNull() {
		
		new PizzaDaoList().updatePizza("REI", null);
	}

	@Test
	public void testDeletePizza() {
		fail("Not yet implemented");
	}

}
