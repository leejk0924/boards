# boards

## Git 커밋 컨벤션
### 1. 메시지 구조
기본적인 커밋 메시지 구조는 Header, Body, Footer로 나누고, 각 파트는 공백으로 구분한다.
```text
Header

Body

Footer
```

### 2. Header
커밋 타입은 다음과 같다.
```text
feat     : 기능 추가
fix      : 에러 및 버그 수정
Docs     : 문서 작성 및 수정
Style    : 
Test     : 테스트 코드
Refactor : 리팩토링
Comment  : 주석
Rename   : 파일 혹은 폴더명 수정
Remove   : 삭제 
```

### 3. Footer
이슈 트래커 유형은 다음과 같다.
- Fixes : 이슈 수정 중
- Resolves : 이슈 해결
- Ref : 참고할 이슈가 있을 경우
- Related to : 해당 커밋에 관련된 이슈번호 (아직 해결X)

여러개의 이슈를 적을 때는 쉼표(,) 사용



