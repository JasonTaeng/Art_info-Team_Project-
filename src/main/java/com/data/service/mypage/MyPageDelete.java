package com.data.service.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.dao.user.UserDAO;
import com.data.dto.User;
import com.data.service.Service;

public class MyPageDelete implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Delete 기능 - 비밀번호 확인 후 계정 탈퇴
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("userID");
		String alertMessage = null;
		
		UserDAO userdao = new UserDAO();
		User user = userdao.getUser(userID);
		// 비밀번호 일치 시
		if(user.getUserPassword().equals(request.getParameter("userPassword"))) {
			int result = userdao.disabledByUser(userID);
			if(result>0) {
				alertMessage = "탈퇴되었습니다.";
				session.invalidate();
				request.setAttribute("reViewPath", "/");
			} else {
				alertMessage = "탈퇴 실패. 관리자에게 문의해주세요.";
				request.setAttribute("reViewPath", "/mypage");
			}
		// 비밀번호 불일치 시
		} else {
			alertMessage = "비밀번호가 틀립니다";
			request.setAttribute("reViewPath", "/mypage");
		}
		request.setAttribute("alertMessage", alertMessage);

	}

}
