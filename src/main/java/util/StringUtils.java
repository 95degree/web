package util;

import java.util.Map;

public class StringUtils {
    public static final int REQUEST_LINE_PATH = 1;
    public static final String PARENT_DIRECTORY = "./webapp";

    public static String extractHttpMethod(String requestLine) {
        String[] split = requestLine.split(" ");
        return split[0];
    }

    public static String extractPath(String requestLine) {
        String[] split = requestLine.split(" ");
        return PARENT_DIRECTORY + split[REQUEST_LINE_PATH];
    }

    public static String extractQuery(String requestLine) {
        String[] split = requestLine.split("\\?");
        return split[REQUEST_LINE_PATH];
    }
}
