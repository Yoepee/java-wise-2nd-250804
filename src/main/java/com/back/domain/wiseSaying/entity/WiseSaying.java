package com.back.domain.wiseSaying.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WiseSaying {
    private int id;
    private String content;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public WiseSaying(String content, String author) {
        this.content = content;
        this.author = author;
        this.createDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
    }
    public WiseSaying(Map<String, Object> map) {
        this.id = (int) map.get("id");
        this.content = (String) map.get("content");
        this.author = (String) map.get("author");
        this.createDate = LocalDateTime.parse((String) map.get("createDate"), DATE_TIME_FORMAT);
        this.modifyDate = LocalDateTime.parse((String) map.get("modifyDate"), DATE_TIME_FORMAT);
    }

    public String getCreateDate() {
        return createDate.format(DATE_TIME_FORMAT);
    }
    public String getModifyDate() {
        return modifyDate.format(DATE_TIME_FORMAT);
    }

    public boolean isNew() {
        return id == 0;
    }

    public Map<String, Object> toMap() {
        return Map.of(
                "id", id,
                "content", content,
                "author", author,
                "createDate", getCreateDate(),
                "modifyDate", getModifyDate()
        );
    }
}
