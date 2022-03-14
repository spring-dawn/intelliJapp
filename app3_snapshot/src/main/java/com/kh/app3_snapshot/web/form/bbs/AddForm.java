package com.kh.app3_snapshot.web.form.bbs;

import lombok.Data;

//뷰의 입력값을 받아 올 폼(form.html) 객체 정의
@Data
public class AddForm {
  private String bcategory;               //분류
  private String title;                   //제목
  private String email;                   //이메일
  private String nickname;                //별칭
  private String bcontent;                //내용

}
