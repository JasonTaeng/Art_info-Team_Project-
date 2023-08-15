package com.data.dto.board.notice;

public class NoticeList {
	
	private int no_no;
	private String no_title;
	private String no_date;
	private String no_publish;
	private String no_publish_date;
	private int no_hit;
	private String fk_user_userID;
	private int cntComment;
	
	public int getNo_no() {
		return no_no;
	}
	public void setNo_no(int no_no) {
		this.no_no = no_no;
	}
	public String getNo_title() {
		return no_title;
	}
	public void setNo_title(String no_title) {
		this.no_title = no_title;
	}
	public String getNo_date() {
		return no_date;
	}
	public void setNo_date(String no_date) {
		this.no_date = no_date;
	}
	public String getNo_publish() {
		return no_publish;
	}
	public void setNo_publish(String no_publish) {
		this.no_publish = no_publish;
	}
	public String getNo_publish_date() {
		return no_publish_date;
	}
	public void setNo_publish_date(String no_publish_date) {
		this.no_publish_date = no_publish_date;
	}
	public int getNo_hit() {
		return no_hit;
	}
	public void setNo_hit(int no_hit) {
		this.no_hit = no_hit;
	}
	public String getFk_user_userID() {
		return fk_user_userID;
	}
	public void setFk_user_userID(String fk_user_userID) {
		this.fk_user_userID = fk_user_userID;
	}
	public int getCntComment() {
		return cntComment;
	}
	public void setCntComment(int cntComment) {
		this.cntComment = cntComment;
	}
	
}
