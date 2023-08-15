package com.data.dao.board.free;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.data.dbmanager.DBManager;
import com.data.dto.board.free.FreeBoard;


public class FreeBoardDAO {	
	
	public List<FreeBoard> listpage() {
		return listpage("free_title", "", 1);
	}
	
	public List<FreeBoard> listpage(int startno) {
		return listpage("free_title", "", startno);
	}
	
	public List<FreeBoard> listpage(String field, String query, int startno) {
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		String sql = "select * from free_board where " + field + " like ? order by free_no desc limit ?,10";
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+query+"%");
			pstmt.setInt(2, startno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new FreeBoard
						(rset.getInt("free_no"), rset.getString("fk_user_userID"), rset.getString("free_password"), rset.getString("free_title"), rset.getString("free_content"), rset.getString("free_date"), rset.getInt("free_hit"), rset.getString("free_ip")));
			}			
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			if(rset != null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();} }
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();} }
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();} }
		}		
		return list;
	}
	
	public int listCnt() {
		return listCnt("free_title", "");
	}
		
	public int listCnt(String field, String query) {
		int total = -1;
		String sql = "select COUNT(*) from free_board where "+field+" like ? order by free_no DESC";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn=db.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+query+"%");
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
	
	public int insert(FreeBoard dto) {
		int result = -1;
		String sql = "insert into free_board (fk_user_userID, free_password, free_title, free_content, free_ip) values (?, ?, ?, ?, ?)";
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt = null; 
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, dto.getFk_user_userID());
			pstmt.setString(2, dto.getFree_password());
			pstmt.setString(3, dto.getFree_title());
			pstmt.setString(4, dto.getFree_content());
			pstmt.setString(5, InetAddress.getLocalHost().getHostAddress());			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException | UnknownHostException e) {e.printStackTrace();
		} finally {
			if(pstmt != null) {try{pstmt.close();} catch(Exception e) {e.printStackTrace();}}
			if(conn != null) {try{conn.close();} catch(Exception e) {e.printStackTrace();}}
		}		
		return result;
	}
	
	public int update_hit(FreeBoard dto) {
		int result = -1;
		String sql = "update free_board set free_hit=free_hit+1 where free_no=?";
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt = null; 
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, dto.getFree_no());						
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			if(pstmt != null) {try{pstmt.close();} catch(Exception e) {e.printStackTrace();}}
			if(conn != null) {try{conn.close();} catch(Exception e) {e.printStackTrace();}}
		}		
		return result;		
	}
	
	public FreeBoard detail(FreeBoard dto) {
		FreeBoard detail = new FreeBoard();
		String sql = "select * from free_board where free_no=?";
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
				
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getFree_no());
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				detail = new FreeBoard
						(rset.getInt("free_no"), rset.getString("fk_user_userID"), rset.getString("free_password"), rset.getString("free_title"), rset.getString("free_content"), rset.getString("free_date"), rset.getInt("free_hit"), rset.getString("free_ip"));
				}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			if(rset != null) {try {rset.close();} catch (SQLException e) {e.printStackTrace();} }
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();} }
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();} }
		}
		return detail;
	}
	
	public int update(FreeBoard dto) {
		int result = -1;
		String sql = "update free_board set free_title=?, free_content=? where free_no=? and free_password=?";
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, dto.getFree_title());
			pstmt.setString(2, dto.getFree_content());
			pstmt.setInt(3, dto.getFree_no());
			pstmt.setString(4, dto.getFree_password());			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			if(pstmt != null) {try{pstmt.close();} catch(Exception e) {e.printStackTrace();}}
			if(conn != null) {try{conn.close();} catch(Exception e) {e.printStackTrace();}}
		}		
		return result;
	}
	
	public int delete(FreeBoard dto) {
		int result = -1;
		String sql = "delete from free_board where free_no=? and free_password=?";
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getFree_no());
			pstmt.setString(2, dto.getFree_password());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();
		} finally {			
			if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();} }
			if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();} }
		}
		return result;
	}
}
