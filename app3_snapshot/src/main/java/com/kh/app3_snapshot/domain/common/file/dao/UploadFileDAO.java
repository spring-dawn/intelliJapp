package com.kh.app3_snapshot.domain.common.file.dao;

import com.kh.app3_snapshot.domain.common.file.UploadFile;

import java.util.List;

public interface UploadFileDAO {

//    첨부파일 업로드
    Long addFile(UploadFile uploadFile);

//    여러 개 업로드
    void addFile(List<UploadFile> uploadFile);




}
