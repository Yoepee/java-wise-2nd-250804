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

        while(true){
            String cmd = sc.nextLine();
            System.out.println("명령어: " + cmd);
            if(cmd.equals("종료")) break;
//            switch (cmd) {
//                case "종료"-> {
//                    systemController.stop();
//                    System.out.println("앱을 종료합니다.");
//                    return;
//                }
//                default -> {;
//                    String actionName = systemController.getCommand();
//                    if (actionName.equals("종료")) {
//                        systemController.stop();
//                        System.out.println("앱을 종료합니다.");
//                        return;
//                    }
//                    // 여기에 다른 명령어 처리 로직을 추가할 수 있습니다.
//                    System.out.println("알 수 없는 명령어입니다: " + actionName);
//                }
//            }
        }

        systemController.stop();
    }
}
