package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.util.Util;

import java.util.Map;

public class WiseSayingFileRepository {
    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            wiseSaying.setId(1);
        }

        Map<String, Object>wiseSayingMap = wiseSaying.toMap();
        String wiseSayingJson = Util.json.toString(wiseSayingMap);
        Util.file.set("db/wiseSaying/%d".formatted(wiseSaying.getId()), wiseSayingJson);
    }

    public WiseSaying findWiseSayingById(int id) {
        String wiseSayingJson = Util.file.get("db/wiseSaying/%d".formatted(id), null);
        Map<String, Object> wiseSayingMap = Util.json.toMap(wiseSayingJson);

        if (wiseSayingMap != null) {
            return new WiseSaying(wiseSayingMap);
        }
        return null;
    }
}
