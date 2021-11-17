package web.spring.model;

import java.util.Map;

import static web.util.HttpRequestUtils.parseQueryString;

public class Body {

    private Map<String, String> body;

    private Body(Map<String, String> body) {
        this.body = body;
    }

    public static Body from(String body) {
        return new Body(parseQueryString(body));
    }
}
