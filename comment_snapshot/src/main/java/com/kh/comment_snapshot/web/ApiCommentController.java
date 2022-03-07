package com.kh.comment_snapshot.web;

import com.kh.comment_snapshot.domain.Comment;
import com.kh.comment_snapshot.domain.CommentDAO;
import com.kh.comment_snapshot.domain.CommentDAOImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/comments")
public class ApiCommentController {

    private final CommentDAO commentDao = CommentDAOImpl.getInstance();

    //등록
    @PostMapping
    public ApiResult<Object> save(@RequestBody Comment comment){
        Comment savedComment = commentDao.save(comment);
        return new ApiResult<>("00","success",savedComment);
    }
    //목록
    @GetMapping
    public ApiResult<Object> findAll(){
        List<Comment> list = commentDao.findAll();
        return new ApiResult<>("00","success",list);
    }

    //조회
    @GetMapping("/{id}")
    public ApiResult<Object> findById(@PathVariable Long id){
        return new ApiResult<>("00","success",commentDao.findById(id));
    }

    //삭제
    @DeleteMapping("/{id}")
    public ApiResult<Object> delete(@PathVariable Long id){
        return new ApiResult<>("00","success",commentDao.delete(id));
    }
    //수정
    @PatchMapping("/{id}")
    public ApiResult<Object> update(@PathVariable Long id, @RequestBody Comment comment){
        return new ApiResult<>("00","success",commentDao.update(id,comment));
    }

    //전체삭제
    @DeleteMapping
    public ApiResult<Object> clearStore() {

        ApiResult<Object> result = null;

        if (commentDao.findAll().isEmpty()) {
            return new ApiResult<>("01", "success", "삭제할 댓글목록이 없습니다.");
        } else {
            commentDao.clearStore();
            if (commentDao.findAll().isEmpty()) {
                return new ApiResult<>("00", "success", "전체 댓글을 삭제했습니다.");
            }
        }
        return result;
    }

//   초기화(빌드)직후 샘플데이터
    @PostConstruct
    public void init(){
        Comment comment = new Comment();
        comment.setEmail("test1@kh.com");
        comment.setNickname("테스터1");
        comment.setContent("댓글1");
        commentDao.save(comment);

        comment = new Comment();
        comment.setEmail("test2@kh.com");
        comment.setNickname("테스터2");
        comment.setContent("댓글2");
        commentDao.save(comment);
    }




}








