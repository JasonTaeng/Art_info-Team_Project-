package com.data.dto.board.notice;

public class NoticeImages {
	
	private int no_img_no;
	private String no_img_name;
	private byte[] no_img_data;
	private String no_img_date;
	private int fk_notice_board_no;
	
	public int getNo_img_no() {
		return no_img_no;
	}
	public void setNo_img_no(int no_img_no) {
		this.no_img_no = no_img_no;
	}
	public String getNo_img_name() {
		return no_img_name;
	}
	public void setNo_img_name(String no_img_name) {
		this.no_img_name = no_img_name;
	}
	public byte[] getNo_img_data() {
		return no_img_data;
	}
	public void setNo_img_data(byte[] no_img_data) {
		this.no_img_data = no_img_data;
	}
	public String getNo_img_date() {
		return no_img_date;
	}
	public void setNo_img_date(String no_img_date) {
		this.no_img_date = no_img_date;
	}
	public int getFk_notice_board_no() {
		return fk_notice_board_no;
	}
	public void setFk_notice_board_no(int fk_notice_board_no) {
		this.fk_notice_board_no = fk_notice_board_no;
	}
	
	
}
