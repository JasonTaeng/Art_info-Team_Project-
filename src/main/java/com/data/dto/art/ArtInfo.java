package com.data.dto.art;

import java.util.Arrays;

public class ArtInfo {
	private int art_no;
	private String art_title;
	private String art_content;
	private String art_location;
	private String start_date;
	private String end_date;
	private String art_duration;
	private String art_age;
	private String art_img_name;
	private byte[] art_img_data;
	private String art_reg_date;
	private int art_price;
	private String fk_user_userID;
	
	public ArtInfo() { super(); }
	
	public ArtInfo(int art_no, String art_title, String art_content, String art_location,
			String start_date, String end_date, String art_duration, String art_age, String art_img_name,
			byte[] art_img_data, String art_reg_date, int art_price, String fk_user_userID) {
		super();
		this.art_no = art_no;
		this.art_title = art_title;
		this.art_content = art_content;
		this.art_location = art_location;
		this.start_date = start_date;
		this.end_date = end_date;
		this.art_duration = art_duration;
		this.art_age = art_age;
		this.art_img_name = art_img_name;
		this.art_img_data = art_img_data;
		this.art_reg_date = art_reg_date;
		this.art_price = art_price;
		this.fk_user_userID = fk_user_userID;
	}
	@Override
	public String toString() {
		return "artInfo [art_no=" + art_no + ", art_title=" + art_title
				+ ", art_content=" + art_content + ", art_location=" + art_location + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", art_duration=" + art_duration + ", art_age=" + art_age
				+ ", art_img_name=" + art_img_name + ", art_img_data=" + Arrays.toString(art_img_data)
				+ ", art_reg_date=" + art_reg_date + ", art_price=" + art_price
				+ "fk_user_userID=" + fk_user_userID + "]";
	}
	public int getArt_no() {
		return art_no;
	}
	public void setArt_no(int art_no) {
		this.art_no = art_no;
	}
	public String getArt_title() {
		return art_title;
	}
	public void setArt_title(String art_title) {
		this.art_title = art_title;
	}
	public String getArt_content() {
		return art_content;
	}
	public void setArt_content(String art_content) {
		this.art_content = art_content;
	}
	public String getArt_location() {
		return art_location;
	}
	public void setArt_location(String art_location) {
		this.art_location = art_location;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getArt_duration() {
		return art_duration;
	}
	public void setArt_duration(String art_duration) {
		this.art_duration = art_duration;
	}
	public String getArt_age() {
		return art_age;
	}
	public void setArt_age(String art_age) {
		this.art_age = art_age;
	}
	public String getArt_img_name() {
		return art_img_name;
	}
	public void setArt_img_name(String art_img_name) {
		this.art_img_name = art_img_name;
	}
	public byte[] getArt_img_data() {
		return art_img_data;
	}
	public void setArt_img_data(byte[] art_img_data) {
		this.art_img_data = art_img_data;
	}
	public String getArt_reg_date() {
		return art_reg_date;
	}
	public void setArt_reg_date(String art_reg_date) {
		this.art_reg_date = art_reg_date;
	}
	public int getArt_price() {
		return art_price;
	}
	public void setArt_price(int art_price) {
		this.art_price = art_price;
	}
	public String getFk_user_userID() {
		return fk_user_userID;
	}
	public void setFk_user_userID(String fk_user_userID) {
		this.fk_user_userID = fk_user_userID;
	}
	
	
}
