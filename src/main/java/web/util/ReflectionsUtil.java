package web.util;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import web.spring.annotation.Controller;

import java.util.Set;

public class ReflectionsUtil {
    public static Set<Class<?>> findControllerClasses() {
        Reflections reflections = new Reflections(new ConfigurationBuilder().addUrls(ClasspathHelper.forPackage("web.controller")));
        return reflections.getTypesAnnotatedWith(Controller.class);
    }
}
