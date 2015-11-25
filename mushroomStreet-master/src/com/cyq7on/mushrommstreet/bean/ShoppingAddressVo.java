package com.cyq7on.mushrommstreet.bean;


public class ShoppingAddressVo {
	/**   
	 * @Title: ShoppingAddressVo.java 
	 * @Package com.cyq7on.mushrommstreet.bean 
	 * @Description: 收货地址信息
	 * @author cyq7on  
	 * @date 2015-11-25 下午10:56:22 
	 * @version V1.0   
	 */
	private String name;
	private String tle;
	private String address;
	private String addressDetail;
	private boolean isChecked;
	
	
	public ShoppingAddressVo(String name, String tle, String address,
			String addressDetail, boolean isChecked) {
		super();
		this.name = name;
		this.tle = tle;
		this.address = address;
		this.addressDetail = addressDetail;
		this.isChecked = isChecked;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTle() {
		return tle;
	}
	public void setTle(String tle) {
		this.tle = tle;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	
}
