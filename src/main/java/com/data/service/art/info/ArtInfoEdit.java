package com.data.service.art.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.data.dao.art.ArtInfoDAO;
import com.data.dto.art.ArtInfo;
import com.data.service.Service;

@MultipartConfig(
	fileSizeThreshold = 1024*1024,	//1메가
	maxFileSize = 1024*1024*10, //10메가
	maxRequestSize = 1024*1024*10*5 //50메가
)
public class ArtInfoEdit implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("art_title") == null || request.getParameter("art_title")=="") {
			int art_no = Integer.parseInt(request.getParameter("art_no")); 
			request.setAttribute("art_no", art_no);
			
			request.setAttribute("disViewPath", "/view/art/info/artInfoEdit.jsp");
		} else {
			HttpSession session = request.getSession();
			String alertMessage = null;	

			int art_no = Integer.parseInt(request.getParameter("art_no"));
			ArtInfoDAO dao = new ArtInfoDAO();
			ArtInfo dto = new ArtInfo();
			
			dto.setArt_no(art_no);
			dto.setArt_title(request.getParameter("art_title"));
			dto.setStart_date(request.getParameter("start_date"));
			dto.setEnd_date(request.getParameter("end_date"));
			dto.setFk_user_userID((String)session.getAttribute("userID"));
			
			if(dao.update(dto) > 0) {
				alertMessage = "글 수정에 성공했습니다.";
				request.setAttribute("dto", dto);
				request.setAttribute("reViewPath", "/art_info");
			} else {
				alertMessage = "글 수정에 실패하였습니다.";
			}
			
			request.setAttribute("alertMessage", alertMessage);

		}
	
	}

}
