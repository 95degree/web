package controller;

import model.HttpRequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public interface HttpController {
    void service(HttpRequest httpRequest, BufferedReader bufferedReader, DataOutputStream dos) throws IOException;
}
