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
                과거에 집착하지 마라.
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
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록
                """);

        assertThat(rs).contains("번호 / 작가 / 명언 / 작성일 / 수정일")
                .contains("2 / 작자미상 / 과거에 집착하지 마라. / ")
                .contains("1 / 작자미상 / 현재를 사랑하라. / ")
                .contains("-------------------------")
                .contains("페이지 : [1]");
    }

    @Test
    @DisplayName("명언 등록 후 삭제")
    public void t5() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=1
                목록
                """);

        assertThat(rs).contains("1번 명언이 삭제되었습니다.")
                .contains("2 / 작자미상 / 과거에 집착하지 마라. / ")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라. / ");
    }

    @Test
    @DisplayName("존재하지 않는 명언 삭제에 대한 예외처리")
    public void t6() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=1
                삭제?id=1
                """);

        assertThat(rs).contains("1번 명언이 삭제되었습니다.")
                .contains("1번 명언이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("수정?id=1")
    void t7 () {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=3
                수정?id=1
                현재와 자신을 사랑하라.
                홍길동
                목록
                  """);

        assertThat(rs)
                .contains("3번 명언이 존재하지 않습니다.")
                .contains("1 / 홍길동 / 현재와 자신을 사랑하라.")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("내용 키워드 검색")
    public void t8() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록?keywordType=content&keyword=과거
                """);

        assertThat(rs)
                .contains("2 / 작자미상 / 과거에 집착하지 마라. / ")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라. / ");
    }

    @Test
    @DisplayName("작가 키워드 검색")
    public void t9() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자상
                목록?keywordType=author&keyword=작자상
                """);

        assertThat(rs)
                .contains("2 / 작자상 / 과거에 집착하지 마라. / ")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라. / ");
    }

    @Test
    @DisplayName("잘못된 키워드 검색")
    public void t10() {
        String rs = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                목록?keywordType=contenttt&keyword=과거
                """);

        assertThat(rs).contains("keywordType는 'content' 또는 'author'만 가능합니다.")
                .doesNotContain("2 / 작자미상 / 과거에 집착하지 마라. / ")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라. / ");
    }

    @Test
    @DisplayName("목록?keyword=이순신")
    void t11() {
        String rs = AppTestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라.
                이순신
                등록
                진정한 해전의 왕은 이순신 뿐이다.
                넬슨제독
                목록?keyword=이순신
                """);

        assertThat(rs)
                .contains("2 / 넬슨제독 / 진정한 해전의 왕은 이순신 뿐이다.")
                .contains("1 / 이순신 / 나의 죽음을 적들에게 알리지 말라.");
    }
}
