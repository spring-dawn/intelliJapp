package com.kh.app3_snapshot.domain.common.file;

import com.kh.app3_snapshot.domain.common.file.dao.UploadFileDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class UploadFileDAOImplTest {

//    D.I
    @Autowired
    private UploadFileDAO uploadFileDAO;


    @Test
    @DisplayName("단일 업로드")
    void addFile() {
//        테스트용 빈 객체 생성
        UploadFile uploadFile = new UploadFile();

//        빈 객체에 값 설정
        uploadFile.setCode("F0101");
        uploadFile.setRid(2L);
        uploadFile.setStore_finename("xxx-yy-zz.png");
        uploadFile.setUpload_filename("커피3.png");
        uploadFile.setFsize("200");
        uploadFile.setFtype("image/png");

//        집어넣고 테스트
        Long fid = uploadFileDAO.addFile(uploadFile);
        log.info("fid={}", fid);


    }

    @Test
    void testAddFile() {
        List<UploadFile> list = new ArrayList<>();
        for(int i = 0; i<10; i++){
            UploadFile uploadFile = new UploadFile();

            uploadFile.setCode("F0101");
            uploadFile.setRid(2L);
            uploadFile.setStore_finename("xxx-yy-zz.png");
            uploadFile.setUpload_filename("커피3.png");
            uploadFile.setFsize("200");
            uploadFile.setFtype("image/png");

            list.add(uploadFile);
        }
        uploadFileDAO.addFile(list);
    }



}