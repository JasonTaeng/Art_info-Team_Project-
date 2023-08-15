package com.data.service.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.dao.user.UserDAO;
import com.data.dto.User;
import com.data.service.Service;

public class JoinController implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String disViewPath = null;
		String reViewPath = null;
		String alertMessage = null;
		
		// 회원가입 입력 전
		if(request.getParameter("userID")==null || request.getParameter("userID")=="") {
			disViewPath = "view/join/join.jsp"; 
		}
		// 회원가입 입력 후
		else {
			UserDAO userdao = new UserDAO();
			User user = new User();
			
			user.setUserID( request.getParameter("userID") );
			user.setUserPassword( request.getParameter("userPassword") );
			user.setUserName( request.getParameter("userName") );
			user.setUserGender( request.getParameter("userGender") );
			user.setUserEmail( request.getParameter("userEmail") );
			user.setUserPhoneNum( request.getParameter("userPhoneNum") );
			user.setUserAuthority(0);
			
			int result = userdao.join(user);
			if(result==1) { // 회원가입 성공
				session.setAttribute("userID",user.getUserID());
				reViewPath = "/";
				alertMessage = "회원가입이 성공했습니다.";
			} else { // 회원가입 실패
				alertMessage = "이미 존재하는 아이디입니다.";
			}
			session.setAttribute("alertMessage", alertMessage);
			session.setMaxInactiveInterval(60 * 30);
		}
		
		// viewPath 설정
		if(disViewPath!=null) {
			request.setAttribute("disViewPath", disViewPath);
		} else {
			request.setAttribute("reViewPath", reViewPath);
		}
		// alertMessage 설정
		if(alertMessage!=null) {
			request.setAttribute("alertMessage", alertMessage);
		}
		
	}

}
