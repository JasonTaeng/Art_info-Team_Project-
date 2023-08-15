package com.data.dao.board.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.data.dbmanager.DBManager;
import com.data.dto.board.notice.NoticeList;

public class NoticeListDAO {
	
	// UserDAO와 달리 메서드 내에서 다른 매세드를 부르며 statement 충돌이 일어나서 지역 변수로 설정
	private Connection conn;
	private ResultSet rs;
	
	// 객체 생성시 jdbc 드라이버와 연동 및 Connection 객체 생성
	public NoticeListDAO() {
		try {
			DBManager db = new DBManager();
			conn = db.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	// 제일 높은 게시물 다음 번호 리턴
	public int getNext(String SQL, String keyword) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
    		pstmt.setString(1, "%"+keyword+"%"); 
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
	
    // 해당 페이지의 필요한 값들을 ArrayList로 리턴하는 메서드
    public ArrayList<NoticeList> getList(String SQL, String keyword) {
        ArrayList<NoticeList> list = new ArrayList<NoticeList>();
        try {
    		PreparedStatement pstmt = conn.prepareStatement(SQL);
    		pstmt.setString(1, "%"+keyword+"%");
    		rs = pstmt.executeQuery();
            while (rs.next()) {
            	NoticeList dto = new NoticeList();
            	dto.setNo_no(rs.getInt(1));
            	dto.setNo_title(rs.getString(2));
            	dto.setNo_date(rs.getString(3));
            	dto.setNo_publish(rs.getString(4));
            	dto.setNo_publish_date(rs.getString(5));
            	dto.setNo_hit(rs.getInt(6));
            	dto.setFk_user_userID(rs.getString(7));
            	dto.setCntComment(rs.getInt(8));
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

		
		
}
