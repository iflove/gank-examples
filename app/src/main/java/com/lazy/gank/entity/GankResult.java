package com.lazy.gank.entity;

import java.util.Date;

/**
 * Created by lazy on 16/9/11.
 */
public class GankResult {

	public Date createdAt;

	public Date publishedAt;

	public String type;

	public String used;

	public String url;

	public String desc;

	public String who;

	public String source;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "GankResult{" +
				"createdAt=" + createdAt +
				", publishedAt=" + publishedAt +
				", type='" + type + '\'' +
				", used='" + used + '\'' +
				", url='" + url + '\'' +
				", desc='" + desc + '\'' +
				", who='" + who + '\'' +
				", source='" + source + '\'' +
				'}';
	}
}
