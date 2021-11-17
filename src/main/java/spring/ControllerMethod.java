package spring;

import spring.model.RequestInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerMethod {
    private final Method method;
    private final ControllerMethodAnnotations controllerMethodAnnotations;

    public ControllerMethod(Method method) {
        this.method = method;
        this.controllerMethodAnnotations = new ControllerMethodAnnotations(method.getDeclaredAnnotations());
    }

    public Map<RequestInfo, Method> requestInfoMethodMap() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<RequestInfo, Method> requestInfoMethodMap = new HashMap<>();
        Set<RequestInfo> requestInfos = controllerMethodAnnotations.requestInfos();
        for (RequestInfo requestInfo : requestInfos) {
            requestInfoMethodMap.put(requestInfo, method);
        }
        return requestInfoMethodMap;
    }
}
