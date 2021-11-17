package spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ControllerClasses {

    private final Set<ControllerClass> controllerClasses;

    public ControllerClasses(Set<Class<?>> classes) {
        this.controllerClasses = classes.stream()
                .map(ControllerClass::new)
                .collect(Collectors.toSet());
    }

    public Map<RequestInfo, Method> createRequestHandles() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<RequestInfo, Method> requestHandles = new HashMap<>();
        for (ControllerClass controllerClass : controllerClasses) {
            requestHandles.putAll(controllerClass.requestInfoMethodMap());
        }
        return requestHandles;
    }
}
