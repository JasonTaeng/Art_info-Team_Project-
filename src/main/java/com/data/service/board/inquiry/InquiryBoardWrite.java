package com.data.service.board.inquiry;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.board.inquiry.InquiryBoardDAO;
import com.data.dto.board.inquiry.InquiryBoard;
import com.data.dto.board.inquiry.InquiryPaging;
import com.data.service.Service;

public class InquiryBoardWrite implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InquiryBoardDAO dao = new InquiryBoardDAO();
		InquiryBoard	dto	= new InquiryBoard();
		String alertMessage ="관리자에게 문의바랍니다";
		System.out.println(request.getParameter("admin"));
		System.out.println(request.getParameter("userID"));
		
		dto.setFk_user_userID(request.getParameter("userID"));
		dto.setIn_title(request.getParameter("in_title"));
		dto.setIn_password(request.getParameter("in_password"));
		dto.setIn_set(request.getParameter("in_set"));
		dto.setIn_content(request.getParameter("in_content"));
		dto.setIn_ip(InetAddress.getLocalHost().getHostAddress());
		
		int result = dao.insert(dto);
		if(result > 0) { alertMessage  ="문의완료";}
		request.setAttribute("alertMessage", alertMessage);
		int pstartno = request.getParameter("in_no") != null? 
				Integer.parseInt(request.getParameter("in_no")) : 0;
				
		request.setAttribute("paging", new InquiryPaging(pstartno));
		request.setAttribute("reViewPath", "/inquiryboard");
		
	}

}
