package com.data.dao.art;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.data.dbmanager.DBManager;
import com.data.dto.art.ArtInfo;

public class ArtInfoDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 객체 생성시 jdbc 드라이버와 연동 및 Connection 객체 생성
	public ArtInfoDAO() {
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
            pstmt = conn.prepareStatement(SQL);
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
	
	//1. 리스트
	public List<ArtInfo> getList(String SQL){
		List<ArtInfo> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new ArtInfo(rs.getInt("art_no"), rs.getString("art_title"),rs.getString("art_content"),
						rs.getString("art_location"), rs.getString("start_date"), rs.getString("end_date"),rs.getString("art_duration"),
						rs.getString("art_age"), rs.getString("art_img_name"), rs.getBytes("art_img_data"),rs.getString("art_reg_date"),
						rs.getInt("art_price"), rs.getString("fk_user_userID")));
			}
		} catch (Exception e) { e.printStackTrace();
		} finally {
			if(rs  !=null) {try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(conn  != null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return list;
	}
	
	public int create_summary(ArtInfo dto) {
		int result = -1;
		String SQL ="insert into art_info(art_title, art_content, art_location, art_duration, art_age"
				+ "art_img_name, art_reg_date, art_price, fk_user_userID) values (?,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, dto.getArt_title());
			pstmt.setString(2, dto.getArt_content());
			pstmt.setString(3, dto.getArt_location());
			pstmt.setString(4, dto.getArt_duration());
			pstmt.setString(5, dto.getArt_age());
			pstmt.setString(6, dto.getArt_img_name());
			pstmt.setString(7, dto.getArt_reg_date());
			pstmt.setInt(8, dto.getArt_price());
			pstmt.setString(9, dto.getFk_user_userID());
		} catch (Exception e) { e.printStackTrace();
		} finally {
			if(pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }}
			if(conn  != null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}
		}
		return result;
	}
	
	public int write(ArtInfo dto) {
        String SQL = "INSERT INTO art_info (art_title, art_content, art_location, start_date, end_date, art_duration, art_age, art_img_name, art_img_data, art_reg_date, art_price, fk_user_userID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";

        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, dto.getArt_title());
            pstmt.setString(2, dto.getArt_content());
            pstmt.setString(3, dto.getArt_location());
            pstmt.setString(4,dto.getStart_date());
            pstmt.setString(5, dto.getEnd_date());
            pstmt.setString(6, dto.getArt_duration());
            pstmt.setString(7, dto.getArt_age());
            pstmt.setString(8, dto.getArt_img_name());
            pstmt.setBytes(9, dto.getArt_img_data());
            pstmt.setInt(10, dto.getArt_price());
            pstmt.setString(11, dto.getFk_user_userID());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return -1;
    }
	
	public ArtInfo detail(ArtInfo dto) {
    	String SQL = "SELECT * FROM art_info WHERE art_no = ?";
        ArtInfo result = new ArtInfo();
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, dto.getArt_no());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                result = new ArtInfo(rs.getInt("art_no"),
                        rs.getString("art_title"),
                        rs.getString("art_content"),
                        rs.getString("art_location"),
                        rs.getString("start_date"),
                		rs.getString("end_date"),
                		rs.getString("art_duration"),
                		rs.getString("art_age"),
                		rs.getString("art_img_name"),
                		rs.getBytes("art_img_data"),
                		rs.getString("art_reg_date"),
                		rs.getInt("art_price"),
                        rs.getString("fk_user_userID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
	 
    public int update(ArtInfo dto) {
		int result = -1;
		String SQL = "update art_info set art_title=?, start_date=?, end_date=?, art_location=? WHERE art_no=?";
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, dto.getArt_title());
			pstmt.setString(2, dto.getStart_date());
			pstmt.setString(3, dto.getEnd_date());
			pstmt.setString(4, dto.getArt_location());
			pstmt.setInt(5, dto.getArt_no());
			result = pstmt.executeUpdate();

		} catch (SQLException e) { e.printStackTrace();
		} finally {
			if(pstmt != null) {try { pstmt.close(); } catch (SQLException e) {  e.printStackTrace(); } }
			if(conn  != null) {try { conn.close(); }  catch (SQLException e) {  e.printStackTrace(); } }
		}
		return result;
		
	}
    
    public int delete (ArtInfo dto) {
		String SQL = "delete from art_info where art_no=?";
		int result = -1; 
		
		try {
			pstmt=conn.prepareStatement(SQL);
			pstmt.setInt(1, dto.getArt_no());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) { e.printStackTrace();
		}finally {
			if(pstmt !=null) {try { pstmt.close(); } catch (SQLException e) {  e.printStackTrace(); } }
			if(conn !=null) {try { conn.close(); } catch (SQLException e) {  e.printStackTrace(); } }
		}
		return result;
	}
	
	
	
	
	
	// 수정
	//String sql ="update art_info set art_title=?, atr_content=?, art_location=?, art_duration=?, art_age=?, "
	//		+ "art_img_name=?, art_reg_date=?, art_price=? where art_no=?";
	
	//삭제
	//String sql ="delete from art_info where art_no =?"
}
