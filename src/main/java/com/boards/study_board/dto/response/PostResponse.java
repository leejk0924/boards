package com.boards.study_board.dto.response;

import java.time.LocalDate;

public record PostResponse(Long postId, String title, String content, String writer, Integer viewCnt, Boolean noticeYn, Boolean deleteYn, LocalDate createDate, LocalDate modifiedDate) {
}
