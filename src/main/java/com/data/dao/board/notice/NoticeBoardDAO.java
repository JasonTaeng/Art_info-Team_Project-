package com.data.dao.board.notice;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.data.dbmanager.DBManager;
import com.data.dto.board.notice.NoticeBoard;

public class NoticeBoardDAO {
	
	// UserDAO와 달리 메서드 내에서 다른 매세드를 부르며 statement 충돌이 일어나서 지역 변수로 설정
	private Connection conn;
	private ResultSet rs;
	
	// 객체 생성시 jdbc 드라이버와 연동 및 Connection 객체 생성
	public NoticeBoardDAO() {
		try {
			DBManager db = new DBManager();
			conn = db.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 제일 높은 게시물 다음 번호 리턴
	public int getNext(String SQL) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	return rs.getInt(1) + 1;
            }
            return 1; // 첫 게시물인 경우
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류 또는 catch문 실행된 경우
    }
		
    // Create 기능 메서드
    public int write(NoticeBoard dto) throws UnknownHostException {
    	String SQL = null;
    	PreparedStatement pstmt = null;
        try {
        	if(dto.getNo_publish()!=null && dto.getNo_publish().equals("공개")) {
        		SQL = "INSERT INTO notice_board (no_title, no_content, no_publish, no_publish_date, no_ip, fk_user_userID) VALUES (?,?,?,now(),?,?)";
        		pstmt = conn.prepareStatement(SQL);
            	pstmt.setString(1, dto.getNo_title());
                pstmt.setString(2, dto.getNo_content());
                pstmt.setString(3, dto.getNo_publish());
                pstmt.setString(4, InetAddress.getLocalHost().getHostAddress());
                pstmt.setString(5, dto.getFk_user_userID());
        	} else {
        		SQL = "INSERT INTO notice_board (no_title, no_content, no_ip, fk_user_userID) VALUES (?,?,?,?)";
        		pstmt = conn.prepareStatement(SQL);
            	pstmt.setString(1, dto.getNo_title());
                pstmt.setString(2, dto.getNo_content());
                pstmt.setString(3, InetAddress.getLocalHost().getHostAddress());
                pstmt.setString(4, dto.getFk_user_userID());
        	}
            return pstmt.executeUpdate(); // 성공이면 1 리턴
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류 또는 catch문 실행된 경우
    }
    
    // Read에서 조회수 증가를 위한 메서드
    public void plusHit(int no_no) {
        String SQL = "UPDATE notice_board set no_hit=no_hit+1 WHERE no_no=?"; 
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, no_no);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Read와 Update에서 NoticeBoard 인스턴스를 던지기 위한 메서드
    public NoticeBoard getNoticeBoard(int no_no) {
        String SQL = "SELECT * FROM notice_board WHERE no_no = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, no_no);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                NoticeBoard dto = new NoticeBoard();
                dto.setNo_no(rs.getInt(1));
            	dto.setNo_title(rs.getString(2));
            	dto.setNo_content(rs.getString(3));
            	dto.setNo_date(rs.getString(4));
            	dto.setNo_publish(rs.getString(5));
            	dto.setNo_publish_date(rs.getString(6));
            	dto.setNo_hit(rs.getInt(7));
            	dto.setNo_ip(rs.getString(8));
            	dto.setFk_user_userID(rs.getString(9));
                return dto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // 데이터베이스 오류 또는 catch문 실행된 경우
    }
    
    // Edit 기능 메서드
    public int edit(NoticeBoard dto) {
        String SQL = "UPDATE notice_board SET no_title=?, no_content=? WHERE no_no = ?";
        try {
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, dto.getNo_title());
            pstmt.setString(2, dto.getNo_content());
            pstmt.setInt(3, dto.getNo_no());
            return pstmt.executeUpdate(); // 성공시 1 리턴
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류 또는 catch문 실행된 경우
    }
    
    // Update, Delete 기능 메서드
    public int updateDelete(String SQL, int no_no) {
    	try {
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, no_no);
            return pstmt.executeUpdate(); // 성공시 1 리턴
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류 또는 catch문 실행된 경우
    }

}
