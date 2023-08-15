package com.data.dto.board.review;

public class ReviewImg {
	
	private int re_img_no;
	private String re_img_name;
	private byte[] re_img_data;
	private String re_img_date;
	private int fk_review_board_re_no;
	
	public int getRe_img_no() {
		return re_img_no;
	}
	public void setRe_img_no(int re_img_no) {
		this.re_img_no = re_img_no;
	}
	public String getRe_img_name() {
		return re_img_name;
	}
	public void setRe_img_name(String re_img_name) {
		this.re_img_name = re_img_name;
	}
	public byte[] getRe_img_data() {
		return re_img_data;
	}
	public void setRe_img_data(byte[] re_img_data) {
		this.re_img_data = re_img_data;
	}
	public String getRe_img_date() {
		return re_img_date;
	}
	public void setRe_img_date(String re_img_date) {
		this.re_img_date = re_img_date;
	}
	public int getFk_review_board_re_no() {
		return fk_review_board_re_no;
	}
	public void setFk_review_board_re_no(int fk_review_board_re_no) {
		this.fk_review_board_re_no = fk_review_board_re_no;
	}
	
}
