package com.data.dto.board.review;

public class ReviewBoard {
	private int re_no;
	private String re_title;
	private String re_content;
	private String re_date;
	private int re_hit;
	private String re_ip;
	private String fk_user_userID;
	
	
	
	public ReviewBoard(int re_no) {
		super();
		this.re_no = re_no;
	}
	public ReviewBoard(int re_no, String re_title, String re_content, String re_date, int re_hit, String re_ip,
			String fk_user_userID) {
		super();
		this.re_no = re_no;
		this.re_title = re_title;
		this.re_content = re_content;
		this.re_date = re_date;
		this.re_hit = re_hit;
		this.re_ip = re_ip;
		this.fk_user_userID = fk_user_userID;
	}
	public ReviewBoard(int re_no, String re_title, String re_content, String re_ip) {
		super();
		this.re_no = re_no;
		this.re_title = re_title;
		this.re_content = re_content;
		this.re_ip = re_ip;
	}
	public ReviewBoard(String re_title, String re_content, String re_ip, String fk_user_userID) {
		super();
		this.re_title = re_title;
		this.re_content = re_content;
		this.re_ip = re_ip;
		this.fk_user_userID = fk_user_userID;
	}
	public ReviewBoard(String re_title, String re_content, String re_date, int re_hit, String re_ip,
			String fk_user_userID) {
		super();
		this.re_title = re_title;
		this.re_content = re_content;
		this.re_date = re_date;
		this.re_hit = re_hit;
		this.re_ip = re_ip;
		this.fk_user_userID = fk_user_userID;
	}
	public ReviewBoard() {
		super();	
	}

	public int getRe_no() {
		return re_no;
	}
	public void setRe_no(int re_no) {
		this.re_no = re_no;
	}
	public String getRe_title() {
		return re_title;
	}
	public void setRe_title(String re_title) {
		this.re_title = re_title;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public String getRe_date() {
		return re_date;
	}
	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}
	public int getRe_hit() {
		return re_hit;
	}
	public void setRe_hit(int re_hit) {
		this.re_hit = re_hit;
	}
	public String getRe_ip() {
		return re_ip;
	}
	public void setRe_ip(String re_ip) {
		this.re_ip = re_ip;
	}
	public String getFk_user_userID() {
		return fk_user_userID;
	}
	public void setFk_user_userID(String fk_user_userID) {
		this.fk_user_userID = fk_user_userID;
	}
	public void setImageURL(String uploadPath) {
		// TODO Auto-generated method stub
		
	}

	
	
}
