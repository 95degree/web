package web.spring;

import web.spring.model.HttpMethod;
import web.spring.model.RequestInfo;
import web.spring.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControllerMethodAnnotation {

    private final Annotation annotation;

    public ControllerMethodAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public RequestInfo createRequestInfo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<? extends Annotation> annotationType = annotation.annotationType();
        RequestMapping requestMapping = annotationType.getAnnotation(RequestMapping.class);
        Method valueMethod = annotationType.getMethod("value");
        String url = (String) valueMethod.invoke(annotation);
        HttpMethod httpMethod = requestMapping.method();
        return new RequestInfo(url, httpMethod);
    }
}
