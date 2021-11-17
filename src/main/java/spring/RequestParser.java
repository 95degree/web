package spring;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import model.Body;
import model.Header;
import model.HttpMethod;
import model.HttpRequest;
import util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;

import static util.StringUtils.split;

public class RequestParser {

    private final BufferedReader bufferedReader;
    private static final int HTTP_METHOD_INDEX = 0;
    private static final int URL_INDEX = 1;
    private static final String NULL_BODY = " ";

    public RequestParser(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public HttpRequest httpRequest() throws IOException {
        String[] requestLine = split(bufferedReader.readLine(), " ");
        HttpMethod httpMethod = HttpMethod.valueOf(requestLine[HTTP_METHOD_INDEX]);
        String url = requestLine[URL_INDEX];
        Header header = readHeader(bufferedReader);
        String body = NULL_BODY;
        if(httpMethod == HttpMethod.POST){
            body = IOUtils.readData(bufferedReader,header.getContentLength());
        }
        return new HttpRequest(httpMethod, header ,url, body);
    }

    private Header readHeader(BufferedReader bufferedReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String readLine;
        while (!(readLine = bufferedReader.readLine()).isEmpty()) {
            stringBuilder.append(readLine).append("\n");
        }
        return Header.from(stringBuilder.toString());
    }
}
