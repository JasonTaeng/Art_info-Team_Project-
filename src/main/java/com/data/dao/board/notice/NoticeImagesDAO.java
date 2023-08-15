package com.data.dao.board.notice;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.data.dbmanager.DBManager;
import com.data.dto.board.notice.NoticeImages;

public class NoticeImagesDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public NoticeImagesDAO() {
		try {
			DBManager db = new DBManager();
			conn = db.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Create 기능 메서드
    public int upload(NoticeImages dto) throws UnknownHostException {
    	String SQL = "INSERT INTO notice_images (no_img_name, no_img_data, no_img_date, fk_notice_board_no) VALUES (?,?,now(),?)";
        try {
    		pstmt = conn.prepareStatement(SQL);
        	pstmt.setString(1, dto.getNo_img_name());
        	pstmt.setBytes(2, dto.getNo_img_data());
            pstmt.setInt(3, dto.getFk_notice_board_no());
            return pstmt.executeUpdate(); // 성공이면 1 리턴
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류 또는 catch문 실행된 경우
    }
    
    // 해당 페이지의 사진 파일들을 ArrayList로 리턴하는 메서드
    public ArrayList<NoticeImages> getList(int fk_notice_board_no) throws IOException {
        ArrayList<NoticeImages> list = new ArrayList<NoticeImages>();
        String SQL = "SELECT * FROM notice_images WHERE fk_notice_board_no = ?";
        try {
    		pstmt = conn.prepareStatement(SQL);
    		pstmt.setInt(1, fk_notice_board_no);
    		rs = pstmt.executeQuery();
            while (rs.next()) {
            	NoticeImages dto = new NoticeImages();
            	dto.setNo_img_no(rs.getInt(1));
            	dto.setNo_img_name(rs.getString(2));
            	
//            	InputStream fis = rs.getBlob(3).getBinaryStream();
//            	dto.setNo_img_data( fis.readAllBytes() );
//				fis.close();
            	
            	dto.setNo_img_data( rs.getBytes(3) );
				
            	dto.setNo_img_date(rs.getString(4));
            	dto.setFk_notice_board_no(rs.getInt(5));
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public String getFileName(int fk_notice_board_no) {
    	String SQL = "SELECT no_img_name FROM notice_images WHERE fk_notice_board_no = ?";
    	String fileName = "";
        try {
    		pstmt = conn.prepareStatement(SQL);
    		pstmt.setInt(1, fk_notice_board_no);
    		rs = pstmt.executeQuery();
            while (rs.next()) {
            	fileName = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileName;
    }
	
	
}
