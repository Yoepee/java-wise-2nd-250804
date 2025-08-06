package com.back.domain.wiseSaying.controller;

import com.back.AppContext;
import com.back.Rq;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;

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

    public void modifyWiseSaying(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) {
            System.out.println("유효하지 않은 id 입니다.");
            return;
        }

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

    public void removeWiseSaying(Rq rq) {
        int id = rq.getParamAsInt("id", -1);
        if (id == -1) {
            System.out.println("유효하지 않은 id 입니다.");
            return;
        }

        WiseSaying ws = wiseSayingService.removeWiseSaying(id);
        if (ws == null) {
            System.out.println("%d번 명언이 존재하지 않습니다.".formatted(id));
        } else {
            System.out.println("%d번 명언이 삭제되었습니다.".formatted(ws.getId()));
        }
    }

    public void printWiseSayingList(Rq rq) {
        String keywordType = rq.getParam("keywordType", null);
        String keyword = rq.getParam("keyword", null);
        if (keywordType!= null && !(keywordType.equals("content") || keywordType.equals("author"))) {
            System.out.println("keywordType는 'content' 또는 'author'만 가능합니다.");
            return;
        }

        int page = rq.getParamAsInt("page", 1);
        int pageSize = rq.getParamAsInt("pageSize", 5);
        Page<WiseSaying> wiseSayingPage = wiseSayingService.getWiseSayings(new Pageable(page, pageSize), keywordType, keyword);
        if (page < 1 || page > wiseSayingPage.getTotalPage()) {
            System.out.println("유효하지 않은 페이지입니다. (1 ~ %d)".formatted(wiseSayingPage.getTotalPage()));
            return;
        }

        if (wiseSayingPage.getTotalCount() == 0) {
            System.out.println("등록된 명언이 없습니다.");
            return;
        }

        boolean isSearch = keywordType != null && keyword != null;
        if (isSearch) {
            System.out.println("-------------------------");
            System.out.println("검색 타입: %s".formatted(keywordType));
            System.out.println("검색어: %s".formatted(keyword));
            System.out.println("-------------------------");
        }

        System.out.println("번호 / 작가 / 명언 / 작성일 / 수정일");
        System.out.println("-------------------------");
        for (WiseSaying ws : wiseSayingPage.getContents()) {
            System.out.println("%d / %s / %s / %s / %s".formatted(ws.getId(), ws.getAuthor(), ws.getContent(), ws.getCreateDate(), ws.getModifyDate()));
        }
        printWiseSayingPage(wiseSayingPage.getPageNumber(), wiseSayingPage.getTotalPage());
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
