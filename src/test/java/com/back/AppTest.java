package com.back;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    @DisplayName("`== 명언 앱 ==` 출력")
    public void t1() {
        String rs = AppTestRunner.run("종료");

        assertThat(rs).contains("== 명언 앱 ==");
    }

    @Test
    @DisplayName("명언 등록 후 출력")
    public void t2() {
        String rs = AppTestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라!
                이순신
                종료
                """);

        assertThat(rs).contains("== 명언 앱 ==")
                .contains("명령)")
                .contains("명언:")
                .contains("작가:")
                .contains("1번 명언이 추가되었습니다.");
    }
}
