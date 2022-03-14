package com.kh.app3_snapshot.web;

import com.kh.app3_snapshot.domain.bbs.svc.BbsSVC;
import com.kh.app3_snapshot.domain.common.CodeDAO;
import com.kh.app3_snapshot.web.form.bbs.AddForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/bbs")
@RequiredArgsConstructor
public class BbsController {

  private final BbsSVC bbsSVC;
  private final CodeDAO codeDAO;

//  db의 게시판 분류 코드와 디코드(code 테이블)를 model객체에 날라오기.
//  어노테이션() 안의 이름으로 접근 가능.
  @ModelAttribute("classifier")
  public List<Code> classifier(){
    return codeDAO.code("B01");
  }


//  원글 작성양식, 방식 두 가지. 어노테이션이냐 아니냐 차이인 듯.
  @GetMapping("/add")
  public String addForm(Model model){
    model.addAttribute("addForm", new AddForm());
    return "bbs/addForm";
  }
//  public String addForm(@ModelAttribute AddForm addForm){

//    return "bbs/addForm";
//  }


//  작성처리
  @PostMapping("/add")
  public String add(@ModelAttribute AddForm addForm){

//    일반적으론 뷰를 찾아가지만 리다이렉트는 요청을 다시 넣는다.
    return "redirect:/bbs/{id}";
  }

//  목록
  @GetMapping
  public String list(){

    return "bbs/list";
  }

//  상세조회
  @GetMapping("/{id}")
  public String detail(){

    return "bbs/detail";
  }

//  삭제
  @GetMapping("/{id}/del")
  public String del(){

//    리다이렉트로 목록으로 가려면 'url(요청)'을 똑같이 넣는다 뷰가 아니라
   return "redirect:/bbs";
  }

//  수정양식
  @GetMapping("/edit")
  public String editForm(){
    return "bbs/editForm";
  }

//  수정처리
  @PostMapping("/edit")
  public String edit(){
    return "redirect:/bbs/{id}";
  }

//  답글 작성양식
  @GetMapping("/{id}/reply")
  public String replyForm(){
    return "/bbs/replyForm";
  }

//  답글 처리
  @PostMapping("/{id}/reply")
  public String reply(){
    return "redirect:/bbs/{id}";
  }

//  전체 건수


}
