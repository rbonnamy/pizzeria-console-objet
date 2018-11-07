package fr.pizzeria.dao;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.NotImplementedException;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.StringUtls;

/**
 * Implémentation mémoire
 * 
 * @author R.B.
 *
 */
public class PizzaDaoFichier implements IPizzaDao {

	/** PIZZAS_FILE_URL : String */
	private static final String PIZZAS_FILE_URL = "C:/Temp/mesPizzas.csv";

	/** DELIMITER : String */
	private static final String DELIMITER = ";";

	private List<Pizza> pizzas = new ArrayList<>();

	public PizzaDaoFichier() {
		initListe(); 
	}

	private void initListe() {
		pizzas = new ArrayList<>();
		try {
			List<String> lines = FileUtils.readLines(new File(PIZZAS_FILE_URL), Charset.forName("UTF-8"));

			for (String line : lines) {
				StringTokenizer decoupeur = new StringTokenizer(line, DELIMITER);
				String code = decoupeur.nextToken();
				String libelle = decoupeur.nextToken();
				String categStr = decoupeur.nextToken();
				String prixStr = decoupeur.nextToken();

				CategoriePizza categ = CategoriePizza.valueOf(categStr);

				Pizza pizza = new Pizza(code, libelle, categ, Double.parseDouble(prixStr));
				pizzas.add(pizza);
			}

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<Pizza> findAllPizzas() {
		
		return pizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {

		pizzas.add(pizza);

		// Appel de la méthode pour rafraichir le contenu du fichier
		storeFile();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {

		Pizza pizzaAModifier = findPizzaByCode(codePizza);

		pizzaAModifier.setCode(pizza.getCode());
		pizzaAModifier.setLibelle(pizza.getLibelle());
		pizzaAModifier.setPrix(pizza.getPrix());

		// Appel de la méthode pour rafraichir le contenu du fichier
		storeFile();
	}

	@Override
	public void deletePizza(String codePizza) {
		Pizza pizza = findPizzaByCode(codePizza);
		pizzas.remove(pizza);

		// Appel de la méthode pour rafraichir le contenu du fichier
		storeFile();
	}

	/**
	 * Méthode qui stocke le contenu de la liste dans un fichier
	 */
	private void storeFile() {
		try {
			File file = new File(PIZZAS_FILE_URL);

			for (int i=0; i<pizzas.size(); i++) {
				boolean append=true;
				if (i==0){
					append=false;
				}
				Pizza pizza = pizzas.get(i);
				// Construction de la chaine de caractères représentant une
				// pizza
				String chaine = StringUtls.traiteCaracteresSpeciaux(pizza.getCode() + DELIMITER + pizza.getLibelle())
						+ DELIMITER + pizza.getCategorie().name() + DELIMITER + pizza.getPrix() + "\n";
				FileUtils.writeStringToFile(file, chaine, Charset.forName("UTF-8"), append);
			}
			
			initListe();

		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Pizza findPizzaByCode(String code) {
		throw new NotImplementedException("Not implemented yet !");
	}

	@Override
	public boolean pizzaExists(String code) {
		throw new NotImplementedException("Not implemented yet !");
	}
}
