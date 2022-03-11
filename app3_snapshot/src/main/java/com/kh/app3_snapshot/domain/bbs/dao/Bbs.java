package com.kh.app3_snapshot.domain.bbs.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Bbs {
    private Long bbsId;                    //게시글 번호
    private String bcategory;               //분류
    private String title;                   //제목
    private String email;                   //이메일
    private String nickname;                //별칭
    private int hit;                        //조회수
    private String bcontent;                //내용
    private Long pbbsId;                   //상위 게시글 번호
    private Long bgroup;                    //답글 그룹
    private int step;                       //답글 단계 Step
    private int bindent;                    //답글 들여쓰기
    private BbsStatus status;                  //게시글 상태
    private LocalDateTime cdate;            //생성일
    private LocalDateTime udate;            //수정일


}
