package com.data.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.service.Service;
import com.data.service.admin.AdminDetail;
import com.data.service.admin.AdminList;
import com.data.service.admin.AdminSearch;
import com.data.service.admin.AdminUpdate;
import com.data.service.art.info.ArtInfoDelete;
import com.data.service.art.info.ArtInfoDetail;
import com.data.service.art.info.ArtInfoEdit;
import com.data.service.art.info.ArtInfoList;
import com.data.service.art.info.ArtInfoWrite;
import com.data.service.board.free.FreeBoardDelete;
import com.data.service.board.free.FreeBoardDetail;
import com.data.service.board.free.FreeBoardEdit;
import com.data.service.board.free.FreeBoardList;
import com.data.service.board.free.FreeBoardWrite;
import com.data.service.board.inquiry.InquiryBoardDelete;
import com.data.service.board.inquiry.InquiryBoardDetail;
import com.data.service.board.inquiry.InquiryBoardEdit;
import com.data.service.board.inquiry.InquiryBoardEditView;
import com.data.service.board.inquiry.InquiryBoardList;
import com.data.service.board.inquiry.InquiryBoardWrite;
import com.data.service.board.inquiry.reply.InquiryReplyDelete;
import com.data.service.board.inquiry.reply.InquiryReplyEdit;
import com.data.service.board.inquiry.reply.InquiryReplyWrite;
import com.data.service.board.notice.board.NoticeDetail;
import com.data.service.board.notice.board.NoticeEdit;
import com.data.service.board.notice.board.NoticeListController;
import com.data.service.board.notice.board.NoticeUpdateDelete;
import com.data.service.board.notice.board.NoticeWrite;
import com.data.service.board.notice.comment.NoticeCommentWrite;
import com.data.service.board.review.ReviewBoardDelete;
import com.data.service.board.review.ReviewBoardDetail;
import com.data.service.board.review.ReviewBoardEdit;
import com.data.service.board.review.ReviewBoardList;
import com.data.service.board.review.ReviewBoardWrite;
import com.data.service.main.MainPageController;
import com.data.service.mypage.MyPageController;
import com.data.service.mypage.MyPageDelete;
import com.data.service.mypage.MyPageUpdate;
import com.data.service.user.AdminJoinController;
import com.data.service.user.CheckID;
import com.data.service.user.JoinController;
import com.data.service.user.LoginOutController;

@MultipartConfig(
		fileSizeThreshold = 1024*1024,	//1메가
		maxFileSize = 1024*1024*10, //10메가
		maxRequestSize = 1024*1024*10*5 //50메가
)
@WebServlet("/")
public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public FrontController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String path = request.getServletPath();
		
		Service service = null;
		
		// 메인 페이지
		if(path.equals("/")) {
			service = new MainPageController();
		}
		// 로그인, 로그아웃 URI
		else if(path.equals("/login")) {
			service = new LoginOutController();
		}
		// 회원가입 URI
		else if(path.equals("/join")) {
			service = new JoinController();
		} else if(path.equals("/join/checkid")) {
			service = new CheckID();
			service.exec(request, response);
			return;
		}
		
		// 회원 URI
		else if(path.contains("/mypage")) {
			if(path.equals("/mypage")) {
				service = new MyPageController();
			} else if(path.equals("/mypage/update")) {
				service = new MyPageUpdate();
			} else if(path.equals("/mypage/delete")) {
				service = new MyPageDelete();
			}
		}
		
		// 관리자 URI
		else if(path.contains("/admin")) {
			if(path.equals("/admin")) {
				service = new AdminList();
			} else if(path.equals("/admin/search")) {
				service = new AdminSearch();
			} else if(path.equals("/admin/detail")) {
				service = new AdminDetail();
			} else if(path.equals("/admin/update")) {
				service = new AdminUpdate();
			} else if(path.equals("/admin/adminjoin")) {
				service = new AdminJoinController();
			}
		}
		
		// 공연소식 URI
		else if(path.contains("/art_info")) {
			if(path.equals("/art_info")) {
				service = new ArtInfoList();
			} else if(path.equals("/art_info/detail")) {
				service = new ArtInfoDetail();
			} else if(path.equals("/art_info/write")) {
				service = new ArtInfoWrite();
			} else if(path.equals("/art_info/edit")) {
				service = new ArtInfoEdit();
			} else if(path.equals("/art_info/delete")) {
				service = new ArtInfoDelete();
			}
		}
		
		// 게시판 URI
		else if(path.contains("/notice")) {
			if(path.equals("/notice")) {
				service = new NoticeListController();
			} else if(path.equals("/notice/detail")) {
				service = new NoticeDetail();
			} else if(path.equals("/notice/write")) {
				service = new NoticeWrite();
			} else if(path.equals("/notice/edit")) {
				service = new NoticeEdit();
			} else if(path.equals("/notice/update_delete")) {
				service = new NoticeUpdateDelete();
			} else if(path.equals("/notice/comment/write")) {
				service = new NoticeCommentWrite();
			}
		} else if(path.contains("/freeboard")) {
			if(path.equals("/freeboard")) {
				service = new FreeBoardList();
			} else if(path.equals("/freeboard/detail")) {
				service = new FreeBoardDetail();
			} else if(path.equals("/freeboard/write")) {
				service = new FreeBoardWrite();
			} else if(path.equals("/freeboard/edit")) {
				service = new FreeBoardEdit();
			} else if(path.equals("/freeboard/delete")) {
				service = new FreeBoardDelete();
			}
		} else if(path.contains("/reviewboard")) {
			if(path.equals("/reviewboard")) {
				service = new ReviewBoardList();
			} else if(path.equals("/reviewboard/detail")) {
				service = new ReviewBoardDetail();
			} else if(path.equals("/reviewboard/write")) {
				service = new ReviewBoardWrite();
			} else if(path.equals("/reviewboard/edit")) {
				service = new ReviewBoardEdit();
			} else if(path.equals("/reviewboard/delete")) {
				service = new ReviewBoardDelete();
			}
		} else if(path.contains("/inquiryboard")) {
			if (path.equals("/inquiryboard")) {
				service = new InquiryBoardList();
			} else if (path.equals("/inquiryboard/write")) {
				service = new InquiryBoardWrite();
			} else if (path.equals("/inquiryboard/detail")) {
				service = new InquiryBoardDetail();
			} else if (path.equals("/inquiryboard/edit")) {
				service = new InquiryBoardEdit();
			} else if (path.equals("/inquiryboard/editView")) {
				service = new InquiryBoardEditView();
			} else if (path.equals("/inquiryboard/delete")) {
				service = new InquiryBoardDelete();
			}
		} else if(path.contains("/inquiryReply")) {
			 if (path.equals("/inquiryReply/write")) {
				service = new InquiryReplyWrite();
			}  else if (path.equals("/inquiryReply/edit")) {
				service = new InquiryReplyEdit();
			}  else if (path.equals("/inquiryReply/delete")) {
				service = new InquiryReplyDelete();
			}
		}
		
		service.exec(request, response);
		
		
		// URL 인코딩하여 alertMessage 쿠키 값 설정
		String alertMessage = (String)request.getAttribute("alertMessage");
		if(alertMessage!=null) {
	        String encodedValue = URLEncoder.encode(alertMessage, "UTF-8");
			Cookie cookie = new Cookie("alertMessage", encodedValue);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}
		
		// dispatcher, redirect, 전 페이지로 이동시키기
		String disViewPath = (String)request.getAttribute("disViewPath");
		String reViewPath = (String)request.getAttribute("reViewPath");
		if(disViewPath!=null) {
			request.getRequestDispatcher(disViewPath).forward(request, response);
		} else if(reViewPath!=null) {
			reViewPath = request.getContextPath() + reViewPath;
			response.sendRedirect(reViewPath);
		} else {
			out.println("<script>history.back();</script>");
		}
		

	}

}
