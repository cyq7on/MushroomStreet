package com.cyq7on.mushrommstreet.bean;

/**   
 * @Title: ShoppingDetailVo.java 
 * @Package com.cyq7on.mushrommstreet.bean 
 * @Description: 购物详情页的数据类 
 * @author cyq7on  
 * @date 2015-11-11 下午9:26:55 
 * @version V1.0   
 */
public class ShoppingDetailVo {
	private String imageUrl;
	private String name;
	private String priceNow;
	private String priceOld;
	
	public ShoppingDetailVo(String imageUrl, String name, String priceNow,
			String priceOld) {
		super();
		this.imageUrl = imageUrl;
		this.name = name;
		this.priceNow = priceNow;
		this.priceOld = priceOld;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPriceNow() {
		return priceNow;
	}
	public void setPriceNow(String priceNow) {
		this.priceNow = priceNow;
	}
	public String getPriceOld() {
		return priceOld;
	}
	public void setPriceOld(String priceOld) {
		this.priceOld = priceOld;
	}
	
}
