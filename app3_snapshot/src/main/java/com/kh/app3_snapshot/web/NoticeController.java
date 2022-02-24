package com.kh.app3_snapshot.web;

import com.kh.app3_snapshot.domain.notice.Notice;
import com.kh.app3_snapshot.domain.notice.svc.NoticeSVC;
import com.kh.app3_snapshot.web.form.notice.AddForm;
import com.kh.app3_snapshot.web.form.notice.DetailForm;
import com.kh.app3_snapshot.web.form.notice.EditForm;
import com.kh.app3_snapshot.web.form.notice.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/notices")    //url 매핑
public class NoticeController {
//  DAO에서 시작한 로직이지만 컨트롤러에서 참조할 때는 SVC에서 불러온다. 담당이 다르다.
  private final NoticeSVC noticeSVC;

  //  등록화면 GET /notices/add
  @GetMapping("")
  public String addForm(@ModelAttribute AddForm addForm) {
    log.info("NoticeController.addForm() 호출");
    return "notice/addForm";    //뷰(화면) 이름.
  }

//  같은 결과 다른 방법
//  public String addForm(Model model) {
//    log.info("NoticeController.addForm() 호출");
//    model.addAttributes("addForm", new AddForm());
//    return "notice/addForm";    //뷰(화면) 이름.
//  }

  //  등록처리 POST /notices/add
  @PostMapping("")
  public String add(
      @ModelAttribute AddForm addForm,
      RedirectAttributes redirectAttributes) {
    log.info("NoticeController.add() 호출");
    log.info("AddForm={}", addForm);

//    새 내용 입력
    Notice notice = new Notice();
    notice.setSubject(addForm.getSubject());
    notice.setContent(addForm.getContent());
    notice.setAuthor(addForm.getAuthor());

    Notice writtenNotice= noticeSVC.write(notice);
    redirectAttributes.addAttribute("noticeId",writtenNotice.getNoticeId());

//    입력된 내용을 model에 담아(바인딩) 상세 페이지에 똑같이 기록. 등록이 안 돼서 잠시 주석처리.
//    DetailForm detailForm = new DetailForm();
//    detailForm.setSubject(writtenNotice.getSubject());
//    detailForm.setContent(writtenNotice.getContent());
//    detailForm.setAuthor(writtenNotice.getAuthor());
//
//    model.addAttribute("detailForm", detailForm);
//    redirectAttributes.addAttribute("noticeId", writtenNotice.getNoticeId());

    return "redirect:/notices/{noticeId}/detail";  //    http://서버:9080/notices/공지사항번호
  }

  // 상세화면  GET /notices/{noticeId}
  @GetMapping("/{noticeId}/detail")
  public String detailForm(@PathVariable Long noticeId, Model model) {

    Notice notice = noticeSVC.findByNoticeId(noticeId);

    DetailForm detailForm = new DetailForm();
    detailForm.setNoticeId(notice.getNoticeId());
    detailForm.setSubject(notice.getSubject());
    detailForm.setContent(notice.getContent());
    detailForm.setAuthor(notice.getAuthor());

    model.addAttribute("detailForm", detailForm);

    return "notice/detailForm";
//    상세페이지 확인할 때마다 조회수 갱신 필요

  }

  // 수정화면 GET /notices/{noticeId}/edit
  @GetMapping("/{noticeId}")
  public String editForm(@PathVariable Long noticeId, Model model) {

    Notice notice = noticeSVC.findByNoticeId(noticeId);

    EditForm editForm = new EditForm();
    editForm.setNoticeId(notice.getNoticeId());
    editForm.setSubject(notice.getSubject());
    editForm.setContent(notice.getContent());
    editForm.setAuthor(notice.getAuthor());

    model.addAttribute("editForm", editForm);

    return "notice/editForm";
  }

  // 수정처리  POST /notices/{noticeId}/edit
  @PatchMapping("/{noticeId}/")
  public String edit(
      @ModelAttribute EditForm editForm,
      @PathVariable Long noticeId,
      RedirectAttributes redirectAttributes) {

    Notice notice = new Notice();
    notice.setNoticeId(noticeId);
    notice.setSubject(editForm.getSubject());
    notice.setContent(editForm.getContent());
    Notice modifiedNotice = noticeSVC.modify(notice);

    log.info("notice={}", notice);

    redirectAttributes.addAttribute("noticeId", modifiedNotice.getNoticeId());

    return "redirect:/notices/{noticeId}/detail";    //뷰 아니고 url. 리다이렉트의 경우엔 다르다.
  }

  // 삭제처리  GET /notices/{noticeId}/del
  @DeleteMapping("/{noticeId}")
  public String del(@PathVariable Long noticeId) {

    noticeSVC.remove(noticeId);

    return "redirect:/notices/all";
  }

//  전체목록 GET /notices
  @GetMapping("/all")
  public String list(Model model){

    List<Notice> list = noticeSVC.findAll();

//    이거 이터레이터 아니냐(iter 까지 치고 엔터)
    List<Item> notices = new ArrayList<>();
    for (Notice notice : list) {

//      목록에 출력할 내용만 get해서 아이템 객체에 set
      Item item = new Item();
      item.setNoticeId(notice.getNoticeId());
      item.setSubject(notice.getSubject());
      item.setCdate(notice.getCdate().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
      item.setCtime(notice.getCdate().toLocalTime().format(DateTimeFormatter.ofPattern("H:m:s")));       //toLocalTime(): 날짜에서 시간만 추려내기
      item.setHit(notice.getHit());

      notices.add(item);
    }

    model.addAttribute("notices", notices);     //k:v
    return "notice/list";
  }

}
