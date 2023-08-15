package com.data.dto.board.inquiry;

public class InquiryBoard {
	
	private int in_no;
	private String fk_user_userID;
	private String in_title;
	private String in_content;
	private String in_password;
	private String in_set;
	private String in_date;
	private String in_ip;
	public InquiryBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InquiryBoard(int in_no, String fk_user_userID, String in_title, String in_content, String in_password,
			String in_set, String in_date, String in_ip) {
		super();
		this.in_no = in_no;
		this.fk_user_userID = fk_user_userID;
		this.in_title = in_title;
		this.in_content = in_content;
		this.in_password = in_password;
		this.in_set = in_set;
		this.in_date = in_date;
		this.in_ip = in_ip;
	}
	
	public InquiryBoard(String fk_user_userID, String in_title, String in_content, String in_password, String in_set,
			String in_date, String in_ip) {
		super();
		this.fk_user_userID = fk_user_userID;
		this.in_title = in_title;
		this.in_content = in_content;
		this.in_password = in_password;
		this.in_set = in_set;
		this.in_date = in_date;
		this.in_ip = in_ip;
	}
	@Override
	public String toString() {
		return "InquiryBoard [in_no=" + in_no + ", fk_user_userID=" + fk_user_userID + ", in_title=" + in_title
				+ ", in_content=" + in_content + ", in_password=" + in_password + ", in_set=" + in_set + ", in_date="
				+ in_date + ", in_ip=" + in_ip + "]";
	}
	public int getIn_no() {
		return in_no;
	}
	public void setIn_no(int in_no) {
		this.in_no = in_no;
	}
	public String getFk_user_userID() {
		return fk_user_userID;
	}
	public void setFk_user_userID(String fk_user_userID) {
		this.fk_user_userID = fk_user_userID;
	}
	public String getIn_title() {
		return in_title;
	}
	public void setIn_title(String in_title) {
		this.in_title = in_title;
	}
	public String getIn_content() {
		return in_content;
	}
	public void setIn_content(String in_content) {
		this.in_content = in_content;
	}
	public String getIn_password() {
		return in_password;
	}
	public void setIn_password(String in_password) {
		this.in_password = in_password;
	}
	public String getIn_set() {
		return in_set;
	}
	public void setIn_set(String in_set) {
		this.in_set = in_set;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public String getIn_ip() {
		return in_ip;
	}
	public void setIn_ip(String in_ip) {
		this.in_ip = in_ip;
	}
	
	
	
}
