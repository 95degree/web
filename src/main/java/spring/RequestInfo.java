package spring;

import model.HttpMethod;
import model.HttpRequest;

public class RequestInfo {
    private final String url;
    private final HttpMethod httpMethod;

    public RequestInfo(String url, HttpMethod httpMethod) {
        this.url = url;
        this.httpMethod = httpMethod;
    }

    public static RequestInfo from (HttpRequest httpRequest){
        return new RequestInfo(httpRequest.getUrl(), httpRequest.getHttpMethod());
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                "url='" + url + '\'' +
                ", httpMethod=" + httpMethod +
                '}';
    }
}
