package com.navi.nbcampjavaspringcrudexample.api.model;

import lombok.Getter;

@Getter
public class ScheduleCreateRequest {
    private String title;
    private String contents;
    private Long memberId;
}
