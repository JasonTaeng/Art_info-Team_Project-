package com.data.dao.board.notice;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.data.dbmanager.DBManager;
import com.data.dto.board.notice.NoticeComment;

public class NoticeCommentDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public NoticeCommentDAO() {
		try {
			DBManager db = new DBManager();
			conn = db.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Create 기능 메서드
    public int write(NoticeComment dto) throws UnknownHostException {
    	String SQL = "INSERT INTO notice_comment (no_comm_content, no_comm_date, fk_user_userID, fk_notice_board_no) VALUES (?,now(),?,?)";
        try {
    		pstmt = conn.prepareStatement(SQL);
        	pstmt.setString(1, dto.getNo_comm_content());
            pstmt.setString(2, dto.getFk_user_userID());
            pstmt.setInt(3, dto.getFk_notice_board_no());
            return pstmt.executeUpdate(); // 성공이면 1 리턴
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류 또는 catch문 실행된 경우
    }
    
    // 해당 페이지의 댓글들을 ArrayList로 리턴하는 메서드
    public ArrayList<NoticeComment> getList(int no_no) {
        ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		String SQL = "SELECT * FROM notice_comment WHERE fk_notice_board_no = ? ORDER BY no_comm_date DESC";
        try {
    		pstmt = conn.prepareStatement(SQL);
    		pstmt.setInt(1, no_no);
    		rs = pstmt.executeQuery();
            while (rs.next()) {
            	NoticeComment dto = new NoticeComment();
                dto.setNo_comm_no(rs.getInt(1));
            	dto.setNo_comm_content(rs.getString(2));
            	dto.setNo_comm_date(rs.getString(3));
            	dto.setFk_user_userID(rs.getString(4));
            	dto.setFk_notice_board_no(rs.getInt(5));
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
	
}
