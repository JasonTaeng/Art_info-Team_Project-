package com.data.service.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.user.UserDAO;
import com.data.dto.User;
import com.data.service.Service;

public class AdminList implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    UserDAO userdao = new UserDAO();
		String SQL = null; String sqlCOUNT = null;
		
		// Read 기능 - 1. 유저 리스트 보기
	 	/* 1. 처음 페이지 들어올 때 전체 회원 조회(10이 전체)
		   2. 검색했다면 검색 조건에 맞는 회원 조회 */
		int page = 1;
	    if (request.getParameter("page") != null) {
	    	page = Integer.parseInt(request.getParameter("page"));
	    }
	    
		// 검색 조건 있디면 받기. 선택 안했거나 전체 선택시 값을 10으로 해서 전체 검색.
		int userEnabled = 0; int userAuthority = 0;
		if(request.getParameter("E")==null) {
			userEnabled = 10;
		} else {
			userEnabled = Integer.parseInt( request.getParameter("E") );
		}
		if(request.getParameter("A")==null) {
			userAuthority = 10;
		} else {
			userAuthority = Integer.parseInt( request.getParameter("A") );
		}
		
		/* 아래 userdao.getList와 userdao.getNext()에 넣어줄 sql 구문.
		   전자는 현재 페이지에 띄울 user 인스턴스 ArrayList 반환. 후자는 조건에 맞는 사람 수 + 1 반환.*/
		if(userEnabled==10 && userAuthority==10) {
			SQL = "SELECT * FROM user ORDER BY userDate DESC LIMIT " + (page-1)*10 + ",10";
			sqlCOUNT = "SELECT COUNT(userID) FROM user";
		} else if(userEnabled==10) { // userEnabled 전체 조회시
			SQL = "SELECT * FROM user WHERE userAuthority=" + userAuthority
    				 + " ORDER BY userDate DESC LIMIT " + (page-1)*10 + ",10";
			sqlCOUNT = "SELECT COUNT(userID) FROM user WHERE userAuthority=" + userAuthority;
		} else if(userAuthority==10) { // userAuthority 전체 조회시
			SQL = "SELECT * FROM user WHERE userEnabled=" + userEnabled
    				 + " ORDER BY userDate DESC LIMIT " + (page-1)*10 + ",10";
			sqlCOUNT = "SELECT COUNT(userID) FROM user WHERE userEnabled=" + userEnabled;
		} else { // 두 조건을 모두 설정했을 때
    		SQL = "SELECT * FROM user WHERE userEnabled=" + userEnabled + " and userAuthority=" + userAuthority
    				 + " ORDER BY userDate DESC LIMIT " + (page-1)*10 + ",10";
			sqlCOUNT = "SELECT COUNT(userID) FROM user WHERE userEnabled=" + userEnabled + " and userAuthority=" + userAuthority;
		}

 		// 검색 유무와 상관없이 공통 수행 부분
    	ArrayList<User> list = userdao.getList(SQL);
		request.setAttribute("list", list);
		
		// 전체 리스트를 보여줄 때, URL에 쿼리스트링을 넣지 않기 위해 attribute 사용
		request.setAttribute("E", userEnabled);
		request.setAttribute("A", userAuthority);
		
		int lastNum = userdao.getNext(sqlCOUNT) - 1;
		request.setAttribute("lastNum", lastNum);
		request.setAttribute("disViewPath", "/view/admin/adminPage.jsp?page="+page);
		
	}

}
