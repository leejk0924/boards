package com.boards.study_board.biz.repository;

import com.boards.study_board.biz.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
