package com.data.service.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.user.UserDAO;
import com.data.dto.User;
import com.data.service.Service;

public class AdminUpdate implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Update 기능 - 유저 상태 or 등급 변경
		UserDAO userdao = new UserDAO();
		
		String checkedUserID = request.getParameter("userID");
		String[] userIDarray = null;
		
		String alertMessage = null;
		
		User[] userArray = null; // 한 명 또는 여러명 가능
		String field = null;
		int value = 0;

		if(request.getParameter("updateEnabled")!=null) { // 상태 변경 or 7,30일 이용정지시
			field = "userEnabled";
			value = Integer.parseInt( request.getParameter("updateEnabled") );
			if(value==-7 || value==-30) { // 7,30일 이용정지 시
				field = "userSuspension";
			}
		} else if(request.getParameter("updateAuthority")!=null) { // 등급 변경시
			field = "userAuthority";
			value = Integer.parseInt( request.getParameter("updateAuthority") );
		}
		
		if(checkedUserID.contains(",")) { // 유저 여러 명 선택한 경우
			userIDarray = checkedUserID.split(",");
			userArray = new User[userIDarray.length];
			for(int i=0; i<userArray.length; i++) {
				userArray[i] = userdao.getUser(userIDarray[i]);
			}
		} else { // 유저 한 명 선택한 경우
			userArray = new User[1];
			userArray[0] = userdao.getUser(checkedUserID);
		}
		
		int result = 0;
		for(int i=0; i<userArray.length; i++) {
			result = userdao.updateUser(userArray[i], field, value);
		}
		
		if(result>0) {
			alertMessage = "변경에 성공했습니다!";
			request.setAttribute("reViewPath", "/admin");
		} else {
			alertMessage = "변경에 실패했습니다.";
		}
		request.setAttribute("alertMessage", alertMessage);
		
	}

}
