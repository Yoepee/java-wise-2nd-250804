package com.back;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SystemSayingControllerTest {
    @Test
    @DisplayName("종료")
    public void t1() {
        String rs = AppTestRunner.run("");

        assertThat(rs).contains("== 명언 앱 ==")
                .contains("명령)");
    }
}
