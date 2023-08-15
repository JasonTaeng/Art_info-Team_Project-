package com.data.service.board.free;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.board.free.FreeBoardDAO;
import com.data.dto.board.free.FreeBoard;
import com.data.service.Service;

public class FreeBoardDetail implements Service {
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("free_no")!=null) {
			request.setAttribute("disViewPath", "/view/board/free/detail.jsp");
		
			
		PrintWriter out = response.getWriter();		
		FreeBoardDAO dao = new FreeBoardDAO();
		FreeBoard dto = new FreeBoard();
		
		dto.setFree_no(Integer.parseInt(request.getParameter("free_no")));
		dto = dao.detail(dto);
		String fk_user_userID = dao.detail(dto).getFk_user_userID();
		request.setAttribute("fk_user_userID", fk_user_userID);
		
		if(dao.update_hit(dto) <= 0) {out.println("<script>history.back();</script>");}
		else {request.setAttribute("dto", dto);}
			
			String reViewPath = "/freeboard";
			request.setAttribute("reViewPath", reViewPath);
		}
	}
}