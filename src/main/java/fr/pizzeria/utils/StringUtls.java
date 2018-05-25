package fr.pizzeria.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import fr.pizzeria.annotation.ToString;

public class StringUtls {
	
	public static String traiteCaracteresSpeciaux(String chaine){
		return chaine;
	}
	
	public static String toString(Object bean){
		String chaine = "";
		try {
			
			Constructor constructor = bean.getClass().getConstructor();
			if (constructor!=null){
				Object b = constructor.newInstance("coucou");
			}
			
			for (Field field: bean.getClass().getDeclaredFields()){
				ToString annotation = field.getAnnotation(ToString.class);
				if (annotation!=null){
					boolean toUpperCase = annotation.toUppercase();
					if (toUpperCase){
						chaine+=" "+field.get(bean).toString().toUpperCase();
					}
					else {
						chaine+=" "+field.get(bean).toString();
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Illegal access to a field");
		} 
		return chaine;
	}

}
