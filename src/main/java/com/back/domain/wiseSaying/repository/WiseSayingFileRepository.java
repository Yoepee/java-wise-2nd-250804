package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.standard.util.Util;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class WiseSayingFileRepository {
    private String tableDirPath;

    public WiseSayingFileRepository() {
        this.tableDirPath = "db/wiseSaying";
    }
    public void save(WiseSaying wiseSaying) {
        if (wiseSaying.isNew()) {
            int newId = getLastId()+1;
            wiseSaying.setId(newId);
            setLastId(newId);
        }

        Map<String, Object>wiseSayingMap = wiseSaying.toMap();
        String wiseSayingJson = Util.json.toString(wiseSayingMap);
        Util.file.set(getEntityFilePath(wiseSaying.getId()), wiseSayingJson);
    }

    public WiseSaying findWiseSayingById(int id) {
        String wiseSayingJson = Util.file.get(getEntityFilePath(id), "");
        if (wiseSayingJson.isBlank()) return null;
        Map<String, Object> wiseSayingMap = Util.json.toMap(wiseSayingJson);

        if (wiseSayingMap != null) {
            return new WiseSaying(wiseSayingMap);
        }
        return null;
    }

    public void delete(WiseSaying wiseSaying) {
        String filePath = getEntityFilePath(wiseSaying.getId());
        Util.file.delete(filePath);
    }

    public void setLastId(int lastId) {
        Util.file.set(getLastIdFilePath(), lastId);
    }

    public int getLastId() {
        int lastId = Util.file.getAsInt(getLastIdFilePath(), 0);
        return lastId;
    }

    public String getEntityFilePath(int id) {
        return getTableDirPath()+"/%d.json".formatted(id);
    }
    public String getLastIdFilePath() {
        return getTableDirPath()+"/lastId.txt";
    }
}
