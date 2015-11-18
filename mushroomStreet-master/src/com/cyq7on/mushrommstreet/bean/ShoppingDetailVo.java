package com.cyq7on.mushrommstreet.bean;

/**   
 * @Title: ShoppingDetailVo.java 
 * @Package com.cyq7on.mushrommstreet.bean 
 * @Description: 购物详情页的数据类  拓展到购物车数据类
 * @author cyq7on  
 * @date 2015-11-11 下午9:26:55 
 * @version V1.0   
 */
public class ShoppingDetailVo {
	private String imageUrl;
	private String name;
	private String priceNow;
	private String priceOld;
	//以下是购物车里特有的成员
	private String color;
	private String size;
	private String storeName;
	private int count;
	//确认订单特有
	private String freight;
	private String priceAll;
	public ShoppingDetailVo(String imageUrl, String name, String priceNow,
			String priceOld) {
		super();
		this.imageUrl = imageUrl;
		this.name = name;
		this.priceNow = priceNow;
		this.priceOld = priceOld;
	}
	
	
	public ShoppingDetailVo(String imageUrl, String name, String priceNow,
			String priceOld, String storeName) {
		super();
		this.imageUrl = imageUrl;
		this.name = name;
		this.priceNow = priceNow;
		this.priceOld = priceOld;
		this.storeName = storeName;
	}

	

	public String getFreight() {
		return freight;
	}


	public void setFreight(String freight) {
		this.freight = freight;
	}


	public String getPriceAll() {
		return priceAll;
	}


	public void setPriceAll(String priceAll) {
		this.priceAll = priceAll;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
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
