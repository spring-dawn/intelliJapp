package com.kh.myApp3.domain.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor   // 디폴트 생성자 자동 생성해준다.
@AllArgsConstructor  // 모든멤버필드를 매개값으로 받아 생성자를 자동 만들어준다.
public class Member {
//  회원 가입 시 필요한 멤버필드
    private Long memberNo;
    private String memberId;
    private String memberPw;
    private String email;
    private String nickname;
    private LocalDate memberSince;

//    member_no   number, --내부 관리 회원번호
//    member_id   varchar2(50), --아이디
//    member_pw   varchar2(30), --패스워드
//    email       varchar2(50), --이메일
//    nickname    varchar2(30), --닉네임
//    member_since  TIMESTAMP default SYSTIMESTAMP  --가입일자
}
