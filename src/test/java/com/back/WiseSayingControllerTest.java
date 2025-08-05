package com.back;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {
    @Test
    @DisplayName("명언 등록 후 출력")
    public void t1() {
        String rs = AppTestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라!
                이순신
                """);

        assertThat(rs).contains("명언:")
                .contains("작가:");
    }

    @Test
    @DisplayName("등록시 생성된 명언번호 노출")
    public void t2() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(rs)
                .contains("번 명언이 추가되었습니다.");
    }

    @Test
    @DisplayName("등록시 생성된 명언번호 노출")
    public void t3() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(rs)
                .contains("1번 명언이 추가되었습니다.")
                .contains("2번 명언이 추가되었습니다.");
    }

    @Test
    @DisplayName("명언 등록 후 목록 출력")
    public void t4() {
        String rs = AppTestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라!
                이순신
                목록
                """);

        assertThat(rs).contains("번호 / 작가 / 명언 / 작성일 / 수정일")
                .contains(" / 이순신 / 나의 죽음을 적들에게 알리지 말라! / ")
                .contains("-------------------------")
                .contains("페이지 : [1]");
    }
}
