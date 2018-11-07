package fr.pizzeria.model;

/** Représente la catégorie d'une pizza
 * @author DIGINAMIC
 */
public enum CategoriePizza {

	/** VIANDE */
	VIANDE("Viande"), 
	/** POISSON */
	POISSON("Poisson"), 
	/** SANS_VIANDE */
	SANS_VIANDE("Sans viande");
	
	/** libellé de la catégorie */
	private String libelle;

	/** Constructeur
	 * @param libelle libellé
	 */
	private CategoriePizza(String libelle){
		this.libelle = libelle;
	}
	
	/** Retourne une catégorie en fonction d'un numéro saisi par l'utilisateur
	 * @param numero numéro de catégorie
	 * @return {@link CategoriePizza}
	 */
	public static CategoriePizza get(int numero){
		if (numero==1){
			return VIANDE;
		}
		else if (numero==2){
			return SANS_VIANDE;
		}
		return POISSON;
	}
	
	/** Getter for libelle
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	
	/** Indique si oui ou non la catégorie dont le nom est passé en paramètre existe ou non
	 * @param nom nom de la catégorie
	 * @return boolean
	 */
	public static boolean exists(String chaine){
		CategoriePizza[] categs = values();
		
		for (int i=0; i<categs.length; i++){
			if (categs[i].name().equals(chaine)){
				return true;
			}
		}
		return false;
	}
}
