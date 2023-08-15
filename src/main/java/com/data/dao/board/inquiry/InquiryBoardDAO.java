package com.data.dao.board.inquiry;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.data.dbmanager.DBManager;
import com.data.dto.board.inquiry.InquiryBoard;



public class InquiryBoardDAO {
	
		//1.페이징처리 리스트
		public List<InquiryBoard> listpage (int startno) {
			List<InquiryBoard> list = new ArrayList<InquiryBoard>();
			String sql = "select * from inquiry_board order by in_no desc limit ?,10";
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn = db.getConnection();
				pstmt= conn.prepareStatement(sql);
				pstmt.setInt(1, startno);
				
				rset = pstmt.executeQuery();
				while(rset.next()) {
					list.add(new InquiryBoard(rset.getInt("in_no"), rset.getString("fk_user_userID"),rset.getString("in_title"),rset.getString("in_content"),
							rset.getString("in_password"),rset.getString("in_set"), rset.getString("in_date"), rset.getString("in_ip")));
				}

			} catch (SQLException e) {  e.printStackTrace();
			} finally {
				if(rset != null) {try { rset.close(); } catch (SQLException e) {  e.printStackTrace(); }}
				if(pstmt != null) {try { pstmt.close(); } catch (SQLException e) {  e.printStackTrace(); }}
				if(conn != null) {try { conn.close(); } catch (SQLException e) {  e.printStackTrace(); }}
			}
			
			return list;
			
		}
		
		//1-1 페이징
		public int listCnt() {
			int total = -1;
			String sql = "select  count(*) from  inquiry_board";
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			DBManager db = new DBManager();
			
			try {
				conn=db.getConnection();
				pstmt=conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					total = rset.getInt(1);
				}

			} catch (SQLException e) { e.printStackTrace();
			} finally {
				if(rset != null) {try { rset.close(); } catch (SQLException e) {  e.printStackTrace(); }}
				if(pstmt != null) {try { pstmt.close(); } catch (SQLException e) {  e.printStackTrace(); }}
				if(conn != null) {try { conn.close(); } catch (SQLException e) {  e.printStackTrace(); }}
			}
			return total;
			
		}
		
		// 2. 글삽입
		public int insert(InquiryBoard dto) { 
			int result = -1;
			String sql = "insert into inquiry_board(fk_user_userID, in_title, in_content, in_password, in_set,in_ip) values(?,?,?,?,?,?)";
			DBManager db = new DBManager();
	        
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            conn = db.getConnection();
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, dto.getFk_user_userID());
	            pstmt.setString(2, dto.getIn_title());
	            pstmt.setString(3, dto.getIn_content());
	            pstmt.setString(4, dto.getIn_password());
	            pstmt.setString(5, dto.getIn_set());
	            pstmt.setString(6, InetAddress.getLocalHost().getHostAddress());
	            //in_date 제거함
	            result = pstmt.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally 
	        { 
	          if (pstmt != null) { try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); } }
	          if (conn != null) { try { conn.close(); } catch (Exception e) { e.printStackTrace(); } }
	        }

	        return result;
	    }
		// 3. 해당번호 상세보기 (비밀번호 체크)
		public InquiryBoard detail(InquiryBoard dto) { 
			InquiryBoard detail = new InquiryBoard();
			String sql = "select*from inquiry_board where in_no=? and in_password=?";
			DBManager db = new DBManager();		
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);			
				pstmt.setInt(1, dto.getIn_no());
				pstmt.setString(2, dto.getIn_password());
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					detail =new InquiryBoard(rset.getInt("in_no"), rset.getString("fk_user_userID"),rset.getString("in_title"),rset.getString("in_content"),
							rset.getString("in_password"),rset.getString("in_set"), rset.getString("in_date"), rset.getString("in_ip"));
				}
				
			} catch (SQLException e) {e.printStackTrace();
			} finally {
				if(rset != null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();} }
				if(pstmt != null) {try{pstmt.close();} catch(Exception e) {e.printStackTrace();}}
				if(conn != null) {try{conn.close();} catch(Exception e) {e.printStackTrace();}}
			}
			return detail;
		}
		
		// 4. 해당번호 수정  기능
		public int update(InquiryBoard dto) { 
			int result = -1;
			String sql = "update inquiry_board set in_title=?, in_set=?, in_password=?, in_content=? where in_no=?";
			DBManager db = new DBManager();		  
			Connection conn = null; PreparedStatement pstmt = null;
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getIn_title());
				pstmt.setString(2, dto.getIn_set());
				pstmt.setString(3, dto.getIn_password());
				pstmt.setString(4, dto.getIn_content());
				pstmt.setInt(5, dto.getIn_no());
				result = pstmt.executeUpdate();
//				
			} catch (SQLException e) {e.printStackTrace();
			} finally {
				if(pstmt != null) {try{pstmt.close();} catch(Exception e) {e.printStackTrace();}}
				if(conn != null) {try{conn.close();} catch(Exception e) {e.printStackTrace();}}
			}		
			return result;
		}
		
		//5. 해당번호 삭제기능
		public int delte(InquiryBoard dto) {
			int result = -1;
			String sql = "delete from inquiry_board where in_no=? and in_password=?";
		
			DBManager db = new DBManager();
			Connection conn = null; PreparedStatement pstmt = null;
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getIn_no());
				pstmt.setString(2, dto.getIn_password());
				result = pstmt.executeUpdate();
		
			} catch (SQLException e) {e.printStackTrace();
			} finally {
				if(pstmt != null) {try{pstmt.close();} catch(Exception e) {e.printStackTrace();}}
				if(conn != null) {try{conn.close();} catch(Exception e) {e.printStackTrace();}}
			}
			return result;
		}
		//6. 비밀번호 검사 메서드
		public boolean checkPassword(InquiryBoard dto) {
			DBManager db = new DBManager();	
			boolean passwordMatched = false;
			Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
			String sql = "select in_password from inquiry_board	where in_no=?";
			
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getIn_no());
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					String savePassword = rset.getString("in_password");
					String inputPassword = dto.getIn_password();
					passwordMatched = savePassword.equals(inputPassword);
				}
					
				
			} catch (SQLException e) {

			}finally {
				if(rset != null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();} }
				if(pstmt != null) {try{pstmt.close();} catch(Exception e) {e.printStackTrace();}}
				if(conn != null) {try{conn.close();} catch(Exception e) {e.printStackTrace();}}
			}
			
			return passwordMatched;
		}
		
		// 7. 게시글 답변 상태 변경
		public int updateAnswerStatus( String inSet, int inNo) {
		    int result = -1;
		    String sql = "update inquiry_board set in_set=? where in_no=?";
		    DBManager db = new DBManager();
		    Connection conn = null;
		    PreparedStatement pstmt = null;

		    try {
		        conn = db.getConnection();
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, inSet);
		        pstmt.setInt(2, inNo);
		        result = pstmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        if (pstmt != null) { try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); } }
		        if (conn != null) { try { conn.close(); } catch (Exception e) { e.printStackTrace(); } }
		    }
		    return result;
		}
}		
		