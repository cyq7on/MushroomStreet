package com.cyq7on.mushrommstreet.bean;

import java.util.List;

public class DynamicVo {
	
	private boolean isShow;//是否显示"蘑菇街官方喜欢了"
	private String url;//用戶头像url
	private String userName;//用户名
	private String date;//动态时间
	private String tvPlace;//动态地址
	private String btnAttention;//关注按钮
	private String tvContent;//动态内容
	private List<String> ContentImageurl;//动态里图片url
	private String goods;//相关商品
	private String ivDianzan;//点赞图标
	private String DianzanNum;//点赞数量
	private String Comment;//评论
	private String LookAll;//查看所有评论
	private String CommentPeople;//评论人
	private String Content;//评论内容
	
	public boolean isShow() {
		return isShow;
	}
	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTvPlace() {
		return tvPlace;
	}
	public void setTvPlace(String tvPlace) {
		this.tvPlace = tvPlace;
	}
	public String getBtnAttention() {
		return btnAttention;
	}
	public void setBtnAttention(String btnAttention) {
		this.btnAttention = btnAttention;
	}
	public String getTvContent() {
		return tvContent;
	}
	public void setTvContent(String tvContent) {
		this.tvContent = tvContent;
	}
	public List<String> getContentImageurl() {
		return ContentImageurl;
	}
	public void setContentImageurl(List<String> contentImageurl) {
		ContentImageurl = contentImageurl;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public String getIvDianzan() {
		return ivDianzan;
	}
	public void setIvDianzan(String ivDianzan) {
		this.ivDianzan = ivDianzan;
	}
	public String getDianzanNum() {
		return DianzanNum;
	}
	public void setDianzanNum(String dianzanNum) {
		DianzanNum = dianzanNum;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	public String getLookAll() {
		return LookAll;
	}
	public void setLookAll(String lookAll) {
		LookAll = lookAll;
	}
	public String getCommentPeople() {
		return CommentPeople;
	}
	public void setCommentPeople(String commentPeople) {
		CommentPeople = commentPeople;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
}
