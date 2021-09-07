package webserver;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import controller.AbstractController;
import controller.CreateUserController;
import controller.GetController;
import controller.LoginController;
import model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static util.StringUtils.*;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private static final Map<String, AbstractController> controllerMap;
    
    static {
        controllerMap = new HashMap<>();
        controllerMap.put("/user/create", new CreateUserController());
        controllerMap.put("/user/login", new LoginController());
        controllerMap.put("", new GetController());
    }

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = bufferedReader.readLine();
            String[] requestLine = split(line, " ");
            String url = requestLine[1];
            AbstractController controller = findController(url);
            DataOutputStream dos = new DataOutputStream(out);
            HttpMethod method = HttpMethod.valueOf(requestLine[0]);
            Header header = readHeader(bufferedReader);
            HttpRequest httpRequest = new HttpRequest(method, header, url);

            log.debug("http request  {}", httpRequest);

            controller.service(httpRequest, bufferedReader,dos);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private Header readHeader(BufferedReader bufferedReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String readLine;
        while (!(readLine = bufferedReader.readLine()).equals("")) {

            stringBuilder.append(readLine).append("\n");
        }
        return Header.from(stringBuilder.toString());
    }

    private AbstractController findController(String url) {
        Set<String> set = controllerMap.keySet();
        if(!set.contains(url)) {
            return controllerMap.get("");
        }

        return controllerMap.get(url);
    }
}
