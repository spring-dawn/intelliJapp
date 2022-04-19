package com.kh.myApp3.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO {
//    구현은 implement, 상속은 extend(확장). 구현은 새 메소드를 정의하는 거고 상속은 있는 메소드를 재정의.

    private final JdbcTemplate jdbcTemplate;

    /**
     * 회원가입
     * @param member 가입에 필요한 회원 정보
     * @return 가입된 정보 
     */
    @Override
    public Member insertMember(Member member) {
//        db에 회원 레코드를 넣는 거니까 게시글 등록이랑 로직 자체는 비슷할 텐데
        StringBuffer sql = new StringBuffer();
        sql.append(" INSERT INTO member ");
        sql.append(" VALUES ( ");
        sql.append("         member_member_no_seq.nextval, ");
        sql.append("         ?, ");
        sql.append("         ?, ");
        sql.append("         ?, ");
        sql.append("         ?, ");
        sql.append("         DEFAULT) ");







        return null;
    }

    /**
     * 회원 정보 수정
     * @param member 수정하려는 회원 정보
     */
    @Override
    public void updateMember(Member member) {

    }

    /**
     * 회원 정보 조회(=내 정보)
     * @param pw 패스워드
     * @return 현 회원 정보(페이지)
     */
    @Override
    public Member selectMemberByPw(String pw) {
        return null;
    }

    /**
     * 회원 탈퇴
     * @param pw 패스워드
     */
    @Override
    public void deleteMember(String pw) {

    }

    /**
     * 계정 유무 검토
     * @param email 가입할 이메일(중복x)
     * @return 아직 회원이 아니면 F, 이미 회원이면 T.
     */
    @Override
    public boolean isMember(String email) {
        return false;
    }
}
