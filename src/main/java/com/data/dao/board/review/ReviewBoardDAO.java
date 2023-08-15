package com.data.dao.board.review;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.data.dbmanager.DBManager;
import com.data.dto.board.review.ReviewBoard;

public class ReviewBoardDAO {
	
	// 제일 높은 게시물 다음 번호 리턴
	public int getNext(String SQL) {
		DBManager db = new DBManager();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	    
        try {
	        conn = db.getConnection();
            pstmt = conn.prepareStatement(SQL);
            rset = pstmt.executeQuery();
            if(rset.next()) {
            	return rset.getInt(1) + 1;
            }
            return 1; // 첫 게시물인 경우
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류 또는 catch문 실행된 경우
    }
	
	public List<ReviewBoard> list() {
	    List<ReviewBoard> list = new ArrayList<>();
	    String sql = "SELECT * FROM review_board ORDER BY re_no DESC";
	    DBManager db = new DBManager();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	        conn = db.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        rset = pstmt.executeQuery();
	        while (rset.next()) {
	        	int reNo = rset.getInt("re_no");
	            String reTitle = rset.getString("re_title");
	            String reContent = rset.getString("re_content");
	            String reDate = rset.getString("re_date");
	            int reHit = rset.getInt("re_hit");
	            String reIp = rset.getString("re_ip");
	            String fkUserId = rset.getString("fk_user_userID");

	            ReviewBoard reviewBoard = new ReviewBoard(reNo,reTitle, reContent, reDate, reHit, reIp, fkUserId);
	            list.add(reviewBoard);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (rset != null) {
	            try {
	                rset.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return list;
	}
	
	public int insert(ReviewBoard dto) throws UnknownHostException {
		String sql = "INSERT INTO review_board (re_title, re_content, re_hit, re_ip, fk_user_userID)" +
                "SELECT ?, ?, ?, ?, u.userID " +
                "FROM user u " +
                "WHERE u.userID = ?";
   DBManager db = new DBManager();
   Connection conn = null;
   PreparedStatement pstmt = null;

   try {
       conn = db.getConnection();
       pstmt = conn.prepareStatement(sql);
       pstmt.setString(1, dto.getRe_title());
       pstmt.setString(2, dto.getRe_content());
       pstmt.setInt(3, dto.getRe_hit());
       pstmt.setString(4, InetAddress.getLocalHost().getHostAddress());
       pstmt.setString(5, dto.getFk_user_userID());
       return pstmt.executeUpdate(); // 쿼리 실행 후 영향을 받는 행의 수를 반환

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
	        if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
	    }

	    return -1;
	}
	
		public int update(ReviewBoard dto) {
			int result = -1;
			String sql = " update review_board set re_title=? , re_content=? where re_no=?";
			DBManager db = new DBManager();
			Connection conn= null; PreparedStatement pstmt = null;
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);				
				pstmt.setString(1, dto.getRe_title());
				pstmt.setString(2, dto.getRe_content());
				pstmt.setInt(3, dto.getRe_no());
				result = pstmt.executeUpdate();

			} catch (SQLException e) { e.printStackTrace();
			} finally {
				if(pstmt != null) {try { pstmt.close(); } catch (SQLException e) {  e.printStackTrace(); } }
				if(conn  != null) {try { conn.close(); }  catch (SQLException e) {  e.printStackTrace(); } }
			}
			return result;
			
		}
		
		public ReviewBoard get(int re_no) {
		    ReviewBoard dto = null;
		    String sql = "SELECT * FROM review_board WHERE re_no = ?";
		    DBManager db = new DBManager();
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rset = null;

		    try {
		        conn = db.getConnection();
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setInt(1, re_no);
		        rset = pstmt.executeQuery();

		        if (rset.next()) {
		            String re_title = rset.getString("re_title");
		            String re_content = rset.getString("re_content");
		            String re_date = rset.getString("re_date");
		            int re_hit = rset.getInt("re_hit");
		            String re_ip = rset.getString("re_ip");
		            String fk_user_userID = rset.getString("fk_user_userID");

		            dto = new ReviewBoard(re_no, re_title, re_content, re_date, re_hit, re_ip, fk_user_userID);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        if (rset != null) {
		            try {
		                rset.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        if (pstmt != null) {
		            try {
		                pstmt.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        if (conn != null) {
		            try {
		                conn.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }

		    return dto;
		}
	
	
	public int update_hit(ReviewBoard dto) {
		String sql = "update review_board set re_hit = re_hit +1  where re_no=?";
		int result = -1;
		DBManager db = new DBManager();
		Connection conn= null; PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getRe_no());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) { e.printStackTrace();
		}finally {
			if(pstmt !=null) {try { pstmt.close(); } catch (SQLException e) {  e.printStackTrace(); } }
			if(conn !=null) {try { conn.close(); } catch (SQLException e) {  e.printStackTrace(); } }
		}
		return result;
		
	}
	
	public ReviewBoard detail(ReviewBoard dto) {
	    String sql = "SELECT review_board.*, " +
	            "    CASE " +
	            "        WHEN TIMESTAMPDIFF(SECOND, re_date, CURRENT_TIMESTAMP()) < 60 THEN '방금' " +
	            "        WHEN TIMESTAMPDIFF(MINUTE, re_date, CURRENT_TIMESTAMP()) < 60 THEN CONCAT(TIMESTAMPDIFF(MINUTE, re_date, CURRENT_TIMESTAMP()), '분 전') " +
	            "        WHEN TIMESTAMPDIFF(HOUR, re_date, CURRENT_TIMESTAMP()) < 24 THEN CONCAT(TIMESTAMPDIFF(HOUR, re_date, CURRENT_TIMESTAMP()), '시간 전') " +
	            "        WHEN TIMESTAMPDIFF(DAY, re_date, CURRENT_TIMESTAMP()) < 7 THEN CONCAT(TIMESTAMPDIFF(DAY, re_date, CURRENT_TIMESTAMP()), '일 전') " +
	            "        ELSE re_date " +
	            "    END AS formatted_re_date " +
	            "FROM review_board " +
	            "WHERE re_no=?";
	    
	    DBManager db = new DBManager();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	    ReviewBoard result = new ReviewBoard();
	    try {
	        conn = db.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, dto.getRe_no());
	        rset = pstmt.executeQuery();

	        while (rset.next()) {
	            result = new ReviewBoard(rset.getInt("re_no"),
	                    rset.getString("re_title"),
	                    rset.getString("re_content"),
	                    rset.getString("re_date"),
	                    rset.getInt("re_hit"),
	                    rset.getString("re_ip"),
	                    rset.getString("fk_user_userID"));
	        }

	        String reDate = result.getRe_date(); // result 객체에서 re_date 값을 가져옴
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date reDateObj = sdf.parse(reDate);
	        Calendar currentCal = Calendar.getInstance();
	        Calendar reDateCal = Calendar.getInstance();
	        reDateCal.setTime(reDateObj);
	        long diffInMillis = currentCal.getTimeInMillis() - reDateCal.getTimeInMillis();
	        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(diffInMillis);

	        String formattedReDate;

	        if (diffInSeconds < 60) {
	            formattedReDate = "방금";
	        } else if (diffInSeconds < 60 * 60) {
	            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
	            formattedReDate = diffInMinutes + "분 전";
	        } else if (diffInSeconds < 60 * 60 * 24) {
	            long diffInHours = TimeUnit.MILLISECONDS.toHours(diffInMillis);
	            formattedReDate = diffInHours + "시간 전";
	        } else if (diffInSeconds < 60 * 60 * 24 * 7) {
	            long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis);
	            formattedReDate = diffInDays + "일 전";
	        } else {
	            formattedReDate = reDate;
	        }

	        result.setRe_date(formattedReDate);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    } finally {
	        if (rset != null) {
	            try {
	                rset.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return result;
	}

	
	public int delete (ReviewBoard dto) {
		String sql = "delete from review_board where re_no=?";
		int result = -1; 
		DBManager db = new DBManager();
		Connection conn= null; PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getRe_no());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) { e.printStackTrace();
		}finally {
			if(pstmt !=null) {try { pstmt.close(); } catch (SQLException e) {  e.printStackTrace(); } }
			if(conn !=null) {try { conn.close(); } catch (SQLException e) {  e.printStackTrace(); } }
		}
		return result;
		
	}
	public void reOrder() {
	    String getSql = "SELECT re_no FROM review_board ORDER BY re_no ASC";
	    String updateSql = "UPDATE review_board SET re_no = ? WHERE re_no = ?";
	    DBManager db = new DBManager();

	    try (
	        Connection conn = db.getConnection();
	        PreparedStatement getStmt = conn.prepareStatement(getSql);
	        PreparedStatement updateStmt = conn.prepareStatement(updateSql);
	    ) {
	        ResultSet rset = getStmt.executeQuery();

	        int newReNo = 1;
	        while (rset.next()) {
	            int oldReNo = rset.getInt("re_no");

	            // 임시 기본 키 값 할당 (임시로 충분히 큰 값으로 설정)
	            updateStmt.setInt(1, oldReNo);
	            updateStmt.setInt(2, oldReNo);
	            updateStmt.executeUpdate();

	            // 실제 기본 키 값 할당
	            updateStmt.setInt(1, newReNo);
	            updateStmt.setInt(2, oldReNo);
	            updateStmt.executeUpdate();

	            newReNo++;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public List<ReviewBoard> listpage (int startno) {
		List<ReviewBoard> list = new ArrayList<ReviewBoard>();
		String sql = "select * from review_board  order by re_no desc limit ?,10";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn = db.getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, startno);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new ReviewBoard(rset.getInt("re_no"),rset.getString("re_title"),rset.getString("re_content"),
						rset.getString("re_date") , 
						rset.getInt("re_hit"),
						rset.getString("re_ip"),
						rset.getString("fk_user_userID")
						));
			}
		//	System.out.println("..............."+list);
		} catch (SQLException e) {  e.printStackTrace();
		} finally {
			if(rset != null) {try { rset.close(); } catch (SQLException e) {  e.printStackTrace(); }}
			if(pstmt != null) {try { pstmt.close(); } catch (SQLException e) {  e.printStackTrace(); }}
			if(conn != null) {try { conn.close(); } catch (SQLException e) {  e.printStackTrace(); }}
		}
		
		return list;
		
	}
	public int listCnt() {
		int total = -1;
		String sql = "select  count(*) from  review_board";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		DBManager db = new DBManager();
		
		try {
			conn=db.getConnection();
			pstmt=conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				// total= rset.getInt("count(*)");
				total = rset.getInt(1);		// 구하는 순서 1,2,3 count(*) 한개 밖에 없기떄문에...
			}
		//	System.out.println("............." + total);
		} catch (SQLException e) { e.printStackTrace();
		} finally {
			if(rset != null) {try { rset.close(); } catch (SQLException e) {  e.printStackTrace(); }}
			if(pstmt != null) {try { pstmt.close(); } catch (SQLException e) {  e.printStackTrace(); }}
			if(conn != null) {try { conn.close(); } catch (SQLException e) {  e.printStackTrace(); }}
		}
		return total;
		
	}
	
 // 페이징처리 1.
	public List<ReviewBoard> getPagedReviews(int pageNo, int pageSize) {
	    List<ReviewBoard> list = new ArrayList<>();
	    String sql = "SELECT * FROM review_board ORDER BY re_no DESC LIMIT ? OFFSET ?";
	    DBManager db = new DBManager();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	        conn = db.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, pageSize);
	        pstmt.setInt(2, (pageNo - 1) * pageSize); // 페이지 번호는 1부터 시작하므로, 실제 offset은 pageNo - 1을 사용

	        rset = pstmt.executeQuery();

	        while (rset.next()) {
	            int reNo = rset.getInt("re_no");
	            String reTitle = rset.getString("re_title");
	            String reContent = rset.getString("re_content");
	            String reDate = rset.getString("re_date");
	            int reHit = rset.getInt("re_hit");
	            String reIp = rset.getString("re_ip");
	            String fkUserId = rset.getString("fk_user_userID");

	            ReviewBoard reviewBoard = new ReviewBoard(reNo, reTitle, reContent, reDate, reHit, reIp, fkUserId);
	            list.add(reviewBoard);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (rset != null) { try { rset.close(); } catch (SQLException e) { e.printStackTrace(); } }
	        if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
	        if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
	    }
	    return list;
	}
	// 페이지 총페이지수 1-2
	
	public int getTotalPosts() {
	    String sql = "SELECT COUNT(*) FROM review_board";
	    int totalPosts = 0;
	    DBManager db = new DBManager();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    try {
	        conn = db.getConnection();
	        pstmt = conn.prepareStatement(sql);
	        rset = pstmt.executeQuery();

	        if (rset.next()) { totalPosts = rset.getInt(1); } 
	        } catch (SQLException e) { e.printStackTrace(); 
	        } finally {
		        if (rset != null) { try { rset.close(); } catch (SQLException e) { e.printStackTrace(); } }
		        if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
		        if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		    }

	    return totalPosts;
	}
	
	
	//
	 public boolean uploadImage(InputStream inputStream, String uploadPath) {
	        
		 try {
	            OutputStream outputStream = new FileOutputStream(uploadPath);

	            byte[] buffer = new byte[4096];
	            int bytesRead;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }

	            outputStream.close();
	            inputStream.close();
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
	
}
