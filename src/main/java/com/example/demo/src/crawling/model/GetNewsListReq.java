package com.example.demo.src.crawling.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetNewsListReq {
    private int userIdx;
    private String keyword;
    private int page;
}
