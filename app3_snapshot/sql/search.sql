--검색 ex
select t1.* 
from( 
    SELECT  ROW_NUMBER() OVER (ORDER BY bgroup DESC, step ASC) no, 
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
    where bcategory = 'B0101'
      and title like '%ㅁㄴㅇㄹ%'
      and bcontent like '%34%'
    ) t1 
where t1.no between 1 and 10;
