package com.boards.study_board.biz.repository;

import com.boards.study_board.biz.domain.BoardEntity;
import com.boards.study_board.biz.domain.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);
}
