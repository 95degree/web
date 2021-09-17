package controller;

import model.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

import static util.HttpRequestUtils.*;

public class UserListController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Override
    public void doGet(HttpRequest httpRequest, BufferedReader bufferedReader, DataOutputStream dos) throws IOException {
        Map<String,String> cookies = parseCookies(httpRequest.getCookies());
        if(!Boolean.parseBoolean(cookies.get("logined"))) {
            requireLogin(dos);
        }
    }

    private void requireLogin(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            dos.writeBytes("Location: http://localhost:8080/login.html\r\n");
            dos.writeBytes("Set-Cookie: logined=false; Path=/");
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseUserList(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: \r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
