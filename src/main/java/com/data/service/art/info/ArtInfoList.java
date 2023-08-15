package com.data.service.art.info;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.art.ArtInfoDAO;
import com.data.service.Service;

public class ArtInfoList implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8"); 
			ArtInfoDAO dao = new ArtInfoDAO();	
			
	        String SQL = "SELECT * FROM art_info";
		 	request.setAttribute("list", dao.getList(SQL));
		    request.setAttribute("disViewPath", "/view/art/info/artInfoList.jsp");
	}

}
