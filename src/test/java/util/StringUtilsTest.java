package util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}