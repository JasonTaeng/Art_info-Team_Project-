package com.data.dto.board.inquiry;

public class InquiryReply {
	private int in_reply_no;
	private String fk_user_userID;
	private String in_reply_content;
	private String in_reply_date;
	private String in_reply_check;
	public InquiryReply() { super(); }
	public InquiryReply(int in_reply_no, String fk_user_userID, String in_reply_content, String in_reply_date,
			String in_reply_check) {
		super();
		this.in_reply_no = in_reply_no;
		this.fk_user_userID = fk_user_userID;
		this.in_reply_content = in_reply_content;
		this.in_reply_date = in_reply_date;
		this.in_reply_check = in_reply_check;
	}
	
	public InquiryReply(String in_reply_content) {
		super();
		this.in_reply_content = in_reply_content;
	}
	@Override
	public String toString() {
		return "InquiryReply [in_reply_no=" + in_reply_no + ", fk_user_userID=" + fk_user_userID + ", in_reply_content="
				+ in_reply_content + ", in_reply_date=" + in_reply_date + ", in_reply_check=" + in_reply_check + "]";
	}
	public int getIn_reply_no() {
		return in_reply_no;
	}
	public void setIn_reply_no(int in_reply_no) {
		this.in_reply_no = in_reply_no;
	}
	public String getFk_user_userID() {
		return fk_user_userID;
	}
	public void setFk_user_userID(String fk_user_userID) {
		this.fk_user_userID = fk_user_userID;
	}
	public String getIn_reply_content() {
		return in_reply_content;
	}
	public void setIn_reply_content(String in_reply_content) {
		this.in_reply_content = in_reply_content;
	}
	public String getIn_reply_date() {
		return in_reply_date;
	}
	public void setIn_reply_date(String in_reply_date) {
		this.in_reply_date = in_reply_date;
	}
	public String getIn_reply_check() {
		return in_reply_check;
	}
	public void setIn_reply_check(String in_reply_check) {
		this.in_reply_check = in_reply_check;
	}
	
	
	
	
}
