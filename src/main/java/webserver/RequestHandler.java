package webserver;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import db.DataBase;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.IOUtils;

import static util.HttpRequestUtils.parseQueryString;
import static util.StringUtils.*;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;
    private DataBase dataBase;

    public RequestHandler(Socket connectionSocket, DataBase dataBase) {
        this.connection = connectionSocket;
        this.dataBase = dataBase;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            DataOutputStream dos = new DataOutputStream(out);
            String line = bufferedReader.readLine();
            String url = extractPath(line);
            Map<String, String> header = new HashMap<>();
            if ("POST".equals(extractHttpMethod(line))) {
                while (!(line = bufferedReader.readLine()).equals("")) {
                    log.debug("line : {}", line);
                    String[] splitedLine = line.split(": ");

                    header.put(splitedLine[0], splitedLine[1]);
                }
                String body = IOUtils.readData(bufferedReader,Integer.parseInt(header.get("Content-Length")));
                User newUser = User.of(parseQueryString(body));
                dataBase.addUser(newUser);
                log.debug("newUser : {}", newUser);
                response302(dos);
                return;
            }
            File file = new File(PARENT_DIRECTORY + url);
            byte[] body = Files.readAllBytes(file.toPath());
            response200Header(dos, body.length);
            responseBody(dos, body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
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

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
