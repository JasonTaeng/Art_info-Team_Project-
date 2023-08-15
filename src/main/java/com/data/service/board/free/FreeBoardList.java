package com.data.service.board.free;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dto.board.free.FreeBoardPaging;
import com.data.service.Service;

public class FreeBoardList implements Service {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String field_ = request.getParameter("search"); 
		 String query_ = request.getParameter("searchvalue");
		 
		 String field = "free_title";
		 if(field_ != null) field = field_;
		 
		 String query = ""; 
		 if(query_ != null) query = query_;
		 
		 
		 
		int pstartno = request.getParameter("free_no") != null ? Integer.parseInt(request.getParameter("free_no")) : 0;
		request.setAttribute("paging", new FreeBoardPaging(field, query, pstartno));
		
		request.setAttribute("disViewPath", "/view/board/free/list.jsp");
	}
}