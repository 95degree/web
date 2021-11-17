package web.webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.spring.DispatcherServlet;
import web.spring.RequestReader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private final Socket connection;
    private final DispatcherServlet dispatcherServlet;

    public RequestHandler(Socket connection, DispatcherServlet dispatcherServlet) {
        this.connection = connection;
        this.dispatcherServlet = dispatcherServlet;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            RequestReader requestReader = new RequestReader(bufferedReader);
            DataOutputStream dos = new DataOutputStream(out);
            dispatcherServlet.doService(requestReader.httpRequest(),dos);
            dos.flush();
        } catch (IOException | InvocationTargetException | IllegalAccessException
                | NoSuchMethodException | InstantiationException e) {
            log.error(e.getMessage());
        }
    }
}
