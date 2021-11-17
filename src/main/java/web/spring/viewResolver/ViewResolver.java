package web.spring.viewResolver;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ViewResolver {

    //"./webapp";
    private static final String PREFIX = "./webapp/";
    private static final String REDIRECT_PREFIX = "redirect:";


    public DataOutputStream createView(String viewName, DataOutputStream dos) throws IOException {
        if (viewName.startsWith(REDIRECT_PREFIX)) {
            String redirectUrl = viewName.substring(REDIRECT_PREFIX.length());
            return create302(redirectUrl, dos);
        }
        return create200(viewName, dos);
    }

    private DataOutputStream create302(String redirectUrl, DataOutputStream dos) throws IOException {
        dos.writeBytes("HTTP/1.1 302 Found \r\n");
        dos.writeBytes("Location: " + redirectUrl + "\r\n");
        dos.writeBytes("\r\n");

        return dos;
    }

    private DataOutputStream create200(String viewName, DataOutputStream dos) throws IOException {
        byte[] view = findView(viewName);
        dos.writeBytes("HTTP/1.1 200 OK \r\n");
        dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
        dos.writeBytes("Content-Length: " + view.length + "\r\n");
        dos.writeBytes("\r\n");
        dos.write(view, 0, view.length);

        return dos;
    }

    private byte[] findView(String viewName) throws IOException {
        File file = new File(PREFIX + viewName);

        return Files.readAllBytes(file.toPath());
    }
}
