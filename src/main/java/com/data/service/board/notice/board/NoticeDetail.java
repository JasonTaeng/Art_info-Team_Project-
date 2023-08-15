package com.data.service.board.notice.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.dao.board.notice.NoticeBoardDAO;
import com.data.dao.board.notice.NoticeCommentDAO;
import com.data.dao.board.notice.NoticeImagesDAO;
import com.data.dto.board.notice.NoticeBoard;
import com.data.dto.board.notice.NoticeComment;
import com.data.dto.board.notice.NoticeImages;
import com.data.service.Service;

public class NoticeDetail implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Read 기능 - 2. 공지사항 페이지 보기
		HttpSession session = request.getSession();
		int no_no = Integer.parseInt( request.getParameter("no_no") );

		NoticeBoardDAO boardDao = new NoticeBoardDAO();
		boardDao.plusHit(no_no);
		NoticeBoard boardDto = boardDao.getNoticeBoard(no_no);
		
		request.setAttribute("boardDto", boardDto);
		
		NoticeCommentDAO commentDao = new NoticeCommentDAO();
		ArrayList<NoticeComment> commentList = commentDao.getList(no_no);
		request.setAttribute("commentList", commentList);
		
		NoticeImagesDAO imagesDao = new NoticeImagesDAO();
		ArrayList<NoticeImages> imagesList = imagesDao.getList(no_no);
		
		
		// download 받기
		String realPath = request.getServletContext().getRealPath("/download/notice");
		
		File path = new File(realPath);
		if(!path.exists()) {
			path.mkdirs();
		}
		
		
		String filePath = "";
		
		Iterator<NoticeImages> it = imagesList.iterator();
		StringBuilder fileName = new StringBuilder();
		
		while(it.hasNext()) {
			NoticeImages noticeImages = it.next();
			if(noticeImages.getNo_img_data()!=null) {
				filePath = noticeImages.getNo_img_name();
				
				String downloadPath = realPath + File.separator + filePath;
				FileOutputStream fos = new FileOutputStream(downloadPath);
				
				fos.write(noticeImages.getNo_img_data());
				fos.close();
			}
			
			fileName.append(noticeImages.getNo_img_name());
		    if(it.hasNext()) {
		        fileName.append(","); // 각 파일 이름 사이에 구분자 추가
		    }
			
		    request.setAttribute("fileName", fileName);
		}
		
		
		if(session.getAttribute("admin") != null) {
			request.setAttribute("disViewPath", "/view/admin/notice/detail.jsp");
		} else {
			request.setAttribute("disViewPath", "/view/board/notice/detail.jsp");
		}
		
		
	}

}
