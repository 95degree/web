package spring;

import model.HttpMethod;

public class RequestInfo {
    private final String url;
    private final HttpMethod httpMethod;

    public RequestInfo(String url, HttpMethod httpMethod) {
        this.url = url;
        this.httpMethod = httpMethod;
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                "url='" + url + '\'' +
                ", httpMethod=" + httpMethod +
                '}';
    }
}
