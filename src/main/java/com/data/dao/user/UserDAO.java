package com.data.dao.user;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.data.dbmanager.DBManager;
import com.data.dto.User;

public class UserDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			DBManager db = new DBManager();
			conn = db.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 관리자, 회원 공통 기능 - 1. 로그인 기능
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword))
					return 1; // 로그인 성공
				else
					return 0; // 비밀번호 불일치
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터베이스 오류 또는 catch문 실행된 경우
	}
	
	// 관리자, 회원 공통 기능 - 1-1. 로그인 시 마지막 아이피 업데이트
	public int updateLastIP(String userID) {
		String SQL = "UPDATE user SET userLastIP=? WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, InetAddress.getLocalHost().getHostAddress());
			pstmt.setString(2, userID);
			return pstmt.executeUpdate(); // 성공이면 1 리턴
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류 또는 catch문 실행된 경우
	}
		
	// 관리자, 회원 공통 기능 - 2.회원가입 기능 또는  관리자 등록 기능
	public int join(User user) throws UnknownHostException {
		try {
			String SQL = null;
			SQL = "INSERT INTO user (userID, userPassword, userName, userGender, userEmail, userPhoneNum, userAuthority, userJoinIP, userLastIP) VALUES(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			pstmt.setString(6, user.getUserPhoneNum());
			pstmt.setInt(7, user.getUserAuthority());
			pstmt.setString(8, InetAddress.getLocalHost().getHostAddress());
			pstmt.setString(9, InetAddress.getLocalHost().getHostAddress());
			return pstmt.executeUpdate(); // 성공이면 1 리턴
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류, 아이디 중복 또는 catch문 실행된 경우
	}
	
	// 관리자, 회원 공통 기능 - 3.관리자(관리자 페이지) 또는 회원(마이페이지) 조회
	public User getUser(String userID) {
		String SQL = "SELECT* FROM user WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				User user = new User();
	        	user.setUserID(rs.getString(1));
	        	user.setUserPassword(rs.getString(2));
	        	user.setUserName(rs.getString(3));
	        	user.setUserGender(rs.getString(4));
	        	user.setUserEmail(rs.getString(5));
	        	user.setUserPhoneNum(rs.getString(6));
	        	user.setUserDate(rs.getString(7));
	        	user.setUserEnabled(rs.getInt(8));
	        	user.setUserSuspension(rs.getString(9));
	        	user.setUserAuthority(rs.getInt(10));
	        	user.setUserJoinIP(rs.getString(11));
	        	user.setUserLastIP(rs.getString(12));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return null; // 데이터베이스 오류 또는 catch문 실행된 경우
	}
	
	
	/* ^O^ ^O^ ^O^ ^O^ ^O^ ^O^ ^O^ ^O^ ^O^ ^O^ */
	
	// 관리자 기능 - 0. 조건에 맞는 사람 수 + 1 COUNT --> 없애도 되나?
	public int getNext(String sqlCOUNT) {
        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlCOUNT);
            rs = pstmt.executeQuery();
            if(rs.next()) {
            	return rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류 또는 catch문 실행된 경우
    }
	
	// 관리자 기능 - 1.전체 or 가입 or 탈퇴 or 제명 / 관리자 or 회원 조회
	public ArrayList<User> getList(String SQL) {
        ArrayList<User> list = new ArrayList<User>();
        try {
        	pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	User user = new User();
	        	user.setUserID(rs.getString(1));
	        	user.setUserPassword(rs.getString(2));
	        	user.setUserName(rs.getString(3));
	        	user.setUserGender(rs.getString(4));
	        	user.setUserEmail(rs.getString(5));
	        	user.setUserPhoneNum(rs.getString(6));
	        	user.setUserDate(rs.getString(7));
	        	user.setUserEnabled(rs.getInt(8));
	        	user.setUserSuspension(rs.getString(9));
	        	user.setUserAuthority(rs.getInt(10));
	        	user.setUserJoinIP(rs.getString(11));
	        	user.setUserLastIP(rs.getString(12));
	        	list.add(user);
	        }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		return list;
	}
	
	// 관리자 기능 - 4.[상태 변경] 회원(관리자 포함) 가입,탈퇴,제명. cf)일반 관리자가 다른 관리자를 조정하려는지 앞단에서 넘어와야 의미가 있음-->걍 앞단에서 막자.
	// 관리자 기능 - 5.[등급 변경] 회원(관리자) 제명 or 복구 (userEnabled에 -1을 곱하는 로직)
	// 관리자 기능 - 6.[7일 이용정지] 회원만 (DB userSuspension datetime 설정)
	public int updateUser(User user, String field, int value) {
		String SQL = null;
		// [상태 변경]메인 관리자일 경우 관리자와 회원 모두 등급 조정 가능 <-> 일반 관리자는 회원만 가능
		if(field.equals("userEnabled")) { // 가입 or 제명으로 상태 변경 시
			SQL = "UPDATE user SET userEnabled = ? WHERE userID = ?";
		} else if(field.equals("userSuspension")) { // 이용정지 시
			SQL = "UPDATE user SET userEnabled = ?, userSuspension = now() WHERE userID = ?"; // 7일 이용정지로 상태 변경
		} else if(field.equals("userAuthority")) { // 둥급 변경 시
			SQL = "UPDATE user SET userAuthority = ? WHERE userID = ?";
		}
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, value);
			pstmt.setString(2, user.getUserID());
			return pstmt.executeUpdate(); // 성공이면 1 리턴
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류 또는 catch문 실행된 경우
	}
	
	
	
	/* ^O^ ^O^ ^O^ ^O^ ^O^ ^O^ ^O^ ^O^ ^O^ ^O^ */

	
	
	// 회원 기능 - 1.아이디 찾기 기능
	
	
	// 회원 기능 - 2.비밀번호 찾기 기능
	
	// 회원 기능 - 3. 내 정보 수정 기능(비밀번호, 이메일, 폰 번호만 수정 가능)
	public int updateMyProfile(User user) {
		String SQL = "UPDATE user SET userPassword=?, userEmail=?, userPhoneNum=? WHERE userID=?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserPassword());
			pstmt.setString(2, user.getUserEmail());
			pstmt.setString(3, user.getUserPhoneNum());
			pstmt.setString(4, user.getUserID());
			return pstmt.executeUpdate(); // 성공이면 1이상 리턴
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류 또는 catch문 실행된 경우
	}
	
	// 회원 기능 - 4.탈퇴 기능
	public int disabledByUser(String userID) {
		String SQL = "UPDATE user SET userEnabled=0 WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			return pstmt.executeUpdate(); // 성공이면 1 리턴
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류 또는 catch문 실행된 경우
	}
	
	
}
