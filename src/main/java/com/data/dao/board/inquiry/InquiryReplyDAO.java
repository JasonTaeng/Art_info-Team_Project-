package com.data.dao.board.inquiry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.data.dbmanager.DBManager;
import com.data.dto.board.inquiry.InquiryReply;

	public class InquiryReplyDAO {
		
	    // 1. 해당게시글에 있는 답변 조회
	    public InquiryReply Rdetail(InquiryReply dto) {
	        InquiryReply detail = new InquiryReply();
	        String sql = "select r.in_reply_content from inquiry_reply r LEFT JOIN inquiry_board b "
	        		+ "ON r.in_reply_no = b.in_no "
	        		+ "where b.in_no = ?";
	        DBManager db = new DBManager();
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rset = null;

	        try {
	            conn = db.getConnection();
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, dto.getIn_reply_no());
	            rset = pstmt.executeQuery();
	            
	                if(rset.next()) {
	                	detail= new InquiryReply(rset.getString("in_reply_content"));
	                }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	        	if(rset != null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();} }
				if(pstmt != null) {try{pstmt.close();} catch(Exception e) {e.printStackTrace();}}
				if(conn != null) {try{conn.close();} catch(Exception e) {e.printStackTrace();}}
	        }

	        return detail;
	    }
	    
	    // 2. 답변 쓰기
	    public int insert(InquiryReply dto) {
	        int result = -1;
	        String sql = "INSERT INTO Inquiry_reply(in_reply_no, fk_user_userID, in_reply_content, in_reply_check) VALUES (?, ?, ?, ?)";
	        DBManager db = new DBManager();
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            conn = db.getConnection();
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, dto.getIn_reply_no());
	            pstmt.setString(2, dto.getFk_user_userID());
	            pstmt.setString(3, dto.getIn_reply_content());
	            pstmt.setString(4, dto.getIn_reply_check());

	            result = pstmt.executeUpdate();
	        } catch (SQLException e) { e.printStackTrace();
	        } finally {
	            if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
	            if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
	        }

	        return result;
	    }
	    // 3. 답변 조회 우선 패스 필요 없는듯
	    // select * from inquiry_reply where in_reply_no = ?
	    
	    // 3. 답변 수정
	    public int update(InquiryReply dto) {
	        int result = -1;
	        String sql = "UPDATE Inquiry_reply SET in_reply_content = ? WHERE in_reply_no = ?";
	        DBManager db = new DBManager();
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            conn = db.getConnection();
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, dto.getIn_reply_content());
	            pstmt.setInt(2, dto.getIn_reply_no());

	            result = pstmt.executeUpdate();
	        } catch (SQLException e) { e.printStackTrace();
	        } finally {
	            if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
	            if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
	        }
	        return result;
	    }

	    // 5. 답변 삭제 삭제하시겠습니까? 모달 띄우기
	    public int delete(InquiryReply dto) {
	        int result = -1;
	        String sql = "DELETE FROM Inquiry_reply WHERE in_reply_no = ?";
	        DBManager db = new DBManager();
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            conn = db.getConnection();
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, dto.getIn_reply_no());

	            result = pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
	            if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
	        }

	        return result;
	    }
	    
	    //6. 답변확인 InquiryReply가 아닌 InquiryBoard의 값을 받는다? 안될텐데
	    public int answerCheck(int num) {
	    	
	    	DBManager db = new DBManager();
	    	Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
	    	String sql = "select count(*) from inquiry_reply where in_reply_no = ?";
	    	int count = 0;
	    	try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
	    		rset = pstmt.executeQuery();
	    	
	    		if(rset.next())
	    			count = rset.getInt(1);
	    			
			} catch (SQLException  e) {
				e.printStackTrace();
			}finally {
	        	if(rset != null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();} }
				if(pstmt != null) {try{pstmt.close();} catch(Exception e) {e.printStackTrace();}}
				if(conn != null) {try{conn.close();} catch(Exception e) {e.printStackTrace();}}
	        }
	    	
	    	return count;
	    }
	    //7. 답변확인 처리 위한 작업
	 // 7. 전체 리스트 조회
	    public List<InquiryReply> list() {
	        List<InquiryReply> list = new ArrayList<>();
	        String sql = "SELECT * FROM Inquiry_reply";
	        DBManager db = new DBManager();
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rset = null;

	        try {
	            conn = db.getConnection();
	            pstmt = conn.prepareStatement(sql);
	            rset = pstmt.executeQuery();
	            while (rset.next()) {
	                InquiryReply dto = new InquiryReply();
	                dto.setIn_reply_no(rset.getInt("in_reply_no"));
	                dto.setFk_user_userID(rset.getString("fk_user_userID"));
	                dto.setIn_reply_content(rset.getString("in_reply_content"));
	                dto.setIn_reply_check(rset.getString("in_reply_check"));

	                list.add(dto);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (rset != null) { try { rset.close(); } catch (SQLException e) { e.printStackTrace(); } }
	            if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
	            if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
	        }

	        return list;
	    }
	}