package com.data.service.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.dao.art.ArtInfoDAO;
import com.data.service.Service;


public class MainPageController implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArtInfoDAO dao = new ArtInfoDAO(); 
		String SQL = "select * from art_info order by art_no desc limit 0,8";
		request.setAttribute("list", dao.getList(SQL));
		request.setAttribute("disViewPath", "/view/main.jsp");
		
	}


}
