package com.data.service.board.inquiry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dto.board.inquiry.InquiryPaging;
import com.data.service.Service;

public class InquiryBoardList implements Service{
	
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//InquiryReplyDAO dao = new InquiryReplyDAO();
		//int count = 0;
		
		int pstartno = request.getParameter("in_no") != null? 
				Integer.parseInt(request.getParameter("in_no")) : 0;
		//count =dao.answerCheck(Integer.parseInt(request.getParameter("in_no")));
		//count =dao.answerCheck(pstartno);
		//request.setAttribute("count", count);
		
		request.setAttribute("reply", new InquiryBoardList());
		request.setAttribute("paging", new InquiryPaging(pstartno));
		request.setAttribute("disViewPath", "/view/board/inquiry/list.jsp");
	}
 
}
