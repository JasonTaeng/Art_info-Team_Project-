package com.data.service.board.notice.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.dao.board.notice.NoticeListDAO;
import com.data.dto.board.notice.NoticeList;
import com.data.service.Service;

public class NoticeListController implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Read(list) - 게시물 리스트 보기 기능
		/* 몇 페이지인지에 따라 bbsdao.getList() 메서드로 ArayList<Bbs>와 삭제하지 않은 가장 높은 게시물 번호를 보내줌
		   삭제하지 않은 게시물 수에 따라 가장 높은 페이지 수도 보내줌 */
		HttpSession session = request.getSession();
		
		NoticeListDAO dao = new NoticeListDAO();
		String SQL = null; String sqlCOUNT = null;
		
		// Read 기능 - 1. 공지사항 리스트 보기
	 	/* 1. 처음 페이지 들어올 때 전체 공지사항 조회
		   2. 검색했다면 검색 조건에 맞는 공지사항 조회 */
		int page = 1;
	    if (request.getParameter("page") != null) {
	    	page = Integer.parseInt(request.getParameter("page"));
	    }
	    
		// 검색 조건 있디면 받기
		String field1 = "no_title";
		String field2 = "no_title";
		String keyword = "";
		if(request.getParameter("type")!=null && request.getParameter("type").equals("ID")) {
			field1 = "b.fk_user_userID";
			field2 = "fk_user_userID";
		}
		
		if(request.getParameter("keyword")!=null && request.getParameter("keyword")!="") {
			keyword = request.getParameter("keyword");
		}
		
		if(session.getAttribute("admin") != null) {
			request.setAttribute("disViewPath", "/view/admin/notice/list.jsp?page="+page);
			
			/* dto.getList와 dto.getNext()에 넣어줄 sql 구문.
			   전자는 현재 페이지에 띄울 dto 인스턴스 ArrayList 반환. 후자는 조건에 맞는 공지사항 수 + 1 반환.*/
			SQL = "SELECT no_no, no_title, no_date, no_publish, no_publish_date, no_hit, b.fk_user_userID, count(no_comm_no) AS cntComment "
					+ "FROM notice_board b "
					+ "LEFT JOIN notice_comment c ON b.no_no = c.fk_notice_board_no "
					+ "WHERE " + field1 + " LIKE ? "
					+ "GROUP BY no_no "
					+ "ORDER BY no_date DESC "
					+ "LIMIT " + (page-1)*10 + ",10";
			sqlCOUNT = "SELECT COUNT(no_no) FROM notice_board WHERE " + field2 + " LIKE ?";
		} else {
			request.setAttribute("disViewPath", "/view/board/notice/list.jsp?page="+page);
			
			/* dto.getList와 dto.getNext()에 넣어줄 sql 구문.
			   전자는 현재 페이지에 띄울 dto 인스턴스 ArrayList 반환. 후자는 조건에 맞는 공지사항 수 + 1 반환.*/
			SQL = "SELECT no_no, no_title, no_date, no_publish, no_publish_date, no_hit, b.fk_user_userID, count(no_comm_no) AS cntComment "
					+ "FROM notice_board b "
					+ "LEFT JOIN notice_comment c ON b.no_no = c.fk_notice_board_no "
					+ "WHERE " + field1 + " LIKE ? AND no_publish = '공개'"
					+ "GROUP BY no_no "
					+ "ORDER BY no_date DESC "
					+ "LIMIT " + (page-1)*10 + ",10";
			sqlCOUNT = "SELECT COUNT(no_no) FROM notice_board WHERE " + field2 + " LIKE ? AND no_publish = '공개'";
		}
		
		
		
 		// 검색 유무와 상관없이 공통 수행 부분
    	ArrayList<NoticeList> list = dao.getList(SQL, keyword);
		request.setAttribute("list", list);
		
		int lastNum = dao.getNext(sqlCOUNT, keyword) - 1;
		// 검색 결과 없을시 에러 방지
		if(lastNum==0) {
			lastNum=1;
		}
		request.setAttribute("lastNum", lastNum);
		
		
	}

}
