package controller;


import model.HttpMethod;
import model.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static model.HttpMethod.*;

public abstract class AbstractController implements HttpController {

    private static final Logger log = LoggerFactory.getLogger(AbstractController.class);

    @Override
    public void service(HttpRequest httpRequest, BufferedReader bufferedReader, DataOutputStream dos) throws IOException {
        HttpMethod httpMethod = httpRequest.getHttpMethod();
        if (httpMethod == POST) {
            doPost(httpRequest, bufferedReader, dos);
        } else if (httpMethod == GET) {
            doGet(httpRequest, bufferedReader, dos);
        }
    }

    public void doPost(HttpRequest httpRequest, BufferedReader bufferedReader, DataOutputStream dos) throws IOException{
    }

    public void doGet(HttpRequest httpRequest, BufferedReader bufferedReader, DataOutputStream dos) throws IOException {
        File file = new File("./webapp" + httpRequest.getUrl());
        byte[] body = Files.readAllBytes(file.toPath());
        response200Header(dos, body.length);
        responseBody(dos, body);
    }

    protected void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

     protected void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}

