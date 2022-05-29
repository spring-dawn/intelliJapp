--차트 데이터 테이블을 만들어야 하는데
--기본키, 제약 조건은 생략
drop table chart;
create table chart(
    manager varchar2(15),
    item    varchar2(60),
    cdate   TIMESTAMP default SYSTIMESTAMP
);

--샘플 데이터 
INSERT INTO chart (manager, item, cdate) VALUES('a','1',default);
INSERT INTO chart (manager, item, cdate) VALUES('a','2',default);
INSERT INTO chart (manager, item, cdate) VALUES('a','3',default);
INSERT INTO chart (manager, item, cdate) VALUES('a','4',default);
INSERT INTO chart (manager, item, cdate) VALUES('a','5',default);
INSERT INTO chart (manager, item, cdate) VALUES('b','6',default);
INSERT INTO chart (manager, item, cdate) VALUES('b','7',default);
INSERT INTO chart (manager, item, cdate) VALUES('b','8',default);
INSERT INTO chart (manager, item, cdate) VALUES('b','9',default);
INSERT INTO chart (manager, item, cdate) VALUES('b','10',default);

select * from chart;
commit;

--bar chart, 연도별/담당자별 업무량 쿼리
select count(item), manager from chart where cdate like '22%' group by manager;







