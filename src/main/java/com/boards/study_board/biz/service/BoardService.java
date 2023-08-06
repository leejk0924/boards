package com.boards.study_board.biz.service;

import com.boards.study_board.biz.domain.BoardEntity;
import com.boards.study_board.biz.domain.BoardFileEntity;
import com.boards.study_board.biz.repository.BoardFileRepository;
import com.boards.study_board.biz.repository.BoardRepository;
import com.boards.study_board.dto.request.BoardDTO;
import io.micrometer.common.util.internal.logging.Slf4JLoggerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    private final Logger logger = LoggerFactory.getLogger(Slf4JLoggerFactory.class);

    public void save(BoardDTO boardDTO) throws IOException {
        if (boardDTO.getBoardFile().isEmpty()) {
            // 첨부파일 없을 경우
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity);
        } else {
            // 첨부파일 있을 경우
            MultipartFile boardFile = boardDTO.getBoardFile();
            String originalFilename = boardFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "-" + originalFilename;
            String savePath = "//Users/jk/jk/board/" + storedFileName;
            boardFile.transferTo(new File(savePath));
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
            Long saveId = boardRepository.save(boardEntity).getId();
            BoardEntity board = boardRepository.findById(saveId).get();
            BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFilename, storedFileName);
            boardFileRepository.save(boardFileEntity);
        }
    }
    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        ArrayList<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }
    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    @Transactional
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            return BoardDTO.toBoardDTO(optionalBoardEntity.get());
        } else {
            return null;
        }
    }
    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        // 한페이지당 보여줄 글의 갯수, 정렬 기준은 id 기준으로 내림차순 정렬
        int pageLimit = 3;
        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        logger.info("boardEntity.getContent() = {}", boardEntities.getContent());
        logger.info("boardEntity.getTotalElements = {}", boardEntities.getTotalElements());
        logger.info("boardEntity.getNumber() = {}", boardEntities.getNumber());
        logger.info("boardEntity.getTotalPages() = {}", boardEntities.getTotalPages());
        logger.info("boardEntity.getSize() = {}", boardEntities.getSize());
        logger.info("boardEntity.hasPrevious() = {}", boardEntities.hasPrevious());
        logger.info("boardEntity.isFirst() = {}", boardEntities.isFirst());
        logger.info("boardEntity.isLast() = {}", boardEntities.getContent());
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> BoardDTO.builder()
                .id(board.getId())
                .boardWriter(board.getBoardWriter())
                .boardTitle(board.getBoardTitle())
                .boardHits(board.getBoardHits())
                .boardCreatedTime(board.getCreatedTime()).build());
        return boardDTOS;
    }
}
