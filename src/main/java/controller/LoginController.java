package controller;

import db.DataBase;
import model.HttpRequest;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.IOUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import static util.HttpRequestUtils.parseQueryString;

public class LoginController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Override
    public void doPost(HttpRequest httpRequest, BufferedReader bufferedReader, DataOutputStream dos) throws IOException {

        String body = IOUtils.readData(bufferedReader, httpRequest.getContentsLength());
        User loginUser = User.of(parseQueryString(body));

        User existingUser = DataBase.findUserById(loginUser.getUserId());

        if(!loginUser.equals(existingUser)){
            loginFail(dos);
            return;
        }

        loginSuccess(dos);
    }

    private void loginSuccess(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            dos.writeBytes("Location: http://localhost:8080/index.html\r\n");
            dos.writeBytes("Set-Cookie: logined=true; Path=/");
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void loginFail(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            dos.writeBytes("Location: http://localhost:8080/user/login_failed.html\r\n");
            dos.writeBytes("Set-Cookie: logined=false; Path=/");
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
