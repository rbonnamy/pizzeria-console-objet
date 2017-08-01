package fr.pizzeria.model.pizza;

import java.lang.reflect.Field;

/** Représente une pizza
 * @author RB
 */
public class Pizza {
	
	private int id;
	
	@ToString(toUppercase=true)
	private String code;
	@ToString(toUppercase=false)
	private String nom;
	@ToString(toUppercase=false)
	private double prix;
	
	private static int currentId=0;
	
	/** Constructeur
	 * @param id identifiant
	 * @param code code
	 * @param nom nom complet
	 * @param prix prix en euros
	 */
	public Pizza(String code, String nom, double prix) {
		super();
		this.id = currentId++;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
	}
	
	public String toString(){
		String chaine = "";
		try {
			for (Field field: this.getClass().getDeclaredFields()){
				ToString annotation = field.getAnnotation(ToString.class);
				if (annotation!=null){
					boolean toUpperCase = annotation.toUppercase();
					if (toUpperCase){
						chaine+=" "+field.get(this).toString().toUpperCase();
					}
					else {
						chaine+=" "+field.get(this).toString();
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Illegal access to a field");
		} 
		return chaine;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public static void main(String[] args){
		Pizza pizza = new Pizza("CAL", "Calzone", 8.5);
		System.out.println(pizza);
	}

}
