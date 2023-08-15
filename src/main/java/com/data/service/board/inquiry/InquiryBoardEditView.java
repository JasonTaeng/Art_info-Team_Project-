package com.data.service.board.inquiry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.board.inquiry.InquiryBoardDAO;
import com.data.dto.board.inquiry.InquiryBoard;
import com.data.dto.board.inquiry.InquiryPaging;
import com.data.service.Service;

public class InquiryBoardEditView implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InquiryBoard dto = new InquiryBoard();
		InquiryBoardDAO dao = new InquiryBoardDAO();
		String alertMessage = "비밀번호가 틀립니다.";
		
		dto.setIn_no(Integer.parseInt(request.getParameter("in_no")));
		dto.setIn_password(request.getParameter("in_password"));
			
		int pstartno = request.getParameter("in_no") != null? 
				Integer.parseInt(request.getParameter("in_no")) : 0;
		
		boolean isPassCheck = dao.checkPassword(dto);
		
		if(isPassCheck) {
			
			request.setAttribute("dto", dao.detail(dto));
			request.setAttribute("disViewPath", "/view/board/inquiry/edit.jsp");
		}
		else {
			request.setAttribute("alertMessage", alertMessage);
			request.setAttribute("paging", new InquiryPaging(pstartno));
			request.setAttribute("reViewPath", "/inquiryboard");
		}
		
		
	}

}
