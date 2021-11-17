package spring.model;

import java.util.Map;

import static util.HttpRequestUtils.parseQueryString;

public class Body {

    private Map<String, String> body;

    private Body(Map<String, String> body) {
        this.body = body;
    }

    public static Body from(String body) {
        return new Body(parseQueryString(body));
    }
}
