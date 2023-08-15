package com.data.service.board.notice.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.board.notice.NoticeBoardDAO;
import com.data.service.Service;

public class NoticeUpdateDelete implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Update 기능 - 공지사항 공개 or 미공개 변경
		NoticeBoardDAO dao = new NoticeBoardDAO();
		
		String checkedNo_no = request.getParameter("no_no");
		String[] no_noArray = null;
		
    	String SQL = null;
		String alertMessage = null;
		
		if(request.getParameter("noPublish")!=null) { // 비공개로 변경시
			SQL = "UPDATE notice_board SET no_publish = '비공개' WHERE no_no = ?";
		} else if(request.getParameter("publish")!=null) { // 공개로 변경시
			SQL = "UPDATE notice_board SET no_publish = '공개', no_publish_date = now() WHERE no_no = ?";
		} else if(request.getParameter("delete")!=null) { // 삭제시
			SQL = "DELETE FROM notice_board WHERE no_no = ?";
		}
		
		// NoticeBoard dto 불러오기
		if(checkedNo_no.contains(",")) { // 여러 개 선택한 경우
			no_noArray = checkedNo_no.split(",");
		} else { // 한 개 선택한 경우
			no_noArray = new String[1];
			no_noArray[0] = checkedNo_no;
		}
		
		int result = 0;
		for(int i=0; i<no_noArray.length; i++) {
			result = dao.updateDelete( SQL, Integer.parseInt(no_noArray[i]) );
		}
		
		if(result>0) {
			
			if(request.getParameter("delete")!=null) {
				alertMessage = "삭제 성공했습니다!";
			} else {
				alertMessage = "변경 성공했습니다!";
			}
			request.setAttribute("reViewPath", "/notice");

		} else {
			
			if(request.getParameter("delete")!=null) {
				alertMessage = "삭제 실패했습니다!";
			} else {
				alertMessage = "변경 실패했습니다!";
			}
			
		}
		
		request.setAttribute("alertMessage", alertMessage);
	}

}
