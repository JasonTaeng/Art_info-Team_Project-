package com.data.service.board.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.board.free.FreeBoardDAO;
import com.data.dto.board.free.FreeBoard;
import com.data.service.Service;

public class FreeBoardWrite implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		if(request.getParameter("free_title") == null || ("free_title") == "") {
			request.setAttribute("disViewPath", "/view/board/free/write.jsp");			
		} else {
			FreeBoardDAO dao = new FreeBoardDAO();
			FreeBoard dto = new FreeBoard();
			String alertMessage = null;
			String reViewPath = null;
			dto.setFk_user_userID(request.getParameter("fk_user_userID"));
			dto.setFree_password(request.getParameter("free_password"));
			dto.setFree_title(request.getParameter("free_title"));
			dto.setFree_content(request.getParameter("free_content"));
			
			if(dao.insert(dto) > 0) {
				alertMessage = "글쓰기에 성공했습니다.";
				request.setAttribute("alertMessage", alertMessage);				
				reViewPath = "/freeboard";
				request.setAttribute("reViewPath", reViewPath);
				
				}
			else {
				alertMessage = "글 작성에 실패하였습니다.";
				request.setAttribute("alertMessage", alertMessage);
				}			
		}
	}
}