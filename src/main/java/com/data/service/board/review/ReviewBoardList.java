package com.data.service.board.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.board.review.ReviewBoardDAO;
import com.data.service.Service;

public class ReviewBoardList implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ReviewBoardDAO dao = new ReviewBoardDAO();

	    int pageNo = request.getParameter("pageNo") != null ? Integer.parseInt(request.getParameter("pageNo")) : 1;
	    int pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize")) : 10;

	    // 1보다 작은 페이지 번호, 1보다 작은 페이지 크기는 허용하지 않음
	    if (pageNo < 1 || pageSize < 1) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid page number or size");
	        return;
	    }

	    int totalPosts = dao.getTotalPosts();
	    int totalPages = (int)Math.ceil((double)totalPosts / pageSize);

	    // 요청된 페이지 번호가 전체 페이지 수를 초과하는 경우 에러 반환
	    if (pageNo > totalPages) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Page number exceeds total number of pages");
	        return;
	    }

	    int bottomStart = (int)Math.floor((double)(pageNo - 1) / 10) * 10 + 1;
	    int endStart = Math.min(bottomStart + 9, totalPages);

	    request.setAttribute("list", dao.getPagedReviews(pageNo, pageSize));
	    request.setAttribute("totalPages", totalPages);
	    request.setAttribute("bottomStart", bottomStart);
	    request.setAttribute("endStart", endStart);
	    request.setAttribute("bottomPageLimit", 10);
	    request.setAttribute("bottomPageAll", totalPages / 10 + 1);
	    request.setAttribute("bottomCurrent", pageNo);
	    request.setAttribute("disViewPath", "/view/board/review/list.jsp");
	}
}