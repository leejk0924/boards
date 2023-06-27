package com.boards.study_board.biz.service;

import com.boards.study_board.biz.repository.PostRepository;
import com.boards.study_board.dto.request.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public void save(PostRequest postRequest) {
        postRepository.save(postRequest.of(postRequest));
    }
}
