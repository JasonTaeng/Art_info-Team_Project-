package com.data.dto.board.notice;

public class NoticeComment {
	
	private int no_comm_no;
	private String no_comm_content;
	private String no_comm_date;
	private String fk_user_userID;
	private int fk_notice_board_no;
	
	public int getNo_comm_no() {
		return no_comm_no;
	}
	public void setNo_comm_no(int no_comm_no) {
		this.no_comm_no = no_comm_no;
	}
	public String getNo_comm_content() {
		return no_comm_content;
	}
	public void setNo_comm_content(String no_comm_content) {
		this.no_comm_content = no_comm_content;
	}
	public String getNo_comm_date() {
		return no_comm_date;
	}
	public void setNo_comm_date(String no_comm_date) {
		this.no_comm_date = no_comm_date;
	}
	public String getFk_user_userID() {
		return fk_user_userID;
	}
	public void setFk_user_userID(String fk_user_userID) {
		this.fk_user_userID = fk_user_userID;
	}
	public int getFk_notice_board_no() {
		return fk_notice_board_no;
	}
	public void setFk_notice_board_no(int fk_notice_board_no) {
		this.fk_notice_board_no = fk_notice_board_no;
	}
	
	
}
