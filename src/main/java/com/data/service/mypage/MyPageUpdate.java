package com.data.service.mypage;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.dao.user.UserDAO;
import com.data.dto.User;
import com.data.service.Service;

public class MyPageUpdate implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Update 기능 - 비밀번호, 이메일, 폰 번호 변경
		HttpSession session = request.getSession();
		String userID = (String)session.getAttribute("userID");
		
		UserDAO userdao = new UserDAO();
		User user = userdao.getUser(userID);
		
		// 수정 버튼 클릭시 수정페이지 이동
		if(request.getParameter("userEmail")==null || request.getParameter("userEmail")=="") {
			request.setAttribute("disViewPath", "/view/myPage/myPageUpdate.jsp");
			request.setAttribute("User", user);
		}
		// 수정 페이지에서 수정 후 제출시
		else {
			Enumeration<String> names = request.getParameterNames();
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				if(name.equals("userPassword")) {
					user.setUserPassword( request.getParameter(name) );
				} else if(name.equals("userEmail")) {
					user.setUserEmail( request.getParameter(name) );
				} else if(name.equals("userPhoneNum")) {
					user.setUserPhoneNum( request.getParameter(name) );
				}
			}

			String alertMessage = null;
			int result = userdao.updateMyProfile(user);
			if(result>0) {
				alertMessage = "내 정보 변경에 성공했습니다!";
				request.setAttribute("reViewPath", "/mypage");
			} else if(result==-1) {
				alertMessage = "변경 실패. 관리자에게 문의해주세요.";
			}
			request.setAttribute("alertMessage", alertMessage);
		}
	}

}
