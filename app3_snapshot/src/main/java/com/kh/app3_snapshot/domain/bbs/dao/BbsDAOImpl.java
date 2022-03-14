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
     * 원글 등록.
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
        sql.append( "from bbs " );
        sql.append( "where bbs_id = ? " );

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

    /**
     * 답글 작성
     * @param pbbsId   상위 게시글 번호
     * @param replyBbs 답글
     * @return
     */
    @Override
    public Long saverReply(Long pbbsId, Bbs replyBbs) {

//       상위글의 정보를 우선 반영하고 필요한 만큼 업데이트
       Bbs bbs = addInfoOfParentToChild(pbbsId, replyBbs);

//       세팅 된 답글을 등록(insert). 등록은 원글과 비슷.
        StringBuffer sql = new StringBuffer();
        sql.append( "insert into bbs (bbs_id, bcategory, title, email, nickname, bcontent, pbbs_id, bgroup, step, bindent )" );
        sql.append( "values( bbs_bbs_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );


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
                pstmt.setLong(6, bbs.getPbbsId());
                pstmt.setLong(7, bbs.getBgroup());
                pstmt.setInt(8, bbs.getStep());
                pstmt.setInt(9, bbs.getBindent());

                return pstmt;
            }
        }, keyHolder);

        return Long.valueOf(keyHolder.getKeys().get("bbs_id").toString());
    }

//    답글에 상위글 정보 반영할 메소드
    private Bbs addInfoOfParentToChild(Long pbbsId, Bbs replyBbs) {
//        답글을 달려는 상위글의 정보
        Bbs bbs = findByBbsId(pbbsId);

//        상위글의 카테고리 똑같이 가져오기
        replyBbs.setBcategory(bbs.getBcategory());

//        bgroup 로직
//        답글의 bgroup = 상위글의 bgroup
        replyBbs.setBgroup(bbs.getBgroup());

        //        step 로직
        int affectedRows = updateBstep(bbs);
//        답글의 bstep 값은 상위글이 bstep +1
        replyBbs.setStep(bbs.getStep() + 1);

//        bindent 로직
//        답글의 들여쓰기 = 상위글의 들여쓰기 +1
        replyBbs.setBindent(bbs.getBindent() + 1);

        replyBbs.setPbbsId(pbbsId);
        return replyBbs;
    }

//    상위글과 동일한 그룹 step 반영하는 메소드
    private int updateBstep(Bbs bbs) {
//        step 로직
//        1) 상위글의 bgroup값과 동일한 게시글 중 상위글의 step 보다 큰 게시글의 step 을 1씩 증가.
//        2) 답글의 step 값은 상위글의 step 값+1
        StringBuffer sql = new StringBuffer();
        sql.append(" update bbs ");
        sql.append(" set step = step + 1 ");
        sql.append(" where bgroup = ? ");
        sql.append(" and bstep > ? ");

        int affectedRows = jdbcTemplate.update(sql.toString(), bbs.getBgroup(), bbs.getStep());

        return affectedRows;
    }

    /**
     * 조회수 증가
     * @param id 게시글 번호
     * @return 변경된 조회수
     */
    @Override
    public int increaseHitCount(Long id) {
        StringBuffer sql = new StringBuffer();

        sql.append(" update bbs ");
        sql.append("    set hit = hit+1 ");
        sql.append(" where bbs_id = ? ");

        int affectedRows = jdbcTemplate.update(sql.toString(), id);

        return affectedRows;
//    결과가 제대로 나온다면 cnt값이 1이 나와야 함
    }

    /**
     * 전체 게시물 수
     * @return db에 저장돼있는 게시글 전체 건수
     */
    @Override
    public int totalCount() {
//        그냥 게시물 다 세면 되는 거 아냐? 반환이 리스트도 아니고 그냥 숫자니까..
//        sql문이 단순하면 그냥 스트링 변수 써도 좋다. 근데 그럼 스트링버퍼는 왜 쓰는 거지?
        String sql = "select count(*) from bbs";

//       결과 나왔으면 jdbc로 매핑해야하는데 뭘 써야 하지? > 단일 결과는 쿼리포오브젝트. 다수 결과면 쿼리()에서 로우매퍼 쓰는 구나.
        Integer cnt = jdbcTemplate.queryForObject(sql, Integer.class);

        return cnt;


    }
}
