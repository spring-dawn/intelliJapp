--페이징 
select t1.*
  from(
    SELECT
        ROW_NUMBER() OVER (ORDER BY bgroup DESC, step ASC) no,
        bbs_id,
        bcategory,
        title,
        email,
        nickname,
        hit,
        bcontent,
        pbbs_id,
        bgroup,
        step,
        bindent,
        status,
        cdate,
        udate
    FROM
        bbs) t1
where t1.no between 11 and 20;

select t1.*
  from(
    SELECT
        ROW_NUMBER() OVER (ORDER BY bgroup DESC, step ASC) no,
        bbs_id,
        bcategory,
        title,
        email,
        nickname,
        hit,
        bcontent,
        pbbs_id,
        bgroup,
        step,
        bindent,
        status,
        cdate,
        udate
    FROM bbs 
        where bcategory = 'B0101') t1
where t1.no between 11 and 20;

select * from code;

--페이징에 필요한 sql.
select 
    ROW_NUMBER() OVER (ORDER BY member_id),
    email,
    nickname
from member;

--식별자가 인식이 안 돼서 서브쿼리로 뺌
select t1.no "번호", t1.email "이메일", t1.nickname "별칭"
from(
    select 
        ROW_NUMBER() OVER (ORDER BY member_id) no,
        email,
        nickname
    from member)t1
    where t1.no between 2 and 4;

commit;

