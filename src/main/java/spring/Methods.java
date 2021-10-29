package spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Methods {

    private final Set<ControllerMethod> methods;

    public Methods(Method[] declaredMethods) {
        methods = Arrays.stream(declaredMethods)
                .map(ControllerMethod::new)
                .collect(Collectors.toSet());
    }

    public Map<RequestInfo, Method> requestInfoMethodMap() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<RequestInfo, Method> requestInfoMethodMap = new HashMap<>();
        for (ControllerMethod method : methods) {
            requestInfoMethodMap.putAll(method.requestInfoMethodMap());
        }
        return requestInfoMethodMap;
    }
}
