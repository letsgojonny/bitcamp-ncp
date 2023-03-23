-- 지역
DROP TABLE IF EXISTS hms_region RESTRICT;

-- 댓글
DROP TABLE IF EXISTS hms_comment RESTRICT;

-- QnA
DROP TABLE IF EXISTS hms_qna RESTRICT;

-- 게시판좋아요
DROP TABLE IF EXISTS hms_board_like RESTRICT;

-- 산
DROP TABLE IF EXISTS hms_mt_information RESTRICT;

-- 게시판
DROP TABLE IF EXISTS hms_board RESTRICT;

-- 맛집정보
DROP TABLE IF EXISTS hms_food RESTRICT;

-- 댓글좋아요
DROP TABLE IF EXISTS hms_comment_like RESTRICT;

-- 산악뉴스
DROP TABLE IF EXISTS hms_mt_news RESTRICT;

-- 등산일지
DROP TABLE IF EXISTS hms_diary RESTRICT;

-- 등산코스
DROP TABLE IF EXISTS hms_course_information RESTRICT;

-- 게시판유형
DROP TABLE IF EXISTS hms_board_category RESTRICT;

-- 회원
DROP TABLE IF EXISTS hms_member RESTRICT;

-- 지역행사
DROP TABLE IF EXISTS hms_local_event RESTRICT;

-- 사진
DROP TABLE IF EXISTS hms_photo RESTRICT;

-- 지역
CREATE TABLE hms_region (
  region_id   INTEGER     NOT NULL COMMENT '지역번호', -- 지역번호
  region_list VARCHAR(50) NOT NULL COMMENT '지역별' -- 지역별
)
COMMENT '지역';

-- 지역
ALTER TABLE hms_region
  ADD CONSTRAINT PK_hms_region -- 지역 기본키
  PRIMARY KEY (
  region_id -- 지역번호
  );

-- 지역 유니크 인덱스
CREATE UNIQUE INDEX UIX_hms_region
  ON hms_region ( -- 지역
    region_list ASC -- 지역별
  );

ALTER TABLE hms_region
  MODIFY COLUMN region_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '지역번호';

-- 댓글
CREATE TABLE hms_comment (
  comment_id   INTEGER      NOT NULL COMMENT '댓글번호', -- 댓글번호
  board_id     INTEGER      NOT NULL COMMENT '게시판번호', -- 게시판번호
  member_id    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  content      VARCHAR(255) NOT NULL COMMENT '내용', -- 내용
  created_date DATE         NOT NULL DEFAULT now() COMMENT '작성일' -- 작성일
)
COMMENT '댓글';

-- 댓글
ALTER TABLE hms_comment
  ADD CONSTRAINT PK_hms_comment -- 댓글 기본키
  PRIMARY KEY (
  comment_id -- 댓글번호
  );

ALTER TABLE hms_comment
  MODIFY COLUMN comment_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

-- QnA
CREATE TABLE hms_qna (
  qna_id       INTEGER      NOT NULL COMMENT '문의번호', -- 문의번호
  member_id    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  title        VARCHAR(255) NOT NULL COMMENT '문의제목', -- 문의제목
  content      VARCHAR(255) NOT NULL COMMENT '문의내용', -- 문의내용
  created_date DATE         NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  response     VARCHAR(255) NOT NULL COMMENT '답변' -- 답변
)
COMMENT 'QnA';

-- QnA
ALTER TABLE hms_qna
  ADD CONSTRAINT PK_hms_qna -- QnA 기본키
  PRIMARY KEY (
  qna_id -- 문의번호
  );

ALTER TABLE hms_qna
  MODIFY COLUMN qna_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '문의번호';

-- 게시판좋아요
CREATE TABLE hms_board_like (
  board_id   INTEGER NOT NULL COMMENT '게시판번호', -- 게시판번호
  member_id  INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  like_count INTEGER NOT NULL COMMENT '좋아요수' -- 좋아요수
)
COMMENT '게시판좋아요';

-- 게시판좋아요
ALTER TABLE hms_board_like
  ADD CONSTRAINT PK_hms_board_like -- 게시판좋아요 기본키
  PRIMARY KEY (
  board_id,  -- 게시판번호
  member_id  -- 회원번호
  );

-- 산
CREATE TABLE hms_mt_information (
  mt_information_id INTEGER      NOT NULL COMMENT '산번호', -- 산번호
  region_id         INTEGER      NOT NULL COMMENT '지역번호', -- 지역번호
  name              VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  height            VARCHAR(50)  NOT NULL COMMENT '산높이', -- 산높이
  photo             VARCHAR(255) NOT NULL COMMENT '사진', -- 사진
  address           VARCHAR(255) NOT NULL COMMENT '주소', -- 주소
  sunrise           TIME         NOT NULL COMMENT '일출시간', -- 일출시간
  sunset            TIME         NOT NULL COMMENT '일몰시간' -- 일몰시간
)
COMMENT '산';

-- 산
ALTER TABLE hms_mt_information
  ADD CONSTRAINT PK_hms_mt_information -- 산 기본키
  PRIMARY KEY (
  mt_information_id -- 산번호
  );

ALTER TABLE hms_mt_information
  MODIFY COLUMN mt_information_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '산번호';

-- 게시판
CREATE TABLE hms_board (
  board_id          INTEGER      NOT NULL COMMENT '게시판번호', -- 게시판번호
  board_category_id INTEGER      NOT NULL COMMENT '게시판유형번호', -- 게시판유형번호
  member_id         INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  title             VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content           VARCHAR(255) NOT NULL COMMENT '내용', -- 내용
  tag               VARCHAR(50)  NOT NULL COMMENT '태그명', -- 태그명
  writer            VARCHAR(50)  NOT NULL COMMENT '작성자', -- 작성자
  created_date      DATE         NOT NULL DEFAULT now() COMMENT '작성일' -- 작성일
)
COMMENT '게시판';

-- 게시판
ALTER TABLE hms_board
  ADD CONSTRAINT PK_hms_board -- 게시판 기본키
  PRIMARY KEY (
  board_id -- 게시판번호
  );

ALTER TABLE hms_board
  MODIFY COLUMN board_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시판번호';

ALTER TABLE hms_board
  AUTO_INCREMENT = 1;

-- 맛집정보
CREATE TABLE hms_food (
  board_id     INTEGER      NOT NULL COMMENT '맛집정보번호', -- 맛집정보번호
  food_address VARCHAR(255) NULL     COMMENT '맛집주소', -- 맛집주소
  food_tel     VARCHAR(30)  NULL     COMMENT '맛집전화번호' -- 맛집전화번호
)
COMMENT '맛집정보';

-- 맛집정보
ALTER TABLE hms_food
  ADD CONSTRAINT PK_hms_food -- 맛집정보 기본키
  PRIMARY KEY (
  board_id -- 맛집정보번호
  );

-- 댓글좋아요
CREATE TABLE hms_comment_like (
  comment_id INTEGER NOT NULL COMMENT '댓글번호', -- 댓글번호
  member_id  INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  like_count INTEGER NOT NULL COMMENT '좋아요수' -- 좋아요수
)
COMMENT '댓글좋아요';

-- 댓글좋아요
ALTER TABLE hms_comment_like
  ADD CONSTRAINT PK_hms_comment_like -- 댓글좋아요 기본키
  PRIMARY KEY (
  comment_id, -- 댓글번호
  member_id   -- 회원번호
  );

-- 산악뉴스
CREATE TABLE hms_mt_news (
  mt_news_id INTEGER NOT NULL COMMENT '산악뉴스번호', -- 산악뉴스번호
  board_id   INTEGER NOT NULL COMMENT '게시판번호' -- 게시판번호
)
COMMENT '산악뉴스';

-- 산악뉴스
ALTER TABLE hms_mt_news
  ADD CONSTRAINT PK_hms_mt_news -- 산악뉴스 기본키
  PRIMARY KEY (
  mt_news_id, -- 산악뉴스번호
  board_id    -- 게시판번호
  );

ALTER TABLE hms_mt_news
  MODIFY COLUMN mt_news_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '산악뉴스번호';

-- 등산일지
CREATE TABLE hms_diary (
  board_id          INTEGER     NOT NULL COMMENT '등산일지번호', -- 등산일지번호
  mt_information_id INTEGER     NOT NULL COMMENT '산번호', -- 산번호
  course            VARCHAR(50) NULL     COMMENT '등산경로', -- 등산경로
  record_length     VARCHAR(50) NULL     COMMENT '이동거리', -- 이동거리
  record_time       VARCHAR(50) NULL     COMMENT '이동시간' -- 이동시간
)
COMMENT '등산일지';

-- 등산일지
ALTER TABLE hms_diary
  ADD CONSTRAINT PK_hms_diary -- 등산일지 기본키
  PRIMARY KEY (
  board_id -- 등산일지번호
  );

-- 등산코스
CREATE TABLE hms_course_information (
  course_information_id INTEGER      NOT NULL COMMENT '등산코스번호', -- 등산코스번호
  mt_information_id     INTEGER      NOT NULL COMMENT '산번호', -- 산번호
  name                  VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  length                VARCHAR(50)  NOT NULL COMMENT '등산코스길이', -- 등산코스길이
  time                  VARCHAR(50)  NOT NULL COMMENT '소요시간', -- 소요시간
  level                 VARCHAR(50)  NOT NULL COMMENT '난이도', -- 난이도
  photo                 VARCHAR(255) NOT NULL COMMENT '등산경로사진' -- 등산경로사진
)
COMMENT '등산코스';

-- 등산코스
ALTER TABLE hms_course_information
  ADD CONSTRAINT PK_hms_course_information -- 등산코스 기본키
  PRIMARY KEY (
  course_information_id -- 등산코스번호
  );

ALTER TABLE hms_course_information
  MODIFY COLUMN course_information_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '등산코스번호';

-- 게시판유형
CREATE TABLE hms_board_category (
  board_category_id INTEGER     NOT NULL COMMENT '게시판유형번호', -- 게시판유형번호
  board_name        VARCHAR(50) NULL     COMMENT '게시판명' -- 게시판명
)
COMMENT '게시판유형';

-- 게시판유형
ALTER TABLE hms_board_category
  ADD CONSTRAINT PK_hms_board_category -- 게시판유형 기본키
  PRIMARY KEY (
  board_category_id -- 게시판유형번호
  );

ALTER TABLE hms_board_category
  MODIFY COLUMN board_category_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시판유형번호';

-- 회원
CREATE TABLE hms_member (
  member_id      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  name           VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  tel            VARCHAR(30)  NOT NULL COMMENT '전화번호', -- 전화번호
  email          VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  id             VARCHAR(10)  NOT NULL COMMENT '아이디', -- 아이디
  profile_photo  VARCHAR(255) NULL     COMMENT '프로필사진', -- 프로필사진
  password       VARCHAR(10)  NOT NULL COMMENT '비밀번호', -- 비밀번호
  nickname       VARCHAR(50)  NOT NULL COMMENT '닉네임', -- 닉네임
  postno         VARCHAR(10)  NOT NULL COMMENT '우편번호', -- 우편번호
  basic_address  VARCHAR(255) NOT NULL COMMENT '기본주소', -- 기본주소
  detail_address VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
  birth          DATE         NOT NULL COMMENT '생년월일', -- 생년월일
  status_message VARCHAR(255) NULL     COMMENT '상태메세지', -- 상태메세지
  created_date   DATE         NOT NULL DEFAULT now() COMMENT '가입일', -- 가입일
  authority      VARCHAR(10)  NULL     COMMENT '권한' -- 권한
)
COMMENT '회원';

-- 회원
ALTER TABLE hms_member
  ADD CONSTRAINT PK_hms_member -- 회원 기본키
  PRIMARY KEY (
  member_id -- 회원번호
  );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_hms_member
  ON hms_member ( -- 회원
    id ASC,       -- 아이디
    email ASC,    -- 이메일
    nickname ASC  -- 닉네임
  );

ALTER TABLE hms_member
  MODIFY COLUMN member_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 지역행사
CREATE TABLE hms_local_event (
  board_id       INTEGER      NOT NULL COMMENT '지역행사번호', -- 지역행사번호
  period         VARCHAR(50)  NOT NULL COMMENT '기간', -- 기간
  postno         VARCHAR(10)  NOT NULL COMMENT '우편번호', -- 우편번호
  basic_address  VARCHAR(255) NOT NULL COMMENT '기본주소', -- 기본주소
  detail_address VARCHAR(255) NOT NULL COMMENT '상세주소' -- 상세주소
)
COMMENT '지역행사';

-- 지역행사
ALTER TABLE hms_local_event
  ADD CONSTRAINT PK_hms_local_event -- 지역행사 기본키
  PRIMARY KEY (
  board_id -- 지역행사번호
  );

-- 사진
CREATE TABLE hms_photo (
  photo_id INTEGER      NOT NULL COMMENT '사진번호', -- 사진번호
  board_id INTEGER      NOT NULL COMMENT '게시판번호', -- 게시판번호
  photo    VARCHAR(255) NULL     COMMENT '사진', -- 사진
  mimetype VARCHAR(10)  NULL     COMMENT 'MIMETYPE' -- MIMETYPE
)
COMMENT '사진';

-- 사진
ALTER TABLE hms_photo
  ADD CONSTRAINT PK_hms_photo -- 사진 기본키
  PRIMARY KEY (
  photo_id -- 사진번호
  );

ALTER TABLE hms_photo
  MODIFY COLUMN photo_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '사진번호';

-- 댓글
ALTER TABLE hms_comment
  ADD CONSTRAINT FK_hms_board_TO_hms_comment -- 게시판 -> 댓글
  FOREIGN KEY (
  board_id -- 게시판번호
  )
  REFERENCES hms_board ( -- 게시판
  board_id -- 게시판번호
  );

-- 댓글
ALTER TABLE hms_comment
  ADD CONSTRAINT FK_hms_member_TO_hms_comment -- 회원 -> 댓글
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hms_member ( -- 회원
  member_id -- 회원번호
  );

-- QnA
ALTER TABLE hms_qna
  ADD CONSTRAINT FK_hms_member_TO_hms_qna -- 회원 -> QnA
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hms_member ( -- 회원
  member_id -- 회원번호
  );

-- 게시판좋아요
ALTER TABLE hms_board_like
  ADD CONSTRAINT FK_hms_board_TO_hms_board_like -- 게시판 -> 게시판좋아요
  FOREIGN KEY (
  board_id -- 게시판번호
  )
  REFERENCES hms_board ( -- 게시판
  board_id -- 게시판번호
  );

-- 게시판좋아요
ALTER TABLE hms_board_like
  ADD CONSTRAINT FK_hms_member_TO_hms_board_like -- 회원 -> 게시판좋아요
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hms_member ( -- 회원
  member_id -- 회원번호
  );

-- 산
ALTER TABLE hms_mt_information
  ADD CONSTRAINT FK_hms_region_TO_hms_mt_information -- 지역 -> 산
  FOREIGN KEY (
  region_id -- 지역번호
  )
  REFERENCES hms_region ( -- 지역
  region_id -- 지역번호
  );

-- 게시판
ALTER TABLE hms_board
  ADD CONSTRAINT FK_hms_member_TO_hms_board -- 회원 -> 게시판
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hms_member ( -- 회원
  member_id -- 회원번호
  );

-- 게시판
ALTER TABLE hms_board
  ADD CONSTRAINT FK_hms_board_category_TO_hms_board -- 게시판유형 -> 게시판
  FOREIGN KEY (
  board_category_id -- 게시판유형번호
  )
  REFERENCES hms_board_category ( -- 게시판유형
  board_category_id -- 게시판유형번호
  );

-- 맛집정보
ALTER TABLE hms_food
  ADD CONSTRAINT FK_hms_board_TO_hms_food -- 게시판 -> 맛집정보
  FOREIGN KEY (
  board_id -- 맛집정보번호
  )
  REFERENCES hms_board ( -- 게시판
  board_id -- 게시판번호
  );

-- 댓글좋아요
ALTER TABLE hms_comment_like
  ADD CONSTRAINT FK_hms_comment_TO_hms_comment_like -- 댓글 -> 댓글좋아요
  FOREIGN KEY (
  comment_id -- 댓글번호
  )
  REFERENCES hms_comment ( -- 댓글
  comment_id -- 댓글번호
  );

-- 댓글좋아요
ALTER TABLE hms_comment_like
  ADD CONSTRAINT FK_hms_member_TO_hms_comment_like -- 회원 -> 댓글좋아요
  FOREIGN KEY (
  member_id -- 회원번호
  )
  REFERENCES hms_member ( -- 회원
  member_id -- 회원번호
  );

-- 산악뉴스
ALTER TABLE hms_mt_news
  ADD CONSTRAINT FK_hms_board_TO_hms_mt_news -- 게시판 -> 산악뉴스
  FOREIGN KEY (
  board_id -- 게시판번호
  )
  REFERENCES hms_board ( -- 게시판
  board_id -- 게시판번호
  );

-- 등산일지
ALTER TABLE hms_diary
  ADD CONSTRAINT FK_hms_mt_information_TO_hms_diary -- 산 -> 등산일지
  FOREIGN KEY (
  mt_information_id -- 산번호
  )
  REFERENCES hms_mt_information ( -- 산
  mt_information_id -- 산번호
  );

-- 등산일지
ALTER TABLE hms_diary
  ADD CONSTRAINT FK_hms_board_TO_hms_diary -- 게시판 -> 등산일지
  FOREIGN KEY (
  board_id -- 등산일지번호
  )
  REFERENCES hms_board ( -- 게시판
  board_id -- 게시판번호
  );

-- 등산코스
ALTER TABLE hms_course_information
  ADD CONSTRAINT FK_hms_mt_information_TO_hms_course_information -- 산 -> 등산코스
  FOREIGN KEY (
  mt_information_id -- 산번호
  )
  REFERENCES hms_mt_information ( -- 산
  mt_information_id -- 산번호
  );

-- 지역행사
ALTER TABLE hms_local_event
  ADD CONSTRAINT FK_hms_board_TO_hms_local_event -- 게시판 -> 지역행사
  FOREIGN KEY (
  board_id -- 지역행사번호
  )
  REFERENCES hms_board ( -- 게시판
  board_id -- 게시판번호
  );

-- 사진
ALTER TABLE hms_photo
  ADD CONSTRAINT FK_hms_board_TO_hms_photo -- 게시판 -> 사진
  FOREIGN KEY (
  board_id -- 게시판번호
  )
  REFERENCES hms_board ( -- 게시판
  board_id -- 게시판번호
  );