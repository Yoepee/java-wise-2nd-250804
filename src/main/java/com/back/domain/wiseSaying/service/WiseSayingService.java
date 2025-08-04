package com.back.domain.wiseSaying.service;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

public class WiseSayingService {
    WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = AppContext.wiseSayingRepository;
    }

    public WiseSaying addWiseSaying(String content, String author) {
        WiseSaying ws = new WiseSaying(content, author);
        wiseSayingRepository.save(ws);
        return ws;
    }
}
