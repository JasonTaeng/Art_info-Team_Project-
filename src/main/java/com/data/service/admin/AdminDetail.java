package com.data.service.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.user.UserDAO;
import com.data.dto.User;
import com.data.service.Service;

public class AdminDetail implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Read 기능 - 2. 유저 한 명 페이지 보기
		UserDAO userdao = new UserDAO();
		User user = userdao.getUser(request.getParameter("userID"));
		request.setAttribute("User", user);
		request.setAttribute("disViewPath", "/view/admin/adminDetail.jsp");
	}

}
