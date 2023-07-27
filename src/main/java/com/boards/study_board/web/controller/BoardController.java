package com.boards.study_board.web.controller;

import com.boards.study_board.biz.service.BoardService;
import com.boards.study_board.dto.request.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return "/index";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "/list";
    }

    /**
     * 해당 게시글의 조회수를 1증가, 게시글 데이터를 가져와 detail.html에 출력
     * @param id
     * @param model
     * @return /board/detail.html
     */
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "detail";
    }

    /**
     * 선택한 게시글의 id를 이용해 해당 게시글의 수정페이지(update.html) 출력
     * @param id
     * @param model
     * @return /board/update.html
     */
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "update";
    }

    /**
     * 패스워드 비교 후 동일한 경우 writer, title, contents update 후 detail 페이지 출력
     * 패스워드 기능으 추후 수정 예정
     * @param boardDTO
     * @param model
     * @return /board/detail.html
     */
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "detail";
    }

    /**
     * id값으로 식별하여 게시글 삭제
     * @param id
     * @return redirect:/board/
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board/";
    }
}
