package controller;

import model.HttpMethod;
import static model.HttpMethod.*;

public abstract class AbstractController implements HttpController {

    @Override
    public void service(HttpMethod httpMethod) {
        if (httpMethod == POST) {
            doPost();
        } else if (httpMethod == GET) {
            doGet();
        }
    }

    public void doPost(HttpRequest httpRequest, BufferedReader bufferedReader, DataOutputStream dos) throws IOException{
    }

    abstract void doGet();
}

