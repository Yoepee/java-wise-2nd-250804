package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    List<WiseSaying> wiseSayings = new ArrayList<>();
    private static int lastId = 0;

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

    public int getWiseSayingCount(String keywordType, String keyword) {
        boolean isSearch = keywordType != null && keyword != null;

        return (int) wiseSayings.stream()
                .filter(ws -> {
                    if (!isSearch) return true;
                    String field = keywordType.equals("content") ? ws.getContent() : ws.getAuthor();
                    return field.toLowerCase().contains(keyword.toLowerCase());
                })
                .count();
    }

    public List<WiseSaying> getWiseSayings(int offset, int limit, String keywordType, String keyword) {
        boolean isSearch = keywordType != null && keyword != null;

        return wiseSayings.stream()
                .filter(ws -> {
                    if (!isSearch) return true;
                    String field = keywordType.equals("content") ? ws.getContent() : ws.getAuthor();
                    return field.toLowerCase().contains(keyword.toLowerCase());
                })
                .sorted((a, b) -> b.getId() - a.getId())
                .skip(offset)
                .limit(limit)
                .toList();
    }
}
