package com.data.service.art.info;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.data.dao.art.ArtInfoDAO;
import com.data.dto.art.ArtInfo;
import com.data.service.Service;

@MultipartConfig(
	fileSizeThreshold = 1024*1024,	//1메가
	maxFileSize = 1024*1024*10, //10메가
	maxRequestSize = 1024*1024*10*5 //50메가
)
public class ArtInfoWrite implements Service{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("art_title")==null || request.getParameter("art_title")=="") {
			request.setAttribute("disViewPath", "/view/art/info/artInfoWrite.jsp");
		} else {
			HttpSession session = request.getSession();
			
			ArtInfo dto = new ArtInfo();
			ArtInfoDAO dao = new ArtInfoDAO();
		    String alertMessage = null;
		    
		    dto.setArt_title(request.getParameter("art_title"));
		    dto.setArt_content(request.getParameter("art_content"));
		    dto.setArt_location(request.getParameter("art_location"));
		    dto.setStart_date(request.getParameter("start_date"));
		    dto.setEnd_date(request.getParameter("end_date"));
		    dto.setArt_duration(request.getParameter("art_duration"));
		    dto.setArt_age(request.getParameter("art_age"));
		    dto.setArt_price(Integer.parseInt( request.getParameter("art_price") ));
		    dto.setFk_user_userID((String)session.getAttribute("userID"));
		    
		    // 파일 입출력 부분
			Collection<Part> parts = request.getParts(); //binary 넘겨 받음
			
			for(Part p : parts) {
				if(!p.getName().equals("file")) {continue;}
				if(p.getSize() == 0) {continue;}

				Part filePart = p;
				String fileName = filePart.getSubmittedFileName();
				InputStream fis = filePart.getInputStream();
				
				String realPath = request.getServletContext().getRealPath("/upload/art-info");
				//System.out.println(realPath);
				
				File path = new File(realPath);
				if(!path.exists()) {
					path.mkdirs();
				}
				
				String uploadPath = realPath + File.separator + fileName;
				
				FileOutputStream fos = new FileOutputStream(uploadPath);
				
//				byte[] buffer = new byte[1024];
//				int size = 0;
//				
//				while((size = fis.read(buffer)) != -1) {
//					fos.write(buffer, 0, size);
//				}
				
				//db에 넣기
				dto.setArt_img_name(fileName);
				
				byte[] imgData = fis.readAllBytes();
				fos.write(imgData);
				
				dto.setArt_img_data(imgData);
				
				int result = dao.write(dto); //MEDIUMBLOB 열은 이론적으로 최대 약 17메가 바이트까지 저장할 수 있음
				if(result>0) {
					alertMessage = "공연소식 올리기 성공";
				    request.setAttribute("reViewPath", "/art_info");
				} else {
					alertMessage = "공연소식 올리기 실패";
				}
				
				fos.close();
				fis.close();
			}
			
			request.setAttribute("alertMessage", alertMessage);
		}
		
	   
	}

}
