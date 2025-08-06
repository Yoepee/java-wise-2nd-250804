package com.back.standard.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class Page<T> {
    private final int totalCount;
    private final int totalPage;
    private final int pageNumber;
    private final int pageSize;
    private final List<T> contents;
}
