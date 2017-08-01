package fr.pizzeria.utils;

public class StringUtls {
	
	public static String traiteCaracteresSpeciaux(String chaine){
		return chaine.replace('é', '\u00E9');
	}

	public static void main(String[] args) {
		System.out.println(traiteCaracteresSpeciaux("Végétarienne"));

	}

}
