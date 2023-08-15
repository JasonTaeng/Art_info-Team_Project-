package com.data.service.board.review;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.data.dao.board.review.ReviewBoardDAO;
import com.data.dao.board.review.ReviewImgDAO;
import com.data.dto.board.review.ReviewBoard;
import com.data.dto.board.review.ReviewImg;
import com.data.service.Service;

@MultipartConfig(
		fileSizeThreshold = 1024*1024,	//1메가
		maxFileSize = 1024*1024*10, //10메가
		maxRequestSize = 1024*1024*10*5 //50메가
)
public class ReviewBoardWrite implements Service {
	 

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ReviewBoardDAO dao = new ReviewBoardDAO();
	    ReviewBoard dto = new ReviewBoard();
	    String result = null;
	    
	    dto.setFk_user_userID(request.getParameter("userID"));
	    dto.setRe_title(request.getParameter("re_title"));
	    dto.setRe_content(request.getParameter("re_content"));
	    
	    if (dao.insert(dto) > 0) {
	        result = "글쓰기에 성공했습니다.";
	    } else {
	        result = "글쓰기에 실패하였습니다.";
	        dao.reOrder();
	    }
	    
	    // 파일 입출력 부분
		Collection<Part> parts = request.getParts(); //binary 넘겨 받음
		
		for(Part p : parts) {
			if(!p.getName().equals("file")) {continue;}
			if(p.getSize() == 0) {continue;}

			Part filePart = p;
			String fileName = filePart.getSubmittedFileName();
			InputStream fis = filePart.getInputStream();
			
			String realPath = request.getServletContext().getRealPath("/upload/reviewboard");
			
			File path = new File(realPath);
			if(!path.exists()) {
				path.mkdirs();
			}
			
			String uploadPath = realPath + File.separator + fileName;
			
			FileOutputStream fos = new FileOutputStream(uploadPath);
			
			// 버퍼를 사용해서 db 업로드 방법 없나?
//			byte[] buffer = new byte[1024];
//			int size = 0;
//			
//			while((size = fis.read(buffer)) != -1) {
//				fos.write(buffer, 0, size);
//			}
			
			//db에 넣기
			ReviewImg imgDto = new ReviewImg();
			ReviewImgDAO imgDao = new ReviewImgDAO();
			
			imgDto.setRe_img_name(fileName);
			
			byte[] imgData = fis.readAllBytes();
			fos.write(imgData);
			
			imgDto.setRe_img_data(imgData);
			
			String SQL = "SELECT MAX(re_no) FROM review_board";
			int max_re_no = dao.getNext(SQL) - 1;
			imgDto.setFk_review_board_re_no(max_re_no);
			
			imgDao.upload(imgDto); //MEDIUMBLOB 열은 이론적으로 최대 약 17메가 바이트까지 저장할 수 있음
			
			fos.close();
			fis.close();
		}

	    request.setAttribute("alertMessage", result);
	    request.setAttribute("reViewPath", "/reviewboard");
	}
}
