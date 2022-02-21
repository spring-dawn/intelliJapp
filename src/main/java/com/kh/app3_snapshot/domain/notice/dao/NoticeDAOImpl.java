package com.kh.app3_snapshot.domain.notice.dao;

import com.kh.app3_snapshot.domain.member.Member;
import com.kh.app3_snapshot.domain.notice.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NoticeDAOImpl implements NoticeDAO {

//  @RequiredArgsConstructor를 사용하면 생성자 자동 생성.
  private final JdbcTemplate jdbcTemplate;
//   어노테이션 없으면 아래처럼 작성해줘야 함.
//  public NoticeDAOImpl(JdbcTemplate jdbcTemplate){
//    this.jdbcTemplate = jdbcTemplate;
//  }

  /**
   * 등록
   *
   * @param notice
   * @return
   */
  @Override
  public Notice create(Notice notice) {
//    sql 작성
    StringBuffer sql = new StringBuffer();
    sql.append(" insert into notice (notice_id, subject, content, author) ");
    sql.append(" values(notice_notice_id_seq.nextval, ?, ?, ?) ");

//    sql 실행
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[]{"notice_id"}    //insert 실행 후에 그 중 반환할 컬럼명
        );

        pstmt.setString(1, notice.getSubject());
        pstmt.setString(2, notice.getContent());
        pstmt.setString(3, notice.getAuthor());

        return pstmt;
      }
    }, keyHolder);

//    long notice_id = (Long) (keyHolder.getKey().longValue());
//    가져올 키 값이 여러 개인 경우
    Long notice_id = Long.valueOf(keyHolder.getKeys().get("notice_id").toString());

    return selectOne(notice_id);
//    회원가입 때 아이디도 그렇고, 상세 조회용 메소드를 들고 오네. 등록을 한 다음 확인용 결과를 보여주기 위해서인가.
  }

  /**
   * 전체조회
   *
   * @return
   */
  @Override
  public List<Notice> selectAll() {
    StringBuffer sql = new StringBuffer();

    sql.append(" select notice_id, subject, content, author, hit, cdate, udate ");
    sql.append(" from notice ");

    List<Notice> list = jdbcTemplate.query(
        sql.toString(),
        new BeanPropertyRowMapper<>(Notice.class)
    );
    return list;

  }

  /**
   * 상세조회
   *
   * @return
   */
  @Override
  public Notice selectOne(Long noticeId) {

    StringBuffer sql = new StringBuffer();

    sql.append(" select notice_id, subject, content, author, hit, cdate, udate ");
    sql.append(" from notice ");
    sql.append(" where notice_id = ? ");

    List<Notice> query = jdbcTemplate.query(
        sql.toString(), new BeanPropertyRowMapper<>(Notice.class), noticeId);

//    읭?? query.get(0)이 notice라고? 쿼리의 첫 번째(0번째 인덱스)?
    return (query.size() == 1) ? query.get(0) : null;
  }

  /**
   * 수정
   *
   * @param notice
   * @return
   */
  @Override
  public Notice update(Notice notice) {
    //  등록 로직 복붙
    //    sql 작성
    StringBuffer sql = new StringBuffer();

    sql.append(" update notice ");
    sql.append(" set subject = ?, ");
    sql.append("    content = ?, ");
    sql.append("    udate = systimestamp ");
    sql.append(" where notice_id = ? ");

//    sql 실행
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[]{"notice_id"}    //insert 실행 후에 그 중 반환할 컬럼명
        );

        pstmt.setString(1, notice.getSubject());
        pstmt.setString(2, notice.getContent());
        pstmt.setLong(3, notice.getNoticeId());

        return pstmt;
      }
    }, keyHolder);

//    long notice_id = (Long) (keyHolder.getKey().longValue());
//    가져올 키 값이 여러 개인 경우
    Long notice_id = Long.valueOf(keyHolder.getKeys().get("notice_id").toString());
    return selectOne(notice_id);
  }

  /**
   * 삭제
   *
   * @param noticeId
   * @return
   */
  @Override
  public int delete(Long noticeId) {

    StringBuffer sql = new StringBuffer();
    sql.append(" delete from notice ");
    sql.append(" where notice_id = ? ");

    int cnt = jdbcTemplate.update(sql.toString(), noticeId);

    return cnt;
  }

  /**
   * 조회 수 증가
   *
   * @param noticeId
   * @return
   */
  @Override
  public int updateHit(Long noticeId) {

    StringBuffer sql = new StringBuffer();

    sql.append(" update notice ");
    sql.append("    set hit = hit+1 ");
    sql.append(" where notice_id = ? ");

    int cnt = jdbcTemplate.update(sql.toString(), noticeId);

    return cnt;
//    결과가 제대로 나온다면 cnt값이 1이 나와야 함
  }

}
