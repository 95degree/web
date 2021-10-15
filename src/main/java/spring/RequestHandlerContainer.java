package spring;

import javafx.scene.effect.Reflection;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RequestHandlerContainer {

    Map<RequestInfo, Method> handlers;

    public RequestHandlerContainer() {
        this.handlers = createRequestHandlers();
    }

    private Map<RequestInfo,Method> createRequestHandlers(){

    }

    private Set<Class<?>> findControllerClasses(){
        Reflections reflections = new Reflections();
        return reflections.getTypesAnnotatedWith(Controller.class);
    }

    private Set<Method> findRequestHandlerMethods(Set<Class<?>> controllerClasses){
        Set<Method> methods = new HashSet<>();
        for (Class<?> controllerClass : controllerClasses) {
            Method[] declaredMethods = controllerClass.getDeclaredMethods();
            for(Method method : declaredMethods){
                if(method.isAnnotationPresent())
            }
        }
    }

}
