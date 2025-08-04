package com.back.domain.wiseSaying.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WiseSaying {
    private int id;
    private String content;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public WiseSaying(String content, String author) {
        this.content = "";
        this.author = "";
    }

    public int getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public String getAuthor() {
        return author;
    }
    public String getCreateDate() {
        return createDate.format(DATE_TIME_FORMAT);
    }
    public String getModifyDate() {
        return modifyDate.format(DATE_TIME_FORMAT);
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public boolean isNew() {
        return id == 0;
    }
}
