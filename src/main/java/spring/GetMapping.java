package spring;

import model.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@RequestMapping(method = HttpMethod.GET)
public @interface GetMapping {

    String value() default "";
}
