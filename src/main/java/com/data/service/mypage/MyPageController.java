package com.data.service.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.dao.user.UserDAO;
import com.data.dto.User;
import com.data.service.Service;

public class MyPageController implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Read 기능 - 1. 내 정보 보기
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("userID");
		
		UserDAO userdao = new UserDAO();
		User user = userdao.getUser(userID);
		
		request.setAttribute("User", user);
		request.setAttribute("disViewPath", "/view/myPage/myPage.jsp");
		
	}

}
