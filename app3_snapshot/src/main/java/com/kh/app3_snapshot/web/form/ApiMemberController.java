package com.kh.app3_snapshot.web.form;

import com.kh.app3_snapshot.domain.member.Member;
import com.kh.app3_snapshot.domain.member.svc.MemberSVC;
import com.kh.app3_snapshot.web.api.ApiResult;
import com.kh.app3_snapshot.web.form.member.DetailForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ApiMemberController {

  private final MemberSVC memberSVC;

  @ResponseBody
  @GetMapping("/api/members")
  public List<Member> members() {

    List<Member> list = memberSVC.findAll();
    return list;

  }

  @ResponseBody       //자료를 Json형식으로 반환
  @GetMapping("/api/member")
  public ApiResult<Member> member(@RequestParam String email) {

    Member member = memberSVC.findByEmail(email);
    ApiResult<Member> result = new ApiResult<Member>("00", "success",member);
    return result;
  }

  @ResponseBody
  @GetMapping("/api/members/{email}/exist")
  public ApiResult<Member> existMember(@PathVariable String email){

    boolean existMember = memberSVC.existMember(email);

    if(existMember) {
//      뭔가 이상한데? 중복 아이디가 없어야 사용 가능한 아이디잖아. 지금 중복 체크가 안 돼.
      return new ApiResult("00", "success", "OK");
    }else {
      return new ApiResult("99", "fail", "NOK");
    }
  }


}
