package com.boards.study_board.biz.repository;

import com.boards.study_board.biz.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
