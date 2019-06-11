package net.onest.go2school.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Shop {
	private int shopId;
	private String shopName;
	private String shopLink;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date shopSettlingTime;//入驻时间
	private String shopNotes;
	
	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Shop(int shopId, String shopName, String shopLink, Date shopSettlingTime, String shopNotes) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopLink = shopLink;
		this.shopSettlingTime = shopSettlingTime;
		this.shopNotes = shopNotes;
	}

	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopLink() {
		return shopLink;
	}
	public void setShopLink(String shopLink) {
		this.shopLink = shopLink;
	}
	public Date getShopSettlingTime() {
		return shopSettlingTime;
	}
	public void setShopSettlingTime(Date shopSettlingTime) {
		this.shopSettlingTime = shopSettlingTime;
	}
	public String getShopNotes() {
		return shopNotes;
	}
	public void setShopNotes(String shopNotes) {
		this.shopNotes = shopNotes;
	}
	@Override
	public String toString() {
		return "Shop [shopId=" + shopId + ", shopName=" + shopName + ", shopLink=" + shopLink + ", shopSettlingTime="
				+ shopSettlingTime + ", shopNotes=" + shopNotes + "]";
	}
	
}
