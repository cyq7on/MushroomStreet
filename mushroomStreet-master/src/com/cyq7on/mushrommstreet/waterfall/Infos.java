package com.cyq7on.mushrommstreet.waterfall;

import java.util.List;

public class Infos {
	private String			newsLast	= "0";
	private int				type		= 0;
	private List<DuitangInfo>	newsInfos;

	public String getNewsLast() {
		return newsLast;
	}

	public void setNewsLast(String newsLast) {
		this.newsLast = newsLast;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<DuitangInfo> getNewsInfos() {
		return newsInfos;
	}

	public void setNewsInfos(List<DuitangInfo> newsInfos) {
		this.newsInfos = newsInfos;
	}

}
