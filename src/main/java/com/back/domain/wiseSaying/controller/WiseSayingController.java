package com.back.domain.wiseSaying.controller;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

public class WiseSayingController {
    WiseSayingService wiseSayingService;
    private static Scanner sc;

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
        wiseSayingService = new WiseSayingService();
    }

    public void addWiseSaying() {
        System.out.print("명언: ");
        String content = sc.nextLine().trim();
        System.out.print("작가: ");
        String author = sc.nextLine().trim();

        WiseSaying ws = wiseSayingService.addWiseSaying(content, author);
        System.out.println("%d번 명언이 추가되었습니다.".formatted(ws.getId()));
    }

    public void printWiseSayingList() {
        System.out.println("등록된 명언이 없습니다.");
    }
}
