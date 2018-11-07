package fr.pizzeria.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Annotation à placer sur un attribut à sérialiser en chaine de caractères
 * @author DIGINAMIC
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ToString {
	/** Indique si l'attribut doit être mis en majuscule ou non
	 * @return boolean
	 */
	boolean toUppercase();
	
	/** Indique la chaine de caractères à placer avant l'attribut
	 * @return String
	 */
	String before() default "";
	/** Indique la chaine de caractères à placer après l'attribut
	 * @return String
	 */
	String after() default "";
}
