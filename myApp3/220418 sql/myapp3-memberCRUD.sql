--member
--회원 등록
INSERT INTO member (
    member_no,
    member_id,
    member_pw,
    email,
    nickname,
    member_since
) VALUES (
    member_member_no_seq.nextval,
    'tester1',
    '1234',
    'test1@kh.com',
    '테스터1',
    DEFAULT
);

select * from member;
rollback;
commit;