package com.data.service.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.user.UserDAO;
import com.data.dto.User;
import com.data.service.Service;

public class AdminJoinController implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("userID")==null || request.getParameter("userID")=="") {
			request.setAttribute("disViewPath", "/view/join/adminJoin.jsp");
		} else {
			String alertMessage = null;
			UserDAO userdao = new UserDAO();
			User user = new User();
			
			user.setUserID( request.getParameter("userID") );
			user.setUserPassword( request.getParameter("userPassword") );
			user.setUserName( request.getParameter("userName") );
			user.setUserGender( request.getParameter("userGender") );
			user.setUserEmail( request.getParameter("userEmail") );
			user.setUserPhoneNum( request.getParameter("userPhoneNum") );
			user.setUserAuthority(1);
			
			int result = userdao.join(user);
			if(result==1) { // 관리자 등록 성공
				alertMessage = "일반 관리자를 등록했습니다.";
				request.setAttribute("reViewPath", "/");
			} else { // 관리자 등록 실패
				alertMessage = "일반 관리자 등록에 실패했습니다. 이미 존재하는 아이디인지 확인해주세요.";
			}
			request.setAttribute("alertMessage", alertMessage);
		}
		
	}

}
