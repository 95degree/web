package controller;

import model.HttpMethod;

public interface HttpController {
    void service(HttpMethod httpMethod);
}
