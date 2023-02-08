/* 조인
=> 서로 관련된 테이블의 데이터를 연결하여 추출하는 방법
=> 기법
1) CROSS 조인(=Cartesian product)
2) NATURAL 조인
3) JOIN ~ USING(컬럼명)
4) JOIN ~ ON
5) OUTER JOIN
*/

-- 4) JOIN ~ ON
--    조인 조건을 on에 명시할 수 있다.
select no, title, content, fno, filepath, bno
from board5 b join attach_file5 a on b.no=a.bno;

-- 조건에 일치하는 경우에만 두 테이블의 데이터를 연결한다.
-- 이런 조인을 'inner join' 이라 부른다.
-- SQL 문에서도 inner join 이라 기술할 수 있따.
-- 물론 inner를 생략할 수도 있다.
select no, title, content, fno, filepath, bno
from board5 b inner join attach_file5 a on b.no=a.bno;


/* [inner] join ~ on 의 문제점
   => 반드시 on 에서 지정한 컬럼의 값이 같을 경우에만
        두 테이블의 데이터가 연결된다.
   => 같은 값을 갖는 데이터가 없다면 연결되지 않고, 결과로 출력되지 않는다.
   => 위 SQL의 실행 결과를 보라!
      첨부파일이 없는 2번 게시글은 결과에 포함되지 않는다.
*/

-- 5) OUTER JOIN
--    조인 조건에 일치하는 데이터가 없더라도 두 테이블 중에서 한 테이블의 데이터를
--    결과로 포함시키는 명령이다.
--    문법:
--         select 컬럼명, 컬럼명, ...
--         from 테이블1 t1 [left|right] outer join 테이블2 t2 on t1.컬럼=t2.컬럼
--    left outer join => 왼쪽 테이블의 데이터는 반드시 포함시키라는 뜻이다.
--    right outer join => 오른쪽 테이블의 데이터는 반드시 포함시키라는 뜻이다.
-- 
select no, title, content, fno, filepath, bno
from board5 b left outer join attach_file5 a on b.no=a.bno
order by no desc;

-- 실무
-- 1) 여러 테이블을 조인하여 컬럼을 projection 할 때
--    각 컬럼이 어떤 테이블의 컬럼인지 명시한다.
-- 2) 컬럼을 나열할 떄 한 줄에 한 컬럼을 나열한다.
select 
  b.no, 
  b.title, 
  b.content, 
  a.fno, 
  a.filepath, 
  a.bno
from board5 b 
  left outer join attach_file5 a on b.no=a.bno
order by 
  no desc;


-- 조인 연습:

-- 1) 전체 강의 목록
select lno, titl, rno, mno from lect;

-- 2) 전체 강의실 목록
select rno, loc, name from room;

-- 3) 강의 정보를 출력할 때 센터 이름과 강의실 이름도 함께 출력해보자!
--    강의 테이블(lect)에서 강의명을 가져오고, 
--    강의실 테이블(room)에서 지점명과 강의실명을 가져오자. 
select
    l.lno,
    l.titl,
    l.rno,
    r.rno,
    r.loc,
    r.name
from lect l 
    inner join room r on l.rno=r.rno;

/* inner join의 문제는 위의 경우처럼
   강의실이 아직 지정되지 않은 강의의 경우 강의실 테이블의 데이터와 연결하지 못해
   결과로 출력되지 않는 문제가 있다. */


-- [inner join의 문제점 예2]
-- 1) 모든 강의장 이름을 출력하라.
--    단 강의장에 강의가 배정된 경우 그 강의 이름도 출력하라.

select
  r.rno,
  r.name,
  r.loc,
  l.titl
from room r 
    inner join lect l on r.rno = l.rno;

-- 위의 경우처럼 만약 기준 컬럼의 값과 일치하는 데이터가 없어서
-- 다른 테이블의 데이터와 연결되지 않았다 하더라도
-- 결과로 뽑아내고 싶다면 outer join을 사용하라!*/
-- 즉 아직 강의실이 배정되지 않은 강의 데이터도 출력하고 싶을 때
-- 출력하고 싶은 테이블을 바깥쪽 테이블로 지정하라!

select
    l.lno,
    l.titl,
    r.rno,
    r.loc,
    r.name
from lect l 
    left outer join room r on l.rno=r.rno;
/* 왼쪽 테이블인 lect를 기준으로 room 데이터를 연결한다.
 * 만약 lect와 일치하는 데이터가 room에 없더라도
 * lect 데이터를 출력한다!
 */


select
    l.lno,
    l.titl,
    r.rno,
    r.loc,
    r.name
from lect l 
    right outer join room r on l.rno=r.rno;
/* 오른쪽 테이블인 room를 기준으로 lect 데이터를 연결한다.
 * 만약 room와 일치하는 데이터가 lect에 없더라도
 * room 데이터를 출력한다!
 */


/* 요구사항:
   모든 멤버의 번호와 이름을 출력하라!
   단 학생의 경우 재직여부도 출력하라!*/

-- 1) 모든 멤버 데이터 출력하기
select mno, name
from memb;

-- 2) 학생 데이터를 가져와서 연결하기
select mno, name, work
from memb natural join stnt;

select mno, name, work
from memb join stnt using(mno);

select memb.mno, name, work
from memb, stnt
where memb.mno=stnt.mno;

select memb.mno, name, work
from memb inner join stnt on memb.mno=stnt.mno;

select memb.mno, name, work
from memb join stnt on memb.mno=stnt.mno;

select m.mno, name, work
from memb m join stnt s on m.mno=s.mno;

/* 안타깝게도 위의 SQL문은 학생 목록만 출력한다.
    왜? memb테이블의 데이터와 stnt 테이블의 데이터를
    연결할 때 mno가 같은 데이터만 연결하여 추출하기 때문이다.
   해결책!
    상대 테이블(stnt)에 연결할 대상(데이터)이 없더라도
    select에서 추출하는 방법 */
select m.mno, name, work
from memb m left outer join stnt s on m.mno=s.mno;



/* 여러 테이블의 데이터를 연결하기
    => 다음의 결과가 출력될 수 있도록 수강 신청 데이터를 출력하시오!
    수강신청번호, 강의명, 학생명, 재직여부, 수강신청일, 강의실명, 매니저명, 직위 */

/* 1단계: 수강신청 데이터를 출력 */
select la.lano, la.lno, la.mno, la.rdt
from lect_appl la;

/* 2단계: 수강신청한 학생의 번호 대신 이름을 출력 */
select la.lano, la.lno, m.name, la.rdt
from lect_appl la
     inner join memb m on la.mno=m.mno;

/* 3단계: 수강 신청한 학생의 재직 여부 출력
 * => inner join 에서 inner는 생략 가능
 */
select la.lano, la.lno, m.name, s.work, la.rdt
from lect_appl la
        join memb m on la.mno=m.mno
        join stnt s on la.mno=s.mno;

/* 4단계: 수강신청한 강의 번호 대신 강의명을 출력 */
select la.lano, l.titl, m.name, s.work, la.rdt, l.rno
from lect_appl la
        join memb m on la.mno=m.mno
        join stnt s on la.mno=s.mno
        join lect l on la.lno=l.lno;

/* 5단계: 강의실 이름을 출력한다.
 * => 강의실 번호는 lect 테이블 데이터에 있다.
 * => 강의실 이름은 room 테이블 데이터에 있다.
 */
select la.lano, l.titl, m.name, s.work, la.rdt, r.name, l.mno
from lect_appl la
        join memb m on la.mno=m.mno
        join stnt s on la.mno=s.mno
        join lect l on la.lno=l.lno
        left outer join room r on l.rno=r.rno;

/* 6단계: 매니저 이름을 출력
 * => 매니저 번호는 lect 테이블에 있다.
 * => 매니저 이름은 memb 테이블에 있다.
 */
select
  la.lano,
  l.titl,
  m.name member_name,
  s.work,
  la.rdt,
  r.name room_name,
  m2.name manager_name
from lect_appl la
        join memb m on la.mno=m.mno
        join stnt s on la.mno=s.mno
        join lect l on la.lno=l.lno
        left outer join room r on l.rno=r.rno
        left outer join memb m2 on l.mno=m2.mno;

/* 7단계: 매니저의 직위 출력
 * => 매니저 번호는 lect 테이블 있다.
 * => 매니저 직위는 mgr 테이블에 있다.
 */
select
  la.lano,
  l.titl,
  m.name snm,
  s.work,
  la.rdt,
  r.name rnm,
  m2.name mnm,
  mr.posi
from lect_appl la
        join memb m on la.mno=m.mno
        join stnt s on la.mno=s.mno
        join lect l on la.lno=l.lno
        left outer join room r on l.rno=r.rno
        left outer join memb m2 on l.mno=m2.mno
        left outer join mgr mr on l.mno=mr.mno;










/* */
