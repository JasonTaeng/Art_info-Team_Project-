package com.data.service.board.notice.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.data.dao.board.notice.NoticeBoardDAO;
import com.data.dao.board.notice.NoticeImagesDAO;
import com.data.dto.board.notice.NoticeBoard;
import com.data.dto.board.notice.NoticeImages;
import com.data.service.Service;

@MultipartConfig(
		fileSizeThreshold = 1024*1024,	//1메가
		maxFileSize = 1024*1024*10, //10메가
		maxRequestSize = 1024*1024*10*5 //50메가
)
public class NoticeWrite implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("no_title") == null) {
			request.setAttribute("disViewPath", "/view/admin/notice/write.jsp");
		} else {
			HttpSession session = request.getSession();
			
			NoticeBoard dto = new NoticeBoard();
			NoticeBoardDAO dao = new NoticeBoardDAO();
			
			String alertMessage = null;
			
			dto.setFk_user_userID( (String)session.getAttribute("userID") );
			
			// (제목, 내용 등) 받기
			Enumeration<String> names = request.getParameterNames();
			while(names.hasMoreElements()) {
				String name = names.nextElement();
				if(name.equals("no_title")) {
					dto.setNo_title( request.getParameter(name) );
				} else if(name.equals("no_content")) {
					dto.setNo_content( request.getParameter(name) );
				} else if(name.equals("publish")) {
					dto.setNo_publish("공개");
				}
			}
			
			int result = dao.write(dto);
			if(result>0) {
				alertMessage = "공지사항 등록 성공!";
				request.setAttribute("reViewPath", "/notice");
			} else {
				alertMessage = "공지사항 등록 실패. 관리자에게 문의해주세요.";
				request.setAttribute("reViewPath", "/notice/write");
			}
			
			request.setAttribute("alertMessage", alertMessage);
			
			
			// 파일 입출력 부분
			Collection<Part> parts = request.getParts(); //binary 넘겨 받음
			
			for(Part p : parts) {
				if(!p.getName().equals("file")) {continue;}
				if(p.getSize() == 0) {continue;}

				Part filePart = p;
				String fileName = filePart.getSubmittedFileName();
				InputStream fis = filePart.getInputStream();
				
				String realPath = request.getServletContext().getRealPath("/upload/notice");
				//System.out.println(realPath);
				
				File path = new File(realPath);
				if(!path.exists()) {
					path.mkdirs();
				}
				
				String uploadPath = realPath + File.separator + fileName;
				
				FileOutputStream fos = new FileOutputStream(uploadPath);
				
//				byte[] buffer = new byte[1024];
//				int size = 0;
//				
//				while((size = fis.read(buffer)) != -1) {
//					fos.write(buffer, 0, size);
//				}
				
				//db에 넣기
				NoticeImages imagesDto = new NoticeImages();
				NoticeImagesDAO imagesDao = new NoticeImagesDAO();
				
				imagesDto.setNo_img_name(fileName);
				
				byte[] imgData = fis.readAllBytes();
				fos.write(imgData);
				
				imagesDto.setNo_img_data(imgData);
				
				String SQL = "SELECT MAX(no_no) FROM notice_board";
				int max_no_no = dao.getNext(SQL) - 1;
				imagesDto.setFk_notice_board_no(max_no_no);
				
				imagesDao.upload(imagesDto); //MEDIUMBLOB 열은 이론적으로 최대 약 17메가 바이트까지 저장할 수 있음
				
				fos.close();
				fis.close();
			}
						
		}
		
	}

}
