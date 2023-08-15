package com.data.service.board.notice.board;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.board.notice.NoticeBoardDAO;
import com.data.dto.board.notice.NoticeBoard;
import com.data.service.Service;

public class NoticeEdit implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeBoardDAO dao = new NoticeBoardDAO();
		int no_no = Integer.parseInt( request.getParameter("no_no") );
		NoticeBoard dto = dao.getNoticeBoard(no_no);
		
		String alertMessage = null;
		
		request.setAttribute("dto", dto);

		if(request.getParameter("no_title") == null) {
			request.setAttribute("disViewPath", "/view/admin/notice/edit.jsp");
		} else {
			Enumeration<String> names = request.getParameterNames();
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				if(name.equals("no_title")) {
					dto.setNo_title( request.getParameter(name) );
				} else if(name.equals("no_content")) {
					dto.setNo_content( request.getParameter(name) );
				}
			}
			
			int result = dao.edit(dto);
			if(result>0) {
				alertMessage = "수정 성공했습니다!";
				request.setAttribute("reViewPath", "/notice");
			} else {
				alertMessage = "수정 실패했습니다. 관리자에게 문의해주세요.";
			}
			
			request.setAttribute("alertMessage", alertMessage);
			
		}
		
	}

}
