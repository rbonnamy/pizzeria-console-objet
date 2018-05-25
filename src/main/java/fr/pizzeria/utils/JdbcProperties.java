package fr.pizzeria.utils;

import java.util.ResourceBundle;

/** Singleton qui charge la configuration JDBC
 * @author DIGINAMIC
 */
public class JdbcProperties {

	/** bundle : ResourceBundle */
	private ResourceBundle bundle;
	
	/** properties : Properties */
	private static JdbcProperties properties;
	
	/**
	 * Constructeur
	 */
	private JdbcProperties(){
		bundle = ResourceBundle.getBundle("jdbc");
	}
	
	/** Retourne le singleton
	 * @return JdbcProperties
	 */
	public static JdbcProperties getInstance(){
		if (properties==null){
			properties = new JdbcProperties();
		}
		return properties;
	}
	
	/** Retourne une valeur de clé
	 * @param key clé
	 * @return String
	 */
	public String getString(String key){
		return bundle.getString(key);
	}
}
