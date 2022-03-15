package com.kh.app3_snapshot.web;

import com.kh.app3_snapshot.domain.bbs.dao.Bbs;
import com.kh.app3_snapshot.domain.bbs.svc.BbsSVC;
import com.kh.app3_snapshot.domain.common.CodeDAO;
import com.kh.app3_snapshot.web.form.bbs.AddForm;
import com.kh.app3_snapshot.web.form.bbs.DetailForm;
import com.kh.app3_snapshot.web.form.bbs.ListForm;
import com.kh.app3_snapshot.web.form.login.LoginMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/bbs")
@RequiredArgsConstructor
public class BbsController {
  private final BbsSVC bbsSvc;
  private final CodeDAO codeDAO;

  //게시판 코드,디코드 가져오기
  @ModelAttribute("classifier")
  public List<Code> classifier(){
    return codeDAO.code("B01");
  }


  //작성양식
  @GetMapping("/add")
//  public String addForm(Model model) {
//    model.addAttribute("addForm", new AddForm());
//    return "bbs/addForm";
//  }
  public String addForm(@ModelAttribute AddForm addForm) {
    return "bbs/addForm";
  }

  //작성처리
  @PostMapping("/add")
  public String add(
          @ModelAttribute AddForm addForm,
          HttpSession session,
          RedirectAttributes redirectAttributes) {
    log.info("addForm={}",addForm);

    Bbs bbs = new Bbs();
//    bbs.setBcategory(addForm.getBcategory());
//    bbs.setTitle(addForm.getTitle());
//    bbs.setEmail(addForm.getEmail());
//    bbs.setNickname(addForm.getNickname());
//    bbs.setBcontent(addForm.getBcontent());
    BeanUtils.copyProperties(addForm, bbs);

//    세션 가져오기
    LoginMember loginMember = (LoginMember) session.getAttribute(SessionConst.LOGIN_MEMBER);
//    세션 정보가 없으면 로그인 페이지로 이동.
    if(loginMember == null){
      return "redirect:/login";
    }


//    세션에서 이메일, 별칭 가져오기
    bbs.setEmail(loginMember.getEmail());
    bbs.setNickname(loginMember.getNickname());

    Long originId = bbsSvc.saveOrigin(bbs);
    redirectAttributes.addAttribute("id", originId);
    // <=서버응답 302 get http://서버:port/bbs/10
    // =>클라이언트요청 get http://서버:port/bbs/10
    return "redirect:/bbs/{id}";
  }

  //목록
  @GetMapping
  public String list(Model model) {

    List<Bbs> list = bbsSvc.findAll();

    List<ListForm> partOfList = new ArrayList<>();
    for (Bbs bbs : list) {
      ListForm listForm = new ListForm();
      BeanUtils.copyProperties(bbs, listForm);
      partOfList.add(listForm);
    }

    model.addAttribute("list", partOfList);
    return "bbs/list";
  }


  //조회
  @GetMapping("/{id}")
  public String detail(
          @PathVariable Long id,
          Model model) {

    Bbs detailBbs = bbsSvc.findByBbsId(id);

    DetailForm detailForm = new DetailForm();
//    detailForm.setBcategory(detailBbs.getBcategory());
//    detailForm.setTitle(detailBbs.getTitle());
//    detailForm.setBcontent(detailBbs.getBcontent());
//    detailForm.setEmail(detailBbs.getEmail());
//    detailForm.setNickname(detailBbs.getNickname());
//    detailForm.setHit(detailBbs.getHit());
    BeanUtils.copyProperties(detailBbs, detailForm);      //위의 코드와 같은 내용. 편리한 메소드.
    model.addAttribute("detailForm", detailForm);

    return "bbs/detailForm";
  }

  //삭제
  @GetMapping("/{id}/del")
  public String del() {
    return "redirect:/bbs";
  }

  //수정양식
  @GetMapping("/{id}/edit")
  public String editForm() {
    return "bbs/editForm";
  }

  //수정처리
  @PostMapping("/{id}/edit")
  public String edit() {
    return "redirect:/bbs/{id}";
  }

  //답글작성양식
  @GetMapping("/{id}/reply")
  public String replyForm() {
    return "bbs/replyForm";
  }

  //답글작성처리
  @PostMapping("/{id}/reply")
  public String reply(){

    return "redirect:/bbs/{id}";
  }
}
