# 공연예술 소식 사이트
1. 주제: 콘서트, 영화 등 공연예술에 대한 정보를 얻고, 회원간 커뮤니티 기능이 있는 사이트

2. 설명: Restfull한 URL, Front컨트롤러에서 통일된 경로 처리, contextPath 경로 Cookie를 통한 alert 메시지 띄우기 및 삭제 (컨트롤러 URL 노출 차단)
즉, Front컨트롤러 버전 2와 유사한 MVC패턴으로 프로젝트 기획.

3. 느낀점: 첫 팀프로젝트를 수행해보니 설계와 팀원들간의 의사소통이 중요한 것을 실감했습니다. 특히, 각자의 파트가 유기적으로 공통되는 부분에서 의사의 일치와 소통이 중요하다고 깨달았습니다. 이번 팀 프로젝트를 통해 미흡했던 점은 다음과 같습니다.
   - 각자 이해한 내용이 맞는지 확인하기
   - 진행상황을 중간 공유하기
   - 유지 보수를 위한 설계 밑 코드의 통일성

4. 제작기간 / 참여인원: 2023.05.01 ~ 2023.06.07(약 5주) / 4명

5. 기술스택

<table>
  <tr>
    <td>Frontend</td>
    <td>JavaScript, Bootstrap, Ajax</td>
  </tr>
  <tr>
    <td>Backend</td>
    <td>Java, Jsp, Jstl, MySQL</td>
  </tr>
</table>

6. 개발환경

<table>
  <tr>
    <td>OS</td>
    <td>Window</td>
  </tr>
  <tr>
    <td>개발 환경 (IDE)</td>
    <td>Eclipse</td>
  </tr>
</table>

<br><br>

## ScreenShots

- 메인 페이지

포스터 클릭시 bootstrap을 이용해 모달창 열기

<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/76396755-174c-4211-967b-9a0d20fbf240" width="49%"></img>
<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/3d61661f-a55e-44bd-b7cb-875d93d02114" width="49%"></img>

<br><br>

- 로그인 화면 / 로그인 성공화면

메인관리자, 일반관리자, 회원을 구분하여 세션 할당
아이디 저장하기 쿠키 구현

<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/646c43c4-2a52-4242-b0fb-e72392bc721d" width="49%"></img>
<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/58423d74-d554-4705-b65a-fe3a9979a136" width="49%"></img>

<br><br>

- 회원가입

ajax를 이용한 아이디 중복검사

<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/425b3a95-1212-4e93-9c9b-bdf49ff22889" width="49%"></img>

<br><br>

- 회원관리 화면 / 조건검색 / 상태,등급 변경 / 회원정보

jQuery를 이용한 user DB 변경

<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/c5638d85-b116-4807-b0f1-172de70f6073" width="49%"></img>
<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/e5729926-dbea-4c72-9c3b-a9c48e3a9b5a" width="49%"></img>
<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/59c43b5a-24f9-448b-bb69-631bd57f78c6" width="49%"></img>
<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/6a890946-d00c-4017-b661-4bc4e5cc4794" width="49%"></img>

<br><br>

- 공연소식관리

카카오 맵 api를 이용한 공연 장소 구현 

<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/70ca4e56-4851-4e22-85eb-2052b7422753" width="49%"></img>
<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/37a9cd06-c3ff-4719-bb97-613495cd26e4" width="49%"></img>

<br><br>

- 커뮤니티 - 공지사항 게시판

1. 글 목록 페이지:   
jQuery를 이용한 일괄 공개,비공개,삭제 구현
DB의 게시글과 댓글 테이블 join을 이용한 댓글 수 표시
게시글 검색기능 구현

2. 첨부파일 업로드:   
Binary 형태로 DB에 직접 저장하는 방법과
웹 서버에 저장하는 방법 모두 구현

<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/40b7c38a-0018-45cb-a058-045032bf146f" width="49%"></img>
<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/c92839a2-b479-4535-a632-26191a098636" width="49%"></img>

<br><br>

- 커뮤니티 - 자유게시판

<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/3fb40d7c-d597-450b-be2b-945de2f16672" width="49%"></img>

- 커뮤니티 - 공연후기 게시판

<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/70b28577-97cf-49f3-99f4-5307060fb2d9" width="49%"></img>

- 커뮤니티 - 1:1 문의하기 게시판

<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/02edb76e-8755-4bfc-90a8-c6f0c15b06b7" width="49%"></img>

<br><br>

## ERD

- Full Shot

![Art_Info_ERD(Full)](https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/a79f4b2a-7099-40c3-a101-b6fb6c2aac2b)

- Top / Bottom Shot

<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/2781a4c2-4e87-4532-b0ab-d05b046ad423" width="48%"></img>
<img src="https://github.com/JasonTaeng/Art_info-Team_Project-/assets/134661987/35695ebd-4297-4f56-9b6c-956931cd06f4)" width="49%"></img>

# 
