package com.data.service.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.service.Service;

public class AdminSearch implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userEnabled = request.getParameter("userEnabled");
		String userAuthority = request.getParameter("userAuthority");
		String reViewPath = "/admin?E=" + userEnabled + "&A=" + userAuthority;
		request.setAttribute("reViewPath", reViewPath);
	}

}