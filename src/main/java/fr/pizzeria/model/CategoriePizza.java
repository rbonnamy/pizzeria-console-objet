package fr.pizzeria.model;

public enum CategoriePizza {

	VIANDE("Viande"), 
	POISSON("Poisson"), 
	SANS_VIANDE("Sans viande");
	
	private String libelle;

	private CategoriePizza(String libelle){
		this.libelle = libelle;
	}
	
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
