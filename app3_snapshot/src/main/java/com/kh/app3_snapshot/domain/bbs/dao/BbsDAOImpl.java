package com.kh.app3_snapshot.domain.bbs.dao;

import com.kh.app3_snapshot.domain.notice.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class BbsDAOImpl implements BbsDAO {

//    final은 상수니까 변경되지 않을 초기값을 반드시 할당해야 하는데, 초기화를 안 해주면 에러가 난다.
//    객체를 따로 만들어 쓰진 않고 '주입 받는다'고 한다. 생성자, 세터 등등.
    private final JdbcTemplate jdbcTemplate;

    /**
     * 원글 등록. Notice에서 코드 덮어씀.
     * @param bbs 
     * @return
     */
    @Override
    public Long saveOrigin(Bbs bbs) {
        //    sql 작성
        StringBuffer sql = new StringBuffer();
        sql.append( "insert into bbs (bbs_id, bcategory, title, email, nickname, bcontent, bgroup )" );
        sql.append( "values( bbs_bbs_id_seq.nextval, ?, ?, ?, ?, ?, bbs_bbs_id_seq.currval )" );

//    sql 실행
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        sql.toString(),
                        new String[]{"bbs_id"}    //insert 실행 후에 그 중 반환할 컬럼명. keyholder가 받아준다.
                );
//              각 값을 매칭
                pstmt.setString(1, bbs.getBcategory());
                pstmt.setString(2, bbs.getTitle());
                pstmt.setString(3, bbs.getEmail());
                pstmt.setString(4, bbs.getNickname());
                pstmt.setString(5, bbs.getBcontent());

                return pstmt;
            }
        }, keyHolder);

        return Long.valueOf(keyHolder.getKeys().get("bbs_id").toString());
//        아직은 상세페이지 메소드가 없어서 사용할 수 없다
//    회원가입 때 아이디도 그렇고, 상세 조회용 메소드를 들고 오네. 등록을 한 다음 확인용 결과를 보여주기 위해서인가.
    }

    /**
     * 게시판 조회 
     * @return
     */
    @Override
    public List<Bbs> findAll() {
        StringBuffer sql = new StringBuffer();

        sql.append( "select bbs_id, bcategory, title, email, nickname, hit, bcontent, bgroup," +
                " step, bindent, status, cdate, udate " );
        sql.append( "from bbs" );



        List<Bbs> list = jdbcTemplate.query(
                sql.toString(),
                new BeanPropertyRowMapper<>(Bbs.class)     //단일 타입과 달리 여러 타입인 경우.
        );
        return list;
    }

    /**
     * 게시물 개별 조회
     * @param id 게시글 번호
     * @return
     */
    @Override
    public Bbs findByBbsId(Long id) {
        StringBuffer sql = new StringBuffer();

        sql.append( "select bbs_id, bcategory, title, email, nickname, hit, bcontent, pbbs_id, bgroup, STEP, bindent, status, cdate, udate " );
        sql.append( "from bbs" );
        sql.append( "where bbs_id = ?" );

        Bbs bbsItem = null;
        try {
            bbsItem = jdbcTemplate.queryForObject(
                    sql.toString(),
                    new BeanPropertyRowMapper<>(Bbs.class),
                    id);
        }catch (Exception e){ // 1건을 못찾으면
            bbsItem = null;
        }
        return bbsItem;
    }

    /**
     * 게시글 삭제
     * @param id 게시글 번호
     * @return
     */
    @Override
    public int deleteByBbsId(Long id) {
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM bbs ");
        sql.append(" WHERE bbs_id = ? ");

        int updateItemCount = jdbcTemplate.update(sql.toString(), id);

        return updateItemCount;
    }

    /**
     * 게시글 수정
     * @param id 게시글 번호
     * @param bbs 수정 내용
     * @return
     */
    @Override
    public int updateByBbsId(Long id, Bbs bbs) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE bbs ");
        sql.append("   SET bcategory = ?, ");
        sql.append("       title = ?, ");
        sql.append("       bcontent = ?, ");
        sql.append("       udate = systimestamp ");
        sql.append(" WHERE bbs_id = ? ");

        int updatedItemCount = jdbcTemplate.update(
                sql.toString(),
                bbs.getBcategory(),
                bbs.getTitle(),
                bbs.getBcontent(),
                id
        );

        return updatedItemCount;
    }

    @Override
    public Long saverReply(Bbs bbs) {
        return null;
    }

    @Override
    public int hitCount(Long id) {
        return 0;
    }

    @Override
    public int totalCount() {
        return 0;
    }
}
