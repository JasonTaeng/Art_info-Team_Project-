package com.data.service.art.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.art.ArtInfoDAO;
import com.data.dto.art.ArtInfo;
import com.data.service.Service;

public class ArtInfoDetail implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("art_no")!=null) {
			request.setAttribute("disViewPath", "/view/art/info/artInfoDetail.jsp");
				
			ArtInfo dto = new ArtInfo();
			ArtInfoDAO dao = new ArtInfoDAO();
			
			dto.setArt_no(Integer.parseInt(request.getParameter("art_no")));
			request.setAttribute("dto", dao.detail(dto));
			
		}
	}

}
