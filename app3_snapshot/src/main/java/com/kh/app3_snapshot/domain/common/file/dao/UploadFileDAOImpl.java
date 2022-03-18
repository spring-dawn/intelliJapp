package com.kh.app3_snapshot.domain.common.file.dao;

import com.kh.app3_snapshot.domain.common.file.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
public class UploadFileDAOImpl implements UploadFileDAO{

    private final JdbcTemplate jdbcTemplate;

    /**
     * 업로드 파일 단일 등록
     * @param uploadFile
     */
    @Override
    public Long addFile(UploadFile uploadFile) {

        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO uploadfile ( " );
        sql.append("        uploadfile_id, " );
        sql.append("        code, " );
        sql.append("        rid, " );
        sql.append("        store_filename, " );
        sql.append("        upload_filename, " );
        sql.append("        fsize, " );
        sql.append("        ftype " );
        sql.append(" ) VALUES ( " );
        sql.append("        uploadfile_uploadfile_id_seq.nextval, " );
        sql.append("        ?, " );
        sql.append("        ?, " );
        sql.append("        ?, " );
        sql.append("        ?, " );
        sql.append("        ?, " );
        sql.append("        ? " );
        sql.append( " ) " );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        sql.toString(),
                        new String[]{"uploadFile_id"});

                pstmt.setString(1, uploadFile.getCode());
                pstmt.setLong(2, uploadFile.getRid());
                pstmt.setString(3, uploadFile.getStore_finename());
                pstmt.setString(4, uploadFile.getUpload_filename());
                pstmt.setString(5, uploadFile.getFsize());
                pstmt.setString(6, uploadFile.getFtype());

                return pstmt;
            }
        }, keyHolder);


        return Long.valueOf(keyHolder.getKeys().get("uploadfile_id").toString());
    }


    /**
     * 업로드 파일 다수 등록
     * @param uploadFile
     * @return 파일id
     */
    @Override
    public void addFile(List<UploadFile> uploadFile) {
//        오? batch system을 쓴다고? 좋네
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO uploadfile ( " );
        sql.append("        uploadfile_id, " );
        sql.append("        code, " );
        sql.append("        rid, " );
        sql.append("        store_filename, " );
        sql.append("        upload_filename, " );
        sql.append("        fsize, " );
        sql.append("        ftype " );
        sql.append(" ) VALUES ( " );
        sql.append("        uploadfile_uploadfile_id_seq.nextval, " );
        sql.append("        ?, " );
        sql.append("        ?, " );
        sql.append("        ?, " );
        sql.append("        ?, " );
        sql.append("        ?, " );
        sql.append("        ? " );
        sql.append( " ) " );

//        배치 처리: 여러 건의 갱신 작업을 한 번에 처리하므로 단건반복처리보다 효율적이다.
        jdbcTemplate.batchUpdate(
                sql.toString(),
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//                        참조변수 ps, 컬렉션 인덱스 i
                        ps.setString(1, uploadFile.get(i).getCode());
                        ps.setLong(2, uploadFile.get(i).getRid());
                        ps.setString(3, uploadFile.get(i).getStore_finename());
                        ps.setString(4, uploadFile.get(i).getUpload_filename());
                        ps.setString(5, uploadFile.get(i).getFsize());
                        ps.setString(6, uploadFile.get(i).getFtype());

                    }

                    //배치처리 할 건수(컬렉션 안의 개수만큼)
                    @Override
                    public int getBatchSize() {
                        return uploadFile.size();
                    }
                });

    }
}
