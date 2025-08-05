package com.back.domain.wiseSaying.service;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = AppContext.wiseSayingRepository;
    }

    public WiseSaying writeWiseSaying(String content, String author) {
        WiseSaying ws = new WiseSaying(content, author);
        wiseSayingRepository.save(ws);
        return ws;
    }

    public int getWiseSayingCount(String keywordType, String keyword) {
        return wiseSayingRepository.getWiseSayingCount(keywordType, keyword);
    }
    public List<WiseSaying> getWiseSayings(int offset, int limit, String keywordType, String keyword) {
        return wiseSayingRepository.getWiseSayings(offset, limit, keywordType, keyword);
    }
}
