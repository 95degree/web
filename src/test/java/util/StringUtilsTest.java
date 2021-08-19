package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;
import static util.StringUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {

    @Test
    @DisplayName("요청된 url에서 경로를 추출한다.")
    void parsePath() {
        //given
        String url = "GET /index.html HTTP 1.1";
        String expected = "./webapp/index.html";
        //when
        String actual = StringUtils.extractPath(url);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("GET이 요청된 url에서 쿼리를 추출한다")
    void parseQuery() {
        //given
        String url = "GET /user/create?userId=javajigi&password=password&name=12&email=javajigi40@slipp.net HTTP 1.1";
        String expected = "userId=javajigi&password=password&name=12&email=javajigi40@slipp.net";
        //when
        String actual = extractQuery(extractPath(url));
        //then
        assertThat(actual).isEqualTo(expected);
    }
}