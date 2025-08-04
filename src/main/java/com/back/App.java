package com.back;

import com.back.domain.system.controller.SystemController;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class App {
    Scanner sc;
    SystemController systemController;

    App() {
        this.sc = AppContext.sc;
        this.systemController = AppContext.systemController;
    }

    public void run() {
        PrintStream ORIGINAL_OUT = System.out;
        systemController.start();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream, true);
        System.setOut(printStream);

        System.out.println("반갑습니다.");
        String input = """
                등록
                안녕!!
                """;

        Scanner scanner  = new Scanner(input);
        String cmd = scanner.nextLine();
        String content = scanner.nextLine();
        System.out.println("명령어: " + cmd);
        System.out.println("내용: " + content);
        String str1 = byteArrayOutputStream.toString();

        System.out.println("좋아요. 하하");
        String str2 = byteArrayOutputStream.toString();
        System.setOut(ORIGINAL_OUT);
        printStream.close();

        System.out.println("str1 : " + str1);
        System.out.println("str2 : " + str2);

        systemController.stop();
    }
}
