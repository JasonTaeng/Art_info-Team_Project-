package com.data.service.board.inquiry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.board.inquiry.InquiryBoardDAO;
import com.data.dto.board.inquiry.InquiryBoard;
import com.data.dto.board.inquiry.InquiryPaging;
import com.data.service.Service;

public class InquiryBoardEdit implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InquiryBoard dto = new InquiryBoard();
		InquiryBoardDAO dao = new InquiryBoardDAO();
		String alertMessage = "관리자에게 문의 바랍니다.";
		
		dto.setIn_title(request.getParameter("in_title"));
		dto.setIn_set(request.getParameter("in_set"));
		dto.setIn_password(request.getParameter("in_password"));
		dto.setIn_content(request.getParameter("in_content"));
		dto.setIn_no(Integer.parseInt(request.getParameter("in_no")));
		
		int pstartno = request.getParameter("in_no") != null? 
				Integer.parseInt(request.getParameter("in_no")) : 0;
		
		int result = dao.update(dto);
		if(result > 0) { alertMessage  ="수정완료";}
		request.setAttribute("alertMessage", alertMessage);
				
		request.setAttribute("paging", new InquiryPaging(pstartno));
		request.setAttribute("reViewPath", "/inquiryboard");
	}

}
