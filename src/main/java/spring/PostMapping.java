package spring;

import model.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@RequestMapping(method = HttpMethod.POST)
public @interface PostMapping {

    String value() default "";
}
