package fr.pizzeria.model;

import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fr.pizzeria.annotation.ToString;
import fr.pizzeria.validator.Rule;

/** Représente une pizza
 * @author RB
 */
@Entity
public class Pizza {
	
	@Id
	private int id;
	
	@ToString(toUppercase=false)
	private String code;
	
	private String libelle;
	
	@Rule(min=0)
	@ToString(toUppercase=false)
	private double prix;
	
	@ToString(toUppercase=false)
	private CategoriePizza categorie;
	
	private static int currentId=0;
	
	
	/**
	 * Constructeur par défaut obligatoire
	 */
	public Pizza(){
		
	}
	
	/** Constructeur
	 * @param code code
	 * @param libelle libellé complet
	 * @param categ catégorie de pizza @see {@link CategoriePizza}
	 * @param prix prix en euros
	 */
	public Pizza(String code, String libelle, CategoriePizza categ, double prix) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.categorie = categ;
		this.prix = prix;
	}
	
	/** Constructeur
	 * @param id identifiant
	 * @param code code
	 * @param libelle libellé complet
	 * @param categ catégorie de pizza @see {@link CategoriePizza}
	 * @param prix prix en euros
	 */
	public Pizza(int id, String code, String libelle, CategoriePizza categ, double prix) {
		super();
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.categorie = categ;
		this.prix = prix;
	}
	
	public boolean equals(Object o){
		if (!(o instanceof Pizza)){
			return false;
		}
		Pizza other = (Pizza)o;
		
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(this.code, other.getCode()).append(this.libelle, other.getLibelle()).append(this.prix, other.getPrix());
		return builder.isEquals();
	}
	
	public int hashCode(){
		return new HashCodeBuilder().append(this.code).append(this.libelle).append(this.prix).toHashCode();
	}
	
	public String toString(){
		
		Class<Pizza> cl = Pizza.class;
		
		// Permet de récupérer la liste des attributs de la classe
		Field[] attributs = cl.getDeclaredFields();
		
		String chaine = "";
		
		try {
			// Boucle sur les attributs
			for (Field attr: attributs){
				
				// Permet de vérifier si une annotation est présente sur l'attribut
				if (attr.isAnnotationPresent(ToString.class)){
				
					// Je récupère l'annotation ToString
					ToString annotation = attr.getAnnotation(ToString.class);
					
					// Je récupère la valeur de la propriété toUpperCase de l'annotation
					boolean uppercase = annotation.toUppercase();
					String before = annotation.before();
					String after = annotation.after();
					
					// Récupération de la valeur de l'attribut pour l'instance courante
					Object value = attr.get(this);
					
					//TODO mettre en MAJ value si uppercase=true
					String valueStr = value.toString();
					if (uppercase){
						valueStr = valueStr.toUpperCase();
					}
					
					// Ajout de la valeur de l'attribut à la chaine de caractères
					chaine += valueStr + " ";
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/** Getter for categorie
	 * @return the categorie
	 */
	public CategoriePizza getCategorie() {
		return categorie;
	}

	/** Setter
	 * @param categorie the categorie to set
	 */
	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

}
