package com.data.service.board.notice.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.dao.board.notice.NoticeCommentDAO;
import com.data.dto.board.notice.NoticeComment;
import com.data.service.Service;

public class NoticeCommentWrite implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		NoticeComment dto = new NoticeComment();
		NoticeCommentDAO dao = new NoticeCommentDAO();
		
		dto.setNo_comm_content( request.getParameter("no_comm_content") );
		dto.setFk_user_userID( (String)session.getAttribute("userID") );
		int no_no = Integer.parseInt(request.getParameter("no_no"));
		dto.setFk_notice_board_no(no_no);

		dao.write(dto);
		
		request.setAttribute("reViewPath", "/notice/detail?no_no="+no_no);
			
	}

}
