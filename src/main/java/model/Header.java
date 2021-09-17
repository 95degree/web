package model;

import java.util.Map;

import static util.HttpRequestUtils.parseHeaders;

public class Header {
    private Map<String,String> header;

    private Header(Map<String, String> header) {
        this.header = header;
    }

    public static Header from(String headerLines){
        return new Header(parseHeaders(headerLines));
    }

    public String getContentsLength(){
        return header.get("Content-Length");
    }

    public String getCookies(){
        return header.get("Cookie");
    }
}
