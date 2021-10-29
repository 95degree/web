package model;


import spring.RequestInfo;

public class HttpRequest {
    private final HttpMethod httpMethod;
    private final Header header;
    private final String url;

    public HttpRequest(HttpMethod httpMethod,Header header, String url) {
        this.httpMethod = httpMethod;
        this.header = header;
        this.url = url;
    }

    public int getContentsLength() {
        return Integer.parseInt(header.getContentsLength());
    }

    public String getCookies(){
        return header.getCookies();
    }

    public HttpMethod getHttpMethod(){
        return httpMethod;
    }

    public String getUrl() {
        return url;
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

