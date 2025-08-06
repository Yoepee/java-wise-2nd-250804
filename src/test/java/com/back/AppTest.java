package com.back;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    @DisplayName("없는 명령어")
    public void t1() {
        String rs = AppTestRunner.run("없는명령어");

        assertThat(rs).contains("알 수 없는 명령입니다.");
    }
}
