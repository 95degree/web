package webserver;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import controller.AbstractController;
import controller.CreateUserController;
import db.DataBase;
import model.HttpMethod;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static util.StringUtils.*;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private static final Map<String, AbstractController> controllerMap;
    
    static {
        controllerMap = new HashMap<>();
        controllerMap.put("/user/create", new CreateUserController());
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
            String url = requestLine[1];
            AbstractController controller = controllerMap.get(url);
            controller.service(method);

            //FIXME
            Map<String, String> header = new HashMap<>();
            DataOutputStream dos = new DataOutputStream(out);
            if ("POST".equals(requestLine)) {//Request Line에서 메서드 추출
                while (!(line = bufferedReader.readLine()).equals("")) {//http 메세지의 header를 map에 담는다.
                    log.debug("line : {}", line);
                    String[] splitedLine = line.split(": ");

                    header.put(splitedLine[0], splitedLine[1]);//Ket = content-length ,  value = 길이 를 담는다
                }

                String body = IOUtils.readData(bufferedReader,Integer.parseInt(header.get("Content-Length")));//길이만큼 유저정보 읽어서 body에 담는다
                User newUser = User.of(parseQueryString(body));//담은 body로 유저 생성
                DataBase.addUser(newUser);//넣고
                log.debug("newUser : {}", newUser);
                response302(dos);//응답
                return;
            }

        // String RquestLine; -> /create, /login 분리 & httomethod 분리 -> 해당하는 controller 호출(HttoMethod) ->  각 컨트롤러가 가진 response메서드 사용
        //  GET/POST/PUT 분리 -> POST/creat ->


            File file = new File(PARENT_DIRECTORY + url);
            byte[] body = Files.readAllBytes(file.toPath());
            response200Header(dos, body.length);
            responseBody(dos, body);
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
