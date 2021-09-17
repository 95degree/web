package controller;

import db.DataBase;
import model.HttpRequest;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.IOUtils;

import java.io.*;
import java.util.Collections;

import static util.HttpRequestUtils.parseQueryString;

public class CreateUserController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    public void doPost(HttpRequest httpRequest, BufferedReader bufferedReader, DataOutputStream dos) throws IOException {
        String body = IOUtils.readData(bufferedReader, httpRequest.getContentsLength());
        User newUser = User.of(parseQueryString(body));
        DataBase.addUser(newUser);
        response302(dos);
    }

    private void response302(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            dos.writeBytes("Location: http://localhost:8080/index.html\r\n");
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
           log.error(e.getMessage());
        }
    }
}
