package com.kh.app3_snapshot.domain.member.svc;

import com.kh.app3_snapshot.domain.member.dao.MemberDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class MemberSVCImplTest {

    @Autowired
    private MemberDAO memberDAO;

//    리턴값이 T or F 니까 두 가지 경우 모두 체크 테스트를 해보는 건가?
    @Test
    @DisplayName("회원유무체크:존재하는경우")
    void isMemberOk() {
        String email = "test1@kh.com";
        assertThat(memberDAO.exitMember(email)).isTrue();
    }
    @Test
    @DisplayName("회원유무체크:존재하지 않는 경우")
    void isMemberNok() {
        String email = "zzz@kh.com";
        assertThat(memberDAO.isMember(email)).isFalse();
    }
}