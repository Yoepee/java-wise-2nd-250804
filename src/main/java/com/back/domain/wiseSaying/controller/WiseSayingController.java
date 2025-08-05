package com.back.domain.wiseSaying.controller;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    WiseSayingService wiseSayingService;
    private static Scanner sc;

    public WiseSayingController() {
        this.sc = AppContext.sc;
        wiseSayingService = AppContext.wiseSayingService;
    }

    public void addWiseSaying() {
        System.out.print("명언: ");
        String content = sc.nextLine().trim();
        System.out.print("작가: ");
        String author = sc.nextLine().trim();

        WiseSaying ws = wiseSayingService.writeWiseSaying(content, author);
        System.out.println("%d번 명언이 추가되었습니다.".formatted(ws.getId()));
    }

    public void modifyWiseSaying(int id) {
        WiseSaying ws = wiseSayingService.findWiseSayingById(id);
        if (ws == null) {
            System.out.println("%d번 명언이 존재하지 않습니다.".formatted(id));
            return;
        } else {
            System.out.println("명언(기존) : %s".formatted(ws.getContent()));
            System.out.print("명언: ");
            String content = sc.nextLine().trim();
            System.out.println("작가(기존) : %s".formatted(ws.getAuthor()));
            System.out.print("작가: ");
            String author = sc.nextLine().trim();
            wiseSayingService.modifyWiseSaying(ws, content, author);
        }
    }

    public void removeWiseSaying(int id) {
        WiseSaying ws = wiseSayingService.removeWiseSaying(id);
        if (ws == null) {
            System.out.println("%d번 명언이 존재하지 않습니다.".formatted(id));
        } else {
            System.out.println("%d번 명언이 삭제되었습니다.".formatted(ws.getId()));
        }
    }

    public void printWiseSayingList(int pageSize, int page, String keywordType, String keyword) {
        int totalWiseSayingCount = wiseSayingService.getWiseSayingCount(keywordType, keyword);
        if (totalWiseSayingCount == 0) {
            System.out.println("등록된 명언이 없습니다.");
            return;
        }

        int totalPages = (int) Math.ceil((double) totalWiseSayingCount / pageSize);
        if (page < 1 || page > totalPages) {
            System.out.println("유효하지 않은 페이지입니다. (1 ~ %d)".formatted(totalPages));
            return;
        }

        boolean isSearch = keywordType != null && keyword != null;
        if (isSearch) {
            System.out.println("-------------------------");
            System.out.println("검색 타입: %s".formatted(keywordType));
            System.out.println("검색어: %s".formatted(keyword));
            System.out.println("-------------------------");
        }

        int offset = (page - 1) * pageSize;
        List<WiseSaying> wiseSayings = wiseSayingService.getWiseSayings(offset, pageSize, keywordType, keyword);
        System.out.println("번호 / 작가 / 명언 / 작성일 / 수정일");
        System.out.println("-------------------------");
        for (WiseSaying ws : wiseSayings) {
            System.out.println("%d / %s / %s / %s / %s".formatted(ws.getId(), ws.getAuthor(), ws.getContent(), ws.getCreateDate(), ws.getModifyDate()));
        }
        printWiseSayingPage(page, totalPages);
    }

    public void printWiseSayingPage(int page, int totalPages) {
        System.out.println("-------------------------");
        System.out.print("페이지 : ");
        for (int i = 1; i <= totalPages; i++) {
            System.out.printf((i == page ? "[%d] " : "%d "), i);
            System.out.print(i == totalPages ? "\n " : "/ ");
        }
    }
}
