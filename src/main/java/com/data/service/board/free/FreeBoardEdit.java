package com.data.service.board.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.board.free.FreeBoardDAO;
import com.data.dto.board.free.FreeBoard;
import com.data.service.Service;

public class FreeBoardEdit implements Service {
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if(request.getParameter("free_content") == null) {			
			request.setAttribute("disViewPath", "/view/board/free/edit.jsp");		
		} else {	
			FreeBoardDAO dao = new FreeBoardDAO();
			FreeBoard dto = new FreeBoard();
			String alertMessage = null;
			String reViewPath = null;
			dto.setFree_no(Integer.parseInt(request.getParameter("free_no")));
			dto.setFree_password(request.getParameter("free_password"));
			dto.setFree_title(request.getParameter("free_title"));
			dto.setFree_content(request.getParameter("free_content"));
		
			if(dao.update(dto) > 0) {
			alertMessage = "글 수정에 성공했습니다.";			
			request.setAttribute("alertMessage", alertMessage);
			reViewPath = "/freeboard";
			request.setAttribute("reViewPath", reViewPath);
			}
			else {
				alertMessage = "글 수정에 실패하였습니다. 비밀번호를 다시 확인해주세요.";
				request.setAttribute("alertMessage", alertMessage);
			}		
		}
	}	
}