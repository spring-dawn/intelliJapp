package com.kh.myApp3.domain.member;

//회원 관리에 필요한 메소드. SVC에선 다른 말로 구분해야겠다. SingUP 같은 거.
public interface MemberDAO {
//    회원 가입
    Member insertMember(Member member);

//    회원 정보 수정
    void updateMember(Member member);

//    회원 정보 조회
    Member selectMemberByPw(String pw);

//    회원 탈퇴
    void deleteMember(String pw);

//    가입 여부 체크
    boolean isMember(String email);

//    더 있으면 차근차근 추가하자.
    
    
    
}
