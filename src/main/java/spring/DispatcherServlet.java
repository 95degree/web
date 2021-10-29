package spring;

import model.HttpRequest;

import java.lang.reflect.Method;
import java.util.Map;

public class DispatcherServlet {

    private Map<RequestInfo, Method> handlers;

    public DispatcherServlet(Map<RequestInfo, Method> handlers) {
        this.handlers = handlers;
    }

    private Method getHandler(HttpRequest httpRequest){
        return handlers.get(httpRequest.toRequestInfo());
    }

    @Override
    public String toString() {
        return "RequestHandlerContainer{" +
                "handlers=" + handlers +
                '}';
    }
}
