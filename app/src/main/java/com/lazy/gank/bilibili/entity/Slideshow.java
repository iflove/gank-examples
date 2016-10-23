package com.lazy.gank.bilibili.entity;

import java.util.List;

public class Slideshow {

	private int code;
	private String message;
	private List<SlideshowResult> result;

	public void setCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setResult(List<SlideshowResult> result) {
		this.result = result;
	}

	public List<SlideshowResult> getResult() {
		return result;
	}

}