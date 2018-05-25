package fr.pizzeria.utils;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.runtime.ArretApplicationException;

public class ClassUtils {

	public static IPizzaDao getInstance(String instanceName){
		try {
			
			Object obj = Class.forName(instanceName).newInstance();
			
			return (IPizzaDao)obj;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new ArretApplicationException(e.getMessage());
		}
	}
}
