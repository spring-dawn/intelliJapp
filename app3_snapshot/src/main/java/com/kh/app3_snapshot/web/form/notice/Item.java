package com.kh.app3_snapshot.web.form.notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
  private Long noticeId;
  private String subject;
  private String cdate;
  private String ctime;
  private Long hit;

}


