package fr.pizzeria.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Pizza;

/**
 * Implémentation base de données.
 * 
 * create table pizzas (id int(10) AUTO_INCREMENT NOT NULL PRIMARY KEY, code
 * varchar(3) not null, libelle varchar(50) not null, prix decimal(5,2), id_cat
 * int(10) not null);
 * 
 * @author R.B.
 *
 */
public class PizzaDaoJpa extends AbstractDaoBase implements IPizzaDao {
	
	public static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria");
	}
	
	public PizzaDaoJpa() {
		
	}

	@Override
	public List<Pizza> findAllPizzas() {
		EntityManager em = entityManagerFactory.createEntityManager();
		TypedQuery<Pizza> query = em.createQuery("FROM Pizza", Pizza.class);
		List<Pizza> pizzas = query.getResultList();
		em.close();
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		em.persist(pizza);
		
		et.commit();
		em.close();
		
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		Pizza pizzaFromBase = findPizzaByCode(codePizza);
		
		pizzaFromBase.setCode(pizza.getCode());
		pizzaFromBase.setLibelle(pizza.getLibelle());
		pizzaFromBase.setPrix(pizza.getPrix());
		pizzaFromBase.setCategorie(pizza.getCategorie());
		
		et.commit();
		em.close();
		
	}

	@Override
	public void deletePizza(String codePizza) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		Pizza pizzaToRemove = findPizzaByCode(codePizza);
		em.remove(pizzaToRemove);
		
		et.commit();
		em.close();
	}

	@Override
	public boolean pizzaExists(String code) {
		return findPizzaByCode(code)!=null;
	}

	@Override
	public Pizza findPizzaByCode(String code) {
		EntityManager em = entityManagerFactory.createEntityManager();
		TypedQuery<Pizza> query = em.createQuery("FROM Pizza WHERE code=:code", Pizza.class);
		query.setParameter("code", code);
		em.close();
		
		Pizza pizzaFromBase = query.getSingleResult();
		return pizzaFromBase;
	}
}
