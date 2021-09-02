package controller;

import model.HttpRequest;

public interface HttpController {
    void service(HttpMethod httpMethod);
}
