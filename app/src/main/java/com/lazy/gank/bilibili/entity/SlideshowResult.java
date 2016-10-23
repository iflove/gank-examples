package com.lazy.gank.bilibili.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class SlideshowResult {

	private int id;
	private String img;
	@SerializedName("is_ad")
	private int isAd;
	private Date link;
	private String simg;
	private String title;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getImg() {
		return img;
	}

	public void setIsAd(int isAd) {
		this.isAd = isAd;
	}

	public int getIsAd() {
		return isAd;
	}

	public void setLink(Date link) {
		this.link = link;
	}

	public Date getLink() {
		return link;
	}

	public void setSimg(String simg) {
		this.simg = simg;
	}

	public String getSimg() {
		return simg;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}