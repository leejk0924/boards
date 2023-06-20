package com.boards.study_board.web.controller.dto;

public record PostRequest(Long id, String title, String content, String writer, Boolean noticeYn){
/**
 * record 데이터 클래스
 * 1. 모든 필드는 final 로 정의되어 있고 각 필드의 getter 를 가지고 있음 (getter 는 필드명을 딴 메서드로 생성)
 * 2. record 클래스는 final 클래스라서 상속 불가
 * 3. 모든 필드를 초기화하는 생성자가 생성
 *
 * 자바 직렬화 하기 위해서는 필드 앞에 @JsonProperty 어노테이션을 붙여준다.
 */

}
