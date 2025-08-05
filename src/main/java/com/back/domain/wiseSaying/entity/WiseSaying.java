package com.back.domain.wiseSaying.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
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
}
