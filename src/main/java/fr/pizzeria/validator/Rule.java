package fr.pizzeria.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Rule {

	int length() default Integer.MAX_VALUE;
	int min() default Integer.MIN_VALUE;
	int max() default Integer.MAX_VALUE;
	boolean nullable() default false;
	String matchRegExp() default ".*";
}
