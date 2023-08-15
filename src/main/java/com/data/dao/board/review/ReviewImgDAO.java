package com.data.dao.board.review;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.data.dbmanager.DBManager;
import com.data.dto.board.review.ReviewImg;

public class ReviewImgDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ReviewImgDAO() {
		try {
			DBManager db = new DBManager();
			conn = db.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Create 기능 메서드
    public int upload(ReviewImg dto) throws UnknownHostException {
    	String SQL = "INSERT INTO review_img (re_img_name, re_img_data, re_img_date, fk_review_board_re_no) VALUES (?,?,now(),?)";
        try {
    		pstmt = conn.prepareStatement(SQL);
        	pstmt.setString(1, dto.getRe_img_name());
        	pstmt.setBytes(2, dto.getRe_img_data());
            pstmt.setInt(3, dto.getFk_review_board_re_no());
            return pstmt.executeUpdate(); // 성공이면 1 리턴
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류 또는 catch문 실행된 경우
    }
    
    // 해당 페이지의 사진 파일들을 ArrayList로 리턴하는 메서드
    public ArrayList<ReviewImg> getList(int fk_review_board_re_no) throws IOException {
        ArrayList<ReviewImg> list = new ArrayList<ReviewImg>();
        String SQL = "SELECT * FROM review_img WHERE fk_review_board_re_no = ?";
        try {
    		pstmt = conn.prepareStatement(SQL);
    		pstmt.setInt(1, fk_review_board_re_no);
    		rs = pstmt.executeQuery();
            while (rs.next()) {
            	ReviewImg dto = new ReviewImg();
            	dto.setRe_img_no(rs.getInt(1));
            	dto.setRe_img_name(rs.getString(2));
            	dto.setRe_img_data(rs.getBytes(3));
            	dto.setRe_img_date(rs.getString(4));
            	dto.setFk_review_board_re_no(rs.getInt(5));
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public String getFileName(int fk_review_board_re_no) {
    	String SQL = "SELECT re_img_name FROM review_img WHERE fk_review_board_re_no = ?";
    	String fileName = "";
        try {
    		pstmt = conn.prepareStatement(SQL);
    		pstmt.setInt(1, fk_review_board_re_no);
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
