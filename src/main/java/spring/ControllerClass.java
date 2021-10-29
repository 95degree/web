package spring;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ControllerClass {

    private final Class<?> aClass;
    private final Methods methods;

    public ControllerClass(Class<?> aClass) {
        this.aClass = aClass;
        this.methods = new Methods(aClass.getDeclaredMethods());
    }

    public Map<RequestInfo, Method> requestInfoMethodMap() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return methods.requestInfoMethodMap();
    }
}
