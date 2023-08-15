package com.data.service.board.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.board.review.ReviewBoardDAO;
import com.data.dto.board.review.ReviewBoard;
import com.data.service.Service;

public class ReviewBoardDelete implements Service{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html; charset=UTF-8");
	    ReviewBoard dto = new ReviewBoard();
	    ReviewBoardDAO dao = new ReviewBoardDAO();
	    
	    if(request.getParameter("re_no") == null) {
			int re_no = Integer.parseInt( request.getParameter("re_no") ); 
			request.setAttribute("re_no", re_no);
			request.setAttribute("disViewPath", "/view/board/review/edit.jsp");		
		} else {
			String reViewPath = null;
			String alertMessage = null;	
			
			dto.setRe_no(Integer.parseInt(request.getParameter("re_no")));
			
			if(dao.delete(dto) > 0) {
				dao.reOrder();
				alertMessage = "삭제완료.";
				reViewPath = "/reviewboard";
				request.setAttribute("reViewPath", reViewPath);
				request.setAttribute("alertMessage", alertMessage);
			}else {
				alertMessage = "글 삭제실패";
			}
		
		
		}
	}
	

}
