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
}
