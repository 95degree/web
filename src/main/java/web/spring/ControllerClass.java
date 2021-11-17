package web.spring;


import web.spring.model.RequestInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ControllerClass {

    private final Methods methods;

    public ControllerClass(Class<?> aClass) {
        this.methods = new Methods(aClass.getDeclaredMethods());
    }

    public Map<RequestInfo, Method> requestInfoMethodMap() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return methods.requestInfoMethodMap();
    }
}
