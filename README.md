■ 목차
1. 개요
2. 설명
3. API
4. ERD
5. SQL
6. 사용 방식
7. 폴더 설명

■ 개요
- 프로젝트 이름: 일정관리 앱 만들기
- 프로젝트 기간: 2025.03.19 - 2025.03.24
- 개발 언어: Spring Boot

■ 설명
: 효율적인 일정 관리를 위해 개인의 일정을 등록하고 관리할 수 있는 앱
 
■ API
1) 작성자 등록	POST(/users)		
: 요청 body
{
    "user_id": "id4",
    "user_pw": "pw4",
    "user_name": "name4",
    "user_email": "email4",
    "age":35,
    "job":"marketing"
}	등록 정보	201: Created

2) 일정 등록	POST(/schedules)		
: 요청 body
{
    "title": "제목1",
    "contents" : "내용1",
    "user_name" : "사용자1",
    "user_id" : "id1",
    "user_pw" : "1234"
}	등록 정보	201: 정상 등록

3) 일정 조회  GET(/schedules/id/{id})
: 요청 body
{
    "id": "{id}",
}	조회 정보	200: 정상 조회

4) 일정 목록 조회  GET(/schedules)
: 요청 body 없음
조회 정보	200: 정상 조회

5) 일정 수정  PATCH(/schedules/{id})
: 요청 body
{
    "contents" : "수정된 내용입니다.",
    "user_name" : "수정된 사용자입니다.",
    "user_pw" : "1234"
} 수정 정보	200: 정상 수정

6) 일정 삭제 DEL(/schedules/{id})
: 요청 body
{
    "user_pw": "1234",
}	조회 정보	200: 정상 삭제

■ ERD
classDiagram
direction BT
class schedule {
   varchar(500) title  /* 제목 */
   varchar(500) contents
   varchar(100) user_id  /* 사용자 id */
   varchar(100) user_pw  /* 사용자 pw */
   varchar(100) user_name  /* 사용자 이름 */
   date create_date  /* 작성일 */
   date update_date  /* 수정일 */
   bigint id  /* 일정 식별자 */
}
class user {
   varchar(100) user_id  /* 사용자 id */
   varchar(100) user_pw  /* 사용자 pw */
   varchar(100) user_name  /* 사용자 이름 */
   varchar(100) user_email  /* 사용자 이메일 */
   int age  /* 나이 */
   varchar(50) job
   date create_date  /* 작성일 */
   date update_date  /* 수정일 */
   varchar(50) id  /* 사용자 식별자 */
}

schedule  -->  user : user_id:id
user  -->  user : user_id:id

■ SQL
-- 테이블 생성 user
CREATE TABLE user
(
    id VARCHAR(50) PRIMARY KEY DEFAULT (UUID()) COMMENT '사용자 식별자',
    user_id VARCHAR(100) COMMENT	'사용자 id',
    user_pw VARCHAR(100) COMMENT '사용자 pw',
    user_name VARCHAR(100) COMMENT '사용자 이름',
    user_email VARCHAR(100) COMMENT '사용자 이메일',
    age INT COMMENT '나이',
    job VARCHAR(10) COMMENT '직업',
    create_date DATE COMMENT '작성일',
    update_date DATE COMMENT '수정일'
);

-- 테이블 생성 (schedule)
CREATE TABLE schedule
(	
	id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자', 
	title VARCHAR(500) NOT NULL COMMENT '제목',
	contents VARCHAR(500) NOT NULL COMMENT '내용',
	user_id VARCHAR(100) NOT NULL COMMENT '사용자 id',
	user_pw VARCHAR(100) NOT NULL COMMENT '사용자 pw',
	user_name VARCHAR(100) NOT NULL COMMENT '사용자 이름',
	create_date DATE COMMENT '작성일',
	update_date DATE COMMENT '수정일'
);

■ 사용 방식
1) 프로그램 실행
2) 사용자 등록 (API 참조)
3) 일정 등록 (API 참조)
4) 일정 조회 (API 참조)
5) 일정 수정 (API 참조)
6) 일정 삭제 (API 참조)

■폴더 설명
- controller: 일정(schedule)과 사용자(user)의 Response를 Service에 처리를 요청
- dto: 데이터 교환 객체
- entity: DB의 테이블(schedule, user)과 직접 매핑
- exception: 비즈니스 로직 처리 중 발생하는 공통 예외 처리
- repository: 얻어온 정보를 외부 통신(DB)
- serivce: 전달 받은 데이터를 가공 처리하여 Repository로 전달
