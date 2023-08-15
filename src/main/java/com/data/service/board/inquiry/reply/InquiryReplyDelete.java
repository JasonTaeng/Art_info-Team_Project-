package com.data.service.board.inquiry.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.board.inquiry.InquiryBoardDAO;
import com.data.dao.board.inquiry.InquiryReplyDAO;
import com.data.dto.board.inquiry.InquiryPaging;
import com.data.dto.board.inquiry.InquiryReply;
import com.data.service.Service;

public class InquiryReplyDelete implements Service {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
		InquiryReply dto = new InquiryReply();
		InquiryReplyDAO dao = new InquiryReplyDAO();
		InquiryBoardDAO Bdao = new InquiryBoardDAO();
		String alertMessage = "삭제실패.";
		
		dto.setIn_reply_no(Integer.parseInt(request.getParameter("in_reply_no")));
		
		int in_no = Integer.parseInt(request.getParameter("in_reply_no"));
		
		Bdao.updateAnswerStatus(null, in_no);
		
		int result = dao.delete(dto);
		if(result >0) { alertMessage ="삭제완료"; }
		request.setAttribute("alertMessage", alertMessage);
		
		int pstartno = in_no != 0? 
				in_no : 0;
				
		request.setAttribute("paging", new InquiryPaging(pstartno));
		request.setAttribute("reViewPath", "/inquiryboard");
		
	}

}
