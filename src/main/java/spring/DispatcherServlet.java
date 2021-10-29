package spring;

import java.lang.reflect.Method;
import java.util.Map;

public class DispatcherServlet {

    private Map<RequestInfo, Method> handlers;

    public DispatcherServlet(Map<RequestInfo, Method> handlers) {
        this.handlers = handlers;
    }

    @Override
    public String toString() {
        return "RequestHandlerContainer{" +
                "handlers=" + handlers +
                '}';
    }
}
