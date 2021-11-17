package web.spring.model;

import java.util.Map;

import static web.util.HttpRequestUtils.parseHeaders;

public class Header {
    private Map<String,String> header;
    private static final String CONTENT_LENGTH = "Content-Length";

    private Header(Map<String, String> header) {
        this.header = header;
    }

    public static Header from(String headerLines){
        return new Header(parseHeaders(headerLines));
    }

    public int getContentLength(){
        return Integer.parseInt(header.get(CONTENT_LENGTH));
    }
}
