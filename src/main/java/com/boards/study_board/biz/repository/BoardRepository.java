package com.boards.study_board.biz.repository;

import com.boards.study_board.biz.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // update board_table set board_hits=board_hits+1 where id=?
    @Modifying
    @Query(value = "UPDATE BoardEntity B SET B.boardHits = B.boardHits+1 WHERE B.id=:id")
    void updateHits(@Param("id") Long id);
}
