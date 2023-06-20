package com.boards.study_board.biz.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
/**
 * GenerationType : IDENTITY (PK 자동 증가), SEQUENCE(시퀀스 사용), AUTO(DB 에서 제공하는  PK 사용)
 */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID", length = 20, nullable = false)
    private Long postId;
    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;
    @Column(name = "CONTENT", length = 3000, nullable = false)
    private String content;
    @Column(name = "WRITER", length = 20, nullable = false)
    private String writer;
    @Column(name = "VIEW_CNT", length = 11, nullable = false)
    private int viewCnt;
    @Column(name = "NOTICE_YN", length = 1, nullable = false)
    private Boolean noticeYn;
    @Column(name = "DELETE_YN", length = 1, nullable = false)
    private Boolean deleteYn;
    @Column(name = "CREATED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;
    // TimeStamp 속성은 Default null 이 불가능함
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate;

}
