package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    List<WiseSaying> wiseSayings;
    private static int lastId;

    public WiseSayingRepository(){
        this.wiseSayings = new ArrayList<>();
        this.lastId = 0;
    }

    public void save(WiseSaying ws) {
        LocalDateTime now = LocalDateTime.now();

        if (ws.isNew()) {
            ws.setId(++lastId);
            ws.setCreateDate(now);
            ws.setModifyDate(now);
            wiseSayings.add(ws);
        } else {
            ws.setModifyDate(LocalDateTime.now());
        }
    }

    public WiseSaying remove(WiseSaying ws) {
        wiseSayings.remove(ws);
        return ws;
    }

    public int getWiseSayingCount(String keywordType, String keyword) {
        return (int) wiseSayings.stream()
                .filter(ws -> hasWiseSayingWithKeyword(ws, keywordType, keyword))
                .count();
    }

    public Page<WiseSaying> getWiseSayings(Pageable pageable, String keywordType, String keyword) {
        List<WiseSaying> wiseSayingList = wiseSayings.stream()
                .filter(ws -> hasWiseSayingWithKeyword(ws, keywordType, keyword))
                .sorted((a, b) -> b.getId() - a.getId())
                .skip((pageable.getPage() - 1) * pageable.getSize())
                .limit(pageable.getSize())
                .toList();
        int totalCount = getWiseSayingCount(keywordType, keyword);
        int totalPage = (int) Math.ceil((double) totalCount / pageable.getSize());

        return new Page<>(totalCount, totalPage, pageable.getPage(), pageable.getSize(), wiseSayingList);
    }

    public WiseSaying findWiseSayingById(int id) {
        return wiseSayings.stream()
                .filter(ws -> ws.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean hasWiseSayingWithKeyword(WiseSaying ws, String keywordType, String keyword) {
        // 검색 기반이 없는 경우
        boolean isSearch = keywordType != null && keyword != null;
        if (!isSearch) return true;

        // type 없이 키워드만 있은 경우
        boolean onlyKeyword = keywordType == null && keyword != null;
        if (onlyKeyword) {
            return ws.getContent().toLowerCase().contains(keyword.toLowerCase()) ||
                   ws.getAuthor().toLowerCase().contains(keyword.toLowerCase());
        }

        // type과 키워드가 모두 있는 경우
        String field = keywordType.equals("content") ? ws.getContent() : ws.getAuthor();
        return field.toLowerCase().contains(keyword.toLowerCase());
    }
}
