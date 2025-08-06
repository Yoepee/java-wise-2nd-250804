package com.back.domain.wiseSaying.service;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.standard.dto.Page;
import com.back.standard.dto.Pageable;

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

    public WiseSaying modifyWiseSaying(WiseSaying ws, String content, String author) {
        ws.setContent(content);
        ws.setAuthor(author);
        wiseSayingRepository.save(ws);
        return ws;
    }

    public WiseSaying removeWiseSaying(int id) {
        WiseSaying wiseSaying = wiseSayingRepository.findWiseSayingById(id);
        if (wiseSaying == null) return null; // 해당 id의 명언이 존재하지 않음

        return wiseSayingRepository.remove(wiseSaying);
    }

    public int getWiseSayingCount(String keywordType, String keyword) {
        return wiseSayingRepository.getWiseSayingCount(keywordType, keyword);
    }
    public Page<WiseSaying> getWiseSayings(Pageable pageable, String keywordType, String keyword) {
        return wiseSayingRepository.getWiseSayings(pageable, keywordType, keyword);
    }
    public WiseSaying findWiseSayingById(int id) {
        return wiseSayingRepository.findWiseSayingById(id);
    }
}
