package com.kh.app3_snapshot.web.form.bbs;

import lombok.Data;

import java.time.LocalDateTime;

//뷰의 입력값을 받아 올 폼(form.html) 객체 정의. 컨트롤러에선 Model model로 받을 수 있다?
@Data
public class ListForm {
  private Long bbsId;                     //게시글번호
  private String bcategory;               //분류
  private String title;                   //제목
  private String email;                   //이메일
  private String nickname;                //별칭
  private LocalDateTime cdate;            //작성일
  private int hit;                        //조회수
  private int bindent;                    //들여쓰기

}
