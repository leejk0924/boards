package com.boards.study_board.biz.repository;

import com.boards.study_board.biz.domain.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
}
