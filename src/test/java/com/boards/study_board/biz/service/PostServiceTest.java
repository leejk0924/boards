package com.boards.study_board.biz.service;

import com.boards.study_board.biz.domain.Post;
import com.boards.study_board.biz.repository.PostRepository;
import com.boards.study_board.dto.request.PostRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@DisplayName("PostServiceTest 의 ")
class PostServiceTest {
    @Autowired
    PostRepository postRepository;

    @Nested
    @DisplayName("save 성공 테스트")
    class test{
        @Test
        void success_save() throws IllegalAccessException, NoSuchFieldException {
            PostRequest postRequest = new PostRequest("게시글", "내용", "작가", true);
            Post post = postRequest.of(postRequest);
            postRepository.save(post);

            Post savedPost = postRepository.findById(2L).orElseThrow();

//            title.setAccessible(true);
//            assertThat("게시글").isEqualTo(savedPost);
        }
    }

}