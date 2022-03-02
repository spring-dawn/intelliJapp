package com.kh.app3_snapshot.web;

import com.kh.app3_snapshot.domain.member.Member;
import com.kh.app3_snapshot.domain.member.svc.MemberSVC;
import com.kh.app3_snapshot.web.api.ApiResult;
import com.kh.app3_snapshot.web.form.member.DetailForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ApiMemberController {

  private final MemberSVC memberSVC;


  @ResponseBody  //http응답 메시지를 바디에 직접 쓰기. 반환 타입이 java객체면 json포맷 문자열로 변환, 뷰를 만들지 않는다.
  @GetMapping("/api/members")
//  제네릭 타입으로 감싸기
  public ApiResult<List<Member>> members() {

    List<Member> list = memberSVC.findAll();
    ApiResult<List<Member>> result = new ApiResult<>("00", "success", list);
    return result;

  }

  @ResponseBody       //자료를 Json형식으로 반환
  @GetMapping("/api/member")
  public ApiResult<Member> member(@RequestParam String email) {

    Member member = memberSVC.findByEmail(email);
    ApiResult<Member> result = new ApiResult<Member>("00", "success", member);
    return result;
  }

  @ResponseBody
  @GetMapping("/api/members/{email}/exist")
  public ApiResult<Member> existMember(@PathVariable String email) {

    boolean existMember = memberSVC.existMember(email);

    if (existMember) {
//      뭔가 이상한데? 중복 아이디가 없어야 사용 가능한 아이디잖아. 지금 중복 체크가 안 돼.
      return new ApiResult("00", "success", "OK");
    } else {
      return new ApiResult("99", "fail", "NOK");
    }
  }

  @ResponseBody
  @PutMapping("/api/members/email/find")
  public ApiResult<String> findEmailByNickname(
      @RequestBody String nickname
  ) {

    log.info("nickname={}", nickname);
    ApiResult<String> result = null;

    String email = memberSVC.findEmailByNickname(nickname);

//    StringUtils.isEmpty() : null 또는 ""빈 문자열인지를 검사.
//    if(email == null || email.equals("")) 와 같은 의미.
    if (!StringUtils.isEmpty(email)) {
      result = new ApiResult<>("00", "success", email);
    } else {
      result = new ApiResult<>("99", "fail", "찾으시는 아이디가 없습니다.");
    }
    return result;

  }



}
