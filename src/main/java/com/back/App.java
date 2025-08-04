package com.back;

import com.back.domain.system.controller.SystemController;

import java.util.Scanner;

public class App {
    Scanner sc;
    SystemController systemController;

    App() {
        this.sc = AppContext.sc;
        this.systemController = AppContext.systemController;
    }

    public void run() {
        systemController.start();
        String input = """
                등록
                안녕!!
                """;

        Scanner scanner  = new Scanner(input);
        String cmd = scanner.nextLine();
        String content = scanner.nextLine();
        System.out.println("명령어: " + cmd);
        System.out.println("내용: " + content);
//        while(true){
//            String cmd = sc.nextLine();
//            System.out.println("명령어: " + cmd);
//            if(cmd.equals("종료")) break;
//        }

        systemController.stop();
    }
}
