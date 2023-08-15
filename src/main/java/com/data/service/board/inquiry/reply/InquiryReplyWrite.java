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

public class InquiryReplyWrite implements Service {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InquiryReplyDAO dao = new InquiryReplyDAO();
		InquiryReply dto = new InquiryReply();
		
		//급하게 추가 답변 체크 InquiryBoard의 in_set 으로 변경 페이징 dto로 출력한 값으로 조건을 잡아야됨 그래서 추가함
		InquiryBoardDAO Bdao = new InquiryBoardDAO();
		//
		
		String alertMessage ="관리자에게 문의바랍니다";
		//System.out.println(request.getParameter("admin"));
		//System.out.println(request.getParameter("userID"));
		dto.setIn_reply_no(Integer.parseInt(request.getParameter("in_reply_no")));
		dto.setFk_user_userID(request.getParameter("userID"));
		dto.setIn_reply_content(request.getParameter("in_reply_content"));
		dto.setIn_reply_check(request.getParameter("in_reply_check"));
		
		
		int in_no = Integer.parseInt(request.getParameter("in_reply_no"));
		
		Bdao.updateAnswerStatus("y", in_no);
		
		int result = dao.insert(dto);
		if(result > 0) {alertMessage  ="답변완료";}
		
		
		
		request.setAttribute("alertMessage", alertMessage);
		int pstartno = in_no != 0? 
				in_no : 0;
				
		request.setAttribute("paging", new InquiryPaging(pstartno));
		request.setAttribute("reViewPath", "/inquiryboard");
		
		
	}
}
