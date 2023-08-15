package com.data.service.board.review;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.board.review.ReviewBoardDAO;
import com.data.dao.board.review.ReviewImgDAO;
import com.data.dto.board.review.ReviewBoard;
import com.data.dto.board.review.ReviewImg;
import com.data.service.Service;

public class ReviewBoardDetail implements Service {
    
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int re_no = 0;
		if(request.getParameter("re_no")!=null) {
			re_no = Integer.parseInt( request.getParameter("re_no") );
		}
		request.setAttribute("disViewPath", "/view/board/review/detail.jsp");
				
		PrintWriter out = response.getWriter();	
		ReviewBoardDAO dao = new ReviewBoardDAO();
		ReviewBoard dto = dao.get(re_no);
		
		//파일 이름 넣어주기
		ReviewImgDAO imgDao = new ReviewImgDAO();
		ArrayList<ReviewImg> imgList = imgDao.getList(re_no);
		
		String fileName = "";
		if(!imgList.isEmpty()) {
			fileName = imgList.get(0).getRe_img_name();
		}
		request.setAttribute("fileName", fileName);
		
		request.setAttribute("fk_user_userID", dto.getFk_user_userID());
		
		dto.setRe_no(Integer.parseInt(request.getParameter("re_no")));
		if(dao.update_hit(dto) <= 0) {out.println("<script>history.back();</script>");}
		else {request.setAttribute("dto", dao.detail(dto));}
							
		String reViewPath = "/reviewboard";
		request.setAttribute("reViewPath", reViewPath);
	}
}


 