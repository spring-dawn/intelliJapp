--220419, 가장 먼저 drop 순서 맞추기 
drop table member;

--회원 테이블 생성
create table member(
    member_no   number, --내부 관리 회원번호
    member_id   varchar2(50), --아이디
    member_pw   varchar2(30), --패스워드
    email       varchar2(50), --이메일
    nickname    varchar2(30), --닉네임
    member_since  TIMESTAMP default SYSTIMESTAMP  --가입일자
);

--기본 키
alter table member add CONSTRAINT member_member_no_pk PRIMARY key(member_no);

--제약조건
alter table member add CONSTRAINT member_member_id_uk unique(member_id);
alter table member modify member_id CONSTRAINT member_id_nn not null;

alter table member modify member_pw CONSTRAINT member_pw_nn not null;

alter table member add CONSTRAINT member_email_uk unique(email);
alter table member modify email CONSTRAINT email_nn not null;
alter table member add CONSTRAINT member_nickname_uk unique(nickname);
alter table member modify nickname CONSTRAINT nickname_nn not null;

alter table member modify member_since CONSTRAINT member_since_nn not null;

--시퀀스
drop SEQUENCE member_member_no_seq;
create SEQUENCE member_member_no_seq nocache;

commit;


