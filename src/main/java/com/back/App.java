package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;

public class App {
    private SystemController systemController;
    private WiseSayingController wiseSayingController;

    App() {
        this.systemController = AppContext.systemController;
        this.wiseSayingController = AppContext.wiseSayingController;
    }

    public void run() {
        systemController.start();

        while (true) {
            Rq rq = systemController.getCommand();
            String actionName = rq.getActionName();
            switch (actionName) {
                case "종료" -> {
                    systemController.stop();
                    return;
                }
                case "등록" -> wiseSayingController.addWiseSaying();
                case "목록" -> wiseSayingController.printWiseSayingList(rq);
                case "삭제" -> wiseSayingController.removeWiseSaying(rq);
                case "수정" -> wiseSayingController.modifyWiseSaying(rq);
                default -> System.out.println("알 수 없는 명령입니다.");
            }
        }
    }
}
