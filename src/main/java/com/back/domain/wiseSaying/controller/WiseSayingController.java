package com.back.domain.wiseSaying.controller;

import com.back.AppContext;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

public class WiseSayingController {
    WiseSayingService wiseSayingService;
    private static Scanner sc;

    public WiseSayingController() {
        sc = AppContext.sc;
    }

    public void addWiseSaying() {
        System.out.print("명언: ");
        String content = sc.nextLine().trim();
        System.out.print("작가: ");
        String author = sc.nextLine().trim();

        int id = 0;
        System.out.println("%d번 명언이 추가되었습니다.".formatted(id));
    }

    public void printWiseSayingList() {
        System.out.println("등록된 명언이 없습니다.");
    }
}
