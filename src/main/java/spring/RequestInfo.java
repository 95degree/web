package spring;

import model.HttpMethod;
import model.HttpRequest;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestInfo that = (RequestInfo) o;
        return Objects.equals(url, that.url) && httpMethod == that.httpMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, httpMethod);
    }
}
