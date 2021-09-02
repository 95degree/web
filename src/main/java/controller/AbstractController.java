package controller;


import model.HttpMethod;
import model.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

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

    public void doGet(HttpRequest httpRequest, BufferedReader bufferedReader, DataOutputStream dos) throws IOException{
    }
}

