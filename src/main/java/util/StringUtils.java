package util;

public class StringUtils {
    public static final int REQUEST_LINE_PATH = 1;
    public static final String PARENT_DIRECTORY = "./webapp";

    public static String extractPath(String requestLine) {
        String[] split = requestLine.split(" ");
        return PARENT_DIRECTORY + split[REQUEST_LINE_PATH];
    }
}
