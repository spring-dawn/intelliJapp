package com.kh.comment_snapshot.domain;

import java.util.List;

public interface CommentDAO {

//    등록
    Comment save(Comment comment);
//    목록
    List<Comment> findAll();

//    조회
    Comment findById(Long id);

//    삭제
    Comment delete(Long id);

//    수정
    Comment update(Long id, Comment comment);

//    저장소 클리어
    void clearStore();

}
