package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    private Scanner sc;
    private SystemController systemController;
    private WiseSayingController wiseSayingController;
    private static int PAGE_SIZE = 5;

    App(Scanner sc) {
        this.sc = sc;
        this.systemController = new SystemController(sc);
        this.wiseSayingController = new WiseSayingController(sc);
    }

    public void run() {
        systemController.start();

        while (true) {
            String cmd = systemController.getCommand();
            if (cmd.equals("종료")) break;
            switch (cmd) {
                case "종료" -> {
                    systemController.stop();
                    return;
                }
                case "등록" -> wiseSayingController.addWiseSaying();
                case "목록" -> {
                    wiseSayingController.printWiseSayingList(PAGE_SIZE, 1, null, null);
                }
//                case "빌드" -> wiseSayingController.build();
                case "삭제", "수정" -> {
//                    int id = systemController.getRq().getParamAsInt("id", -1);
//                    if (id == -1) {
//                        System.out.println("유효하지 않은 id 입니다.");
//                        continue;
//                    }
//                    if (cmd.equals("수정")) {
//                        AppContext.wiseSayingController.update(id);
//                    } else {
//                        AppContext.wiseSayingController.remove(id);
//                    }
                }
                default -> System.out.println("알 수 없는 명령입니다.");
            }
        }
    }
}
