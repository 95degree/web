package spring;

import spring.model.HttpMethod;
import spring.model.HttpRequest;
import domain.User;
import spring.model.RequestInfo;
import org.reflections.Reflections;
import spring.annotation.Controller;
import spring.viewResolver.ViewResolver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static util.HttpRequestUtils.parseQueryString;

public class DispatcherServlet {

    private Map<RequestInfo, Method> handlers;
    private Map<String, Object> beans;
    private ViewResolver viewResolver;

    private DispatcherServlet() {
        init();
    }

    private void init() {
        Set<Class<?>> classes = searchControllerClass();
        ControllerClasses controllerClasses = new ControllerClasses(classes);
        beans = createBeans(classes);
        initViewResolver();
        try {
            handlers = controllerClasses.createRequestHandles();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void initViewResolver() {
        viewResolver = new ViewResolver();
    }

    private Set<Class<?>> searchControllerClass() {
        Reflections reflections = new Reflections("controller");
        return reflections.getTypesAnnotatedWith(Controller.class);
    }

    private Map<String, Object> createBeans(Set<Class<?>> classes) {
        Map<String, Object> beans = new HashMap<>();
        for (Class<?> aClass : classes) {
            try {
                beans.put(aClass.getName(), aClass.getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException
                    | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return beans;
    }

    public void doService(HttpRequest httpRequest, DataOutputStream dos) throws InvocationTargetException, IllegalAccessException, IOException, NoSuchMethodException, InstantiationException {
        Method method = getHandler(httpRequest);
        User user = null;
        String viewName;
        Object beanClass = beans.get(method.getDeclaringClass().getName());
        if (httpRequest.getHttpMethod() == HttpMethod.POST) {
            user = User.from(parseQueryString(httpRequest.getBody()));
            viewName = String.valueOf(method.invoke(beanClass, user));
        } else {
            viewName = String.valueOf(method.invoke(beanClass));
        }
        viewResolver.createView(viewName, dos);
    }

    private Method getHandler(HttpRequest httpRequest) {
        return handlers.get(RequestInfo.from(httpRequest));
    }

    private static class SingletonHelper {
        private static final DispatcherServlet INSTANCE = new DispatcherServlet();
    }

    public static DispatcherServlet getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
