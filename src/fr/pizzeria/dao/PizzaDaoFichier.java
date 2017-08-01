package fr.pizzeria.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.pizza.Pizza;
import fr.pizzeria.utils.StringUtls;

/** Implémentation mémoire
 * @author R.B.
 *
 */
public class PizzaDaoFichier implements IPizzaDao {
	
	/** PIZZAS_FILE_URL : String */
	private static final String PIZZAS_FILE_URL = "C:/Temp/mesPizzas.csv";

	/** DELIMITER : String */
	private static final String DELIMITER = ";";
	
	private List<Pizza> pizzas = new ArrayList<>();

	public PizzaDaoFichier(){
		try {
			File file = new File(PIZZAS_FILE_URL);
			if (file.exists()){	
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				
				String line = null;
				while ((line=br.readLine())!=null){
					StringTokenizer decoupeur = new StringTokenizer(line, DELIMITER);
					String code = decoupeur.nextToken();
					String libelle = decoupeur.nextToken();
					String prixStr = decoupeur.nextToken();
					
					Pizza pizza = new Pizza(code, libelle, Double.parseDouble(prixStr));
					pizzas.add(pizza);
				}
				
				br.close();
				fr.close();
			}
			
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Pizza[] findAllPizzas() {
		Pizza[] tab = new Pizza[pizzas.size()];
		return pizzas.toArray(tab);
	}

	@Override
	public boolean saveNewPizza(Pizza pizza) throws SavePizzaException {
		
		pizzas.add(pizza);
		
		// Appel de la méthode pour rafraichir le contenu du fichier
		storeFile();
		
		return false;
	}

	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		
		Pizza pizzaAModifier = getPizzaFromCode(codePizza);
		if (pizzaAModifier==null){
			throw new UpdatePizzaException("Le code de la pizza à modifier n'existe pas");
		}

		pizzaAModifier.setCode(pizza.getCode());
		pizzaAModifier.setNom(pizza.getNom());
		pizzaAModifier.setPrix(pizza.getPrix());
		
		// Appel de la méthode pour rafraichir le contenu du fichier
		storeFile();
		return false;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DeletePizzaException {
		Pizza pizza = getPizzaFromCode(codePizza);
		if (pizza==null){
			throw new DeletePizzaException("Le code de la pizza à supprimer n'existe pas");
		}
		pizzas.remove(pizza);
		
		// Appel de la méthode pour rafraichir le contenu du fichier
		storeFile();
		return false;
	}

	@Override
	public boolean pizzaExists(String code) {
		return getPizzaFromCode(code)!=null;
	}
	
	/**
	 * Méthode qui stocke le contenu de la liste dans un fichier
	 */
	private void storeFile() {
		try {
			File file = new File(PIZZAS_FILE_URL);
			FileWriter writer = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			
			for (Pizza pizza: pizzas){
				
				// Construction de la chaine de caractères représentant une pizza
				String chaine = StringUtls.traiteCaracteresSpeciaux(pizza.getCode()+DELIMITER+pizza.getNom())+DELIMITER+pizza.getPrix()+"\n";
				
				// Ecriture de la chaine dans le fichier
				bufferedWriter.write(chaine);
			}
			
			bufferedWriter.flush();
			bufferedWriter.close();
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * Retourne l'index dans le tableau de la pizza dont le code est passé en
	 * paramètre
	 * 
	 * @param pizzas
	 *            tableaux de pizza
	 * @param code
	 *            code de la pizza recherch�e
	 * @return int
	 */
	protected Pizza getPizzaFromCode(String code) {
		for (int index = 0; index < pizzas.size(); index++) {
			if (pizzas.get(index).getCode().equals(code)) {
				return pizzas.get(index);
			}
		}
		return null;
	}

}
