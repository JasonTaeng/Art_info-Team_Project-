package com.data.service.art.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.art.ArtInfoDAO;
import com.data.dto.art.ArtInfo;
import com.data.service.Service;

public class ArtInfoDelete implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html; charset=UTF-8");
	    ArtInfo dto = new ArtInfo();
	    ArtInfoDAO dao = new ArtInfoDAO();
	    
	    if(request.getParameter("art_no") == null) {
			int art_no = Integer.parseInt( request.getParameter("art_no") ); 
			request.setAttribute("art_no", art_no);
			request.setAttribute("disViewPath", "/view/art/info/artInfoEdit.jsp");		
		} else {
			String reViewPath = null;
			String alertMessage = null;	
			
			dto.setArt_no(Integer.parseInt(request.getParameter("art_no")));
			
			if(dao.delete(dto) > 0) {
				alertMessage = "삭제완료.";
				reViewPath = "/art_info";
				request.setAttribute("reViewPath", reViewPath);
				request.setAttribute("alertMessage", alertMessage);
			}else {
				alertMessage = "글 삭제실패";
			}
		
		
		}
	}

}
