package com.kh.comment_snapshot.domain;

import java.time.LocalDateTime;
import java.util.*;

public class CommentDAOImpl implements CommentDAO{

    private static Map<Long, Comment> store = new HashMap<>();
    private static final CommentDAOImpl commentDAOImpl = new CommentDAOImpl();
    private static long seq = 0L;
//    싱글톤
    private CommentDAOImpl(){}

    public static CommentDAOImpl getInstance(){
        return commentDAOImpl;
    }

//    댓글 등록. 아이디는 시퀀스 사용
    @Override
    public Comment save(Comment comment) {
        comment.setId(++seq);

        LocalDateTime localDateTime = LocalDateTime.now();
        comment.setCdate(localDateTime);          //생성일시
        comment.setUdate(localDateTime);          //수정일시

        store.put(comment.getId(), comment);
        return comment;
    }

//    목록. value만.
    @Override
    public List<Comment> findAll() {

        return new ArrayList<>(store.values());
    }

//    조회
    @Override
    public Comment findById(Long id) {
        return store.get(id);
    }

//    삭제
    @Override
    public Comment delete(Long id) {

        return store.remove(id);   //삭제한 value 반환
    }

//    수정
    @Override
    public Comment update(Long id, Comment comment) {
        comment.setId(id);
        comment.setUdate(LocalDateTime.now());

        return store.put(id, comment);    //key에 대한 수정 전 commet반환
    }

//    저장소 clear
    @Override
    public void clearStore() { store.clear(); }
}
