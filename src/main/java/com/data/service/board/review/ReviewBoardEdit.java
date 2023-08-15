package com.data.service.board.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import com.data.dao.board.review.ReviewBoardDAO;
import com.data.dto.board.review.ReviewBoard;
import com.data.service.Service;


public class ReviewBoardEdit implements Service {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//ReviewBoardDAO dao = new ReviewBoardDAO();
		//int re_no = Integer.parseInt(request.getParameter("re_no"));
		

		if(request.getParameter("re_content") == null || request.getParameter("re_content").trim().equals("") ||
			request.getParameter("re_title") == null || request.getParameter("re_title").trim().equals("")) {
			int re_no = Integer.parseInt(request.getParameter("re_no")); 
			request.setAttribute("re_no", re_no);
			request.setAttribute("disViewPath", "/view/board/review/edit.jsp"); // 제목,내용이 공백이면 return 으로 되돌린다
			return;		
		} 
			ReviewBoardDAO dao = new ReviewBoardDAO();
			int re_no = Integer.parseInt(request.getParameter("re_no"));
			ReviewBoard dto = new ReviewBoard();
			
			String reViewPath = null;
			String alertMessage = null;	
			
			dto.setRe_no(Integer.parseInt(request.getParameter("re_no")));
			dto.setRe_title(request.getParameter("re_title"));
			dto.setRe_content(request.getParameter("re_content"));
			
			if(dao.update(dto) > 0) {
				alertMessage = "글 수정에 성공했습니다.";
				reViewPath = "/reviewboard";

				int result = dao.update_hit(dto);
				if(result > 0) {
				  dto = dao.get(re_no);
				  request.setAttribute("dto", dto);
				}
				
				request.setAttribute("dto", dto);
				request.setAttribute("reViewPath", reViewPath);
				request.setAttribute("alertMessage", alertMessage);
			} else {
				alertMessage = "글 수정에 실패하였습니다.";
				request.setAttribute("alertMessage", alertMessage);
			}
		
	
	}
}
