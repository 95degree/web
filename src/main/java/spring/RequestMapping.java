package spring;

import model.HttpMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
public @interface RequestMapping {

    String value() default "";

    HttpMethod method();
}
