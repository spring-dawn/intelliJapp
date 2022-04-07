package com.kh.app3_snapshot.web.form.bbs;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ReplyForm {

  @NotBlank
  @Size(min=5, max = 11)
  private String bcategory;               //분류

  @NotBlank
  @Size(min=5, max=50)
  private String title;                   //제목

  @NotBlank
  @Email
  private String email;                   //이메일

  @NotBlank
  @Size(min=2, max=15)
  private String nickname;                //별칭

  @NotBlank
  @Size(min=5)
  private String bcontent;                //내용

  private List<MultipartFile> files;  // 첨부파일

}
