package web.spring.model;


public class HttpRequest {
    private final HttpMethod httpMethod;
    private final Header header;
    private final String url;
    private final String body;

    public HttpRequest(HttpMethod httpMethod, Header header, String url, String body) {
        this.httpMethod = httpMethod;
        this.header = header;
        this.url = url;
        this.body = body;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "httpMethod=" + httpMethod +
                ", header=" + header +
                ", url='" + url + '\'' +
                '}';
    }
}

