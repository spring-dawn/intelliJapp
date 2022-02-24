package com.kh.app3_snapshot.web.form.notice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class EditForm {
  //field
  private Long noticeId;
  private String subject;
  private String content;
  private String author;


}
