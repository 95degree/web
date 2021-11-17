package spring;

import spring.model.RequestInfo;
import spring.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ControllerMethodAnnotations {
    private final Set<ControllerMethodAnnotation> controllerMethodAnnotations;

    public ControllerMethodAnnotations(Annotation[] controllerMethodAnnotation) {
        this.controllerMethodAnnotations = Arrays.stream(controllerMethodAnnotation)
                .filter(annotation -> annotation.annotationType().isAnnotationPresent(RequestMapping.class))
                .map(ControllerMethodAnnotation::new)
                .collect(Collectors.toSet());
    }

    public Set<RequestInfo> requestInfos() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Set<RequestInfo> requestInfos = new HashSet<>();
        for (ControllerMethodAnnotation annotationType : controllerMethodAnnotations) {
            requestInfos.add(annotationType.createRequestInfo());
        }
        return requestInfos;
    }
}
