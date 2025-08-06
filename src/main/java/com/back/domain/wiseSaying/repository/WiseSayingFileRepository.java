package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.util.Util;

import java.util.Map;

public class WiseSayingFileRepository {
    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            int newId = getLastId()+1;
            wiseSaying.setId(newId);
            setLastId(newId);
        }

        Map<String, Object>wiseSayingMap = wiseSaying.toMap();
        String wiseSayingJson = Util.json.toString(wiseSayingMap);
        Util.file.set("db/wiseSaying/%d.json".formatted(wiseSaying.getId()), wiseSayingJson);
    }

    public WiseSaying findWiseSayingById(int id) {
        String wiseSayingJson = Util.file.get("db/wiseSaying/%d.json".formatted(id), "");
        if (wiseSayingJson.isBlank()) return null;
        Map<String, Object> wiseSayingMap = Util.json.toMap(wiseSayingJson);

        if (wiseSayingMap != null) {
            return new WiseSaying(wiseSayingMap);
        }
        return null;
    }

    public void delete(WiseSaying wiseSaying) {
        String filePath = "db/wiseSaying/%d.json".formatted(wiseSaying.getId());
        Util.file.delete(filePath);
    }

    public void setLastId(int lastId) {
        Util.file.set("db/wiseSaying/lastId.json", lastId);
    }

    public int getLastId() {
        int lastId = Util.file.getAsInt("db/wiseSaying/lastId.json", 0);
        return lastId;
    }
}
