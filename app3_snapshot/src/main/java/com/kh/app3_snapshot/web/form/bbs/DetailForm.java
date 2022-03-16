package com.kh.app3_snapshot.web.form.bbs;

import lombok.Data;

@Data
public class DetailForm {
    private Long bbsId;                     //게시글번호
    private String bcategory;               //분류
    private String title;                   //제목
    private String email;                   //이메일
    private String nickname;                //별칭
    private String bcontent;                //내용
    private int hit;                        //조회수

}
