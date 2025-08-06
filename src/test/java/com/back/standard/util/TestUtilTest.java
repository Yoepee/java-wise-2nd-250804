package com.back.standard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtilTest {
    @Test
    @DisplayName("TestUtil.genScanner() 테스트")
    public void t1() {
        Scanner scanner = TestUtil.genScanner("""
                종료
                """);

        String cmd = scanner.nextLine();

        assertThat(cmd).isEqualTo("종료");
    }

    @Test
    @DisplayName("TestUtil.genScanner() 테스트")
    public void t2() {
        Scanner scanner = TestUtil.genScanner("""
                등록
                나의 죽음을 적들에게 알리지 말라!
                이순신
                종료
                """);

        String cmd = scanner.nextLine();
        String content = scanner.nextLine();
        String author = scanner.nextLine();
        String cmd2 = scanner.nextLine();

        assertThat(cmd).isEqualTo("등록");
        assertThat(content).isEqualTo("나의 죽음을 적들에게 알리지 말라!");
        assertThat(author).isEqualTo("이순신");
        assertThat(cmd2).isEqualTo("종료");
    }

    @Test
    @DisplayName("TestUtil.genScanner() 테스트")
    public void t3() {
        Scanner scanner = TestUtil.genScanner("""
                등록
                나의 죽음을 적들에게 알리지 말라!
                이순신
                목록
                종료
                """);

        String cmd = scanner.nextLine();
        String content = scanner.nextLine();
        String author = scanner.nextLine();
        String cmd2 = scanner.nextLine();
        String cmd3 = scanner.nextLine();

        assertThat(cmd).isEqualTo("등록");
        assertThat(content).isEqualTo("나의 죽음을 적들에게 알리지 말라!");
        assertThat(author).isEqualTo("이순신");
        assertThat(cmd2).isEqualTo("목록");
        assertThat(cmd3).isEqualTo("종료");
    }
}
