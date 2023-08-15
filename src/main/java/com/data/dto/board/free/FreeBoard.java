package com.data.dto.board.free;

public class FreeBoard {
	
	private int free_no;
	private String fk_user_userID;
	private String free_password;
	private String free_title;
	private String free_content;	
	private String free_date;
	private int free_hit;
	private String free_ip;
	
	public FreeBoard() {
		super();
	}
	
	public FreeBoard(int free_no, String fk_user_userID, String free_password, String free_title, String free_content,
			String free_date, int free_hit, String free_ip) {
		super();
		this.free_no = free_no;
		this.fk_user_userID = fk_user_userID;
		this.free_password = free_password;
		this.free_title = free_title;
		this.free_content = free_content;
		this.free_date = free_date;
		this.free_hit = free_hit;
		this.free_ip = free_ip;
	}
	
	@Override
	public String toString() {
		return "Free_Board [free_no=" + free_no + ", fk_user_userID=" + fk_user_userID + ", free_password="
				+ free_password + ", free_title=" + free_title + ", free_content=" + free_content + ", free_date="
				+ free_date + ", free_hit=" + free_hit + ", free_ip=" + free_ip + "]";
	}
	
	public int getFree_no() {
		return free_no;
	}
	public void setFree_no(int free_no) {
		this.free_no = free_no;
	}
	public String getFk_user_userID() {
		return fk_user_userID;
	}
	public void setFk_user_userID(String fk_user_userID) {
		this.fk_user_userID = fk_user_userID;
	}
	public String getFree_password() {
		return free_password;
	}
	public void setFree_password(String free_password) {
		this.free_password = free_password;
	}
	public String getFree_title() {
		return free_title;
	}
	public void setFree_title(String free_title) {
		this.free_title = free_title;
	}
	public String getFree_content() {
		return free_content;
	}
	public void setFree_content(String free_content) {
		this.free_content = free_content;
	}
	public String getFree_date() {
		return free_date;
	}
	public void setFree_date(String free_date) {
		this.free_date = free_date;
	}
	public int getFree_hit() {
		return free_hit;
	}
	public void setFree_hit(int free_hit) {
		this.free_hit = free_hit;
	}
	public String getFree_ip() {
		return free_ip;
	}
	public void setFree_ip(String free_ip) {
		this.free_ip = free_ip;
	}	
}