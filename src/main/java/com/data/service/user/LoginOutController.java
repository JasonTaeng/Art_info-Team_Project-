package com.data.service.user;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.dao.user.UserDAO;
import com.data.dto.User;
import com.data.service.Service;

public class LoginOutController implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String disViewPath = null;
		String reViewPath = null;
		String alertMessage = null;
		
		// 1. 로그인 시
		if(session.getAttribute("userID")==null) {
			String userID = request.getParameter("userID");
			String userPassword = request.getParameter("userPassword");
			
			// 아이디, 비밀번호 입력 전
			if(userID==null || userID=="") {
				disViewPath = "/view/login/login.jsp";
			}
			// 아이디, 비밀번호 입력 후
			else {
				// 로그인 유저의 전체 정보를 User 인스턴스로 받아오기
				UserDAO userdao = new UserDAO();
				
				int result = userdao.login(userID, userPassword);
				// 로그인 성공시
				if(result==1) {
					reViewPath = "/";
					User user = userdao.getUser(userID);
					
					// 로그인 시 아이피 업데이트
					userdao.updateLastIP(InetAddress.getLocalHost().getHostAddress());
					
					// 탈퇴 or 제명 회원 로그인 거부
					int userEnabled = user.getUserEnabled();
					if(userEnabled==0) {
						alertMessage = "탈퇴 회원은 관리자에게 문의해주세요!";
					} else if(userEnabled==-1) {
						alertMessage = "제명 회원은 활동 불가합니다!";
					}
					
					// 세션 값(아이디, 관리자인지), 웰컴 메세지 넣어주기
					int userAuthority = user.getUserAuthority();
					if(userAuthority>0) {
						session.setAttribute("admin", userAuthority);
					}
					session.setAttribute("userID", userID);
					session.setMaxInactiveInterval(60 * 30); //30분
					
					if(userAuthority==2) {
						alertMessage = "반갑습니다 메인 관리자 엄대용님!";
					} else if(userAuthority==1) {
						alertMessage = "반갑습니다 일반 관리자님!";
					} else if(userAuthority==0) {
						alertMessage = "반갑습니다 회원님! 자주 오세요";
					}
					
					// 쿠키 추가(아이디 저장하기 체크한 경우)
					String value = request.getParameter("remember_check");
					if(value!=null && value!="" && value.equals("on")) {
						Cookie c = new Cookie("remember", userID);
						c.setMaxAge(1*60*60*24*7); // 일주일 유지
						response.addCookie(c);
					} else { // 아이디 저장하기 체크하지 않은 경우(쿠키 제거)
						String cookie = request.getHeader("Cookie");
						if(cookie!=null){
							Cookie[] cookies = request.getCookies();
							for(Cookie c : cookies){
								if(c.getName().equals("remember")) {
									c.setMaxAge(0);
									response.addCookie(c);
									break;
								}
							}
						}
					}
					
				// 로그인 실패시
				} else if (result == 0) {
					alertMessage = "비밀번호가 틀립니다.";
				} else if (result == -1) {
					alertMessage = "존재하지 않는 아이디입니다.";
				} else if (result == -2) {
					alertMessage = "데이터베이스 오류가 발생했습니다.";
				}
				
			}
			
		}
		
		// 2. 로그아웃 시
		else {
			session.invalidate();
			reViewPath = "/";
			alertMessage = "로그아웃 되었습니다.";
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
