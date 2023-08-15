package com.data.service.board.inquiry.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.board.inquiry.InquiryReplyDAO;
import com.data.dto.board.inquiry.InquiryPaging;
import com.data.dto.board.inquiry.InquiryReply;
import com.data.service.Service;

public class InquiryReplyEdit implements Service {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InquiryReply dto = new InquiryReply();
		InquiryReplyDAO dao = new InquiryReplyDAO();
		String alertMessage = "관리자에게 문의 바랍니다.";
		
		dto.setIn_reply_content(request.getParameter("in_reply_content"));
		dto.setIn_reply_no(Integer.parseInt(request.getParameter("in_reply_no")));
		
		int pstartno = request.getParameter("in_no") != null? 
				Integer.parseInt(request.getParameter("in_no")) : 0;
		
		int result = dao.update(dto);
		if(result > 0) { alertMessage  ="수정완료";}
		request.setAttribute("alertMessage", alertMessage);
				
		request.setAttribute("paging", new InquiryPaging(pstartno));
		request.setAttribute("reViewPath", "/inquiryboard");
	}
}
