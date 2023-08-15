package com.data.service.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dbmanager.DBManager;
import com.data.service.Service;

public class CheckID implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		DBManager db = new DBManager();
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		int cnt = -1;
		String result = "<p style='color: green;'>사용 가능한 아이디입니다.</p>";
		String SQL = "SELECT COUNT(*) AS cnt FROM user WHERE userID = ?";

		try {
		    conn = db.getConnection();
		    pstmt = conn.prepareStatement(SQL);
		    pstmt.setString(1, request.getParameter("userID"));
		    rset = pstmt.executeQuery();
		    if (rset.next()) {
		        cnt = rset.getInt("cnt");
		    }
		    if (cnt == 1) {
		        result = "<p style='color: red;'>동일한 아이디가 존재합니다.</p>";
		    }
		    out.println(result);
		} catch (SQLException e) {
		    e.printStackTrace();
		} finally {
		    if (rset != null) {
		        try {
		            rset.close();
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
	}

}
