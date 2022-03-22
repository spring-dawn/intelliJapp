--220321 평가. 계정명 product, pw: product1234
--테이블 생성
drop table product;
create table product(
    product_id  number,
    pname       varchar2(70),
    pquantity   number,
    pprice      number
);

--기본키
alter table product add constraint product_product_id_pk PRIMARY key(product_id);

--제약조건
alter table product modify pname CONSTRAINT product_pname_nn not null;
alter table product modify pprice CONSTRAINT product_pprice_nn not null;

--시퀀스
drop SEQUENCE product_product_id_seq;
create SEQUENCE product_product_id_seq nocache;

--샘플데이터 기본 3개
INSERT INTO product (
    product_id,
    pname,
    pquantity,
    pprice
) VALUES (
    product_product_id_seq.nextval,
    '노트',
     50,
    2000
);
INSERT INTO product (
    product_id,
    pname,
    pquantity,
    pprice
) VALUES (
    product_product_id_seq.nextval,
    '필기구',
     100,
    5000
);
INSERT INTO product (
    product_id,
    pname,
    pquantity,
    pprice
) VALUES (
    product_product_id_seq.nextval,
    '문진',
     50,
    30000
);
commit;
select * from product;

--crud
--1. 등록
INSERT INTO product (
    product_id,
    pname,
    pquantity,
    pprice
) VALUES (
    :v0,
    :v1,
    :v2,
    :v3
);

--2. 개별(상세) 조회
SELECT
    product_id,
    pname,
    pquantity,
    pprice
FROM
    product
where product_id = 2;

--3. 수정
UPDATE product
SET
    pprice = 33000
WHERE
        product_id = 3;
        
--4. 삭제
delete from product
where product_id = 3;

--5. 전체 조회
SELECT
    product_id,
    pname,
    pquantity,
    pprice
FROM
    product
order by product_id;

