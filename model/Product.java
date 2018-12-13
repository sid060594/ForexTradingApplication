package model;

import java.util.Date;

public class Product {
	
	private int productId;
	private String buyCcy;
	private String sellCcy;
	private double quantity;
	private double settlementAmount;
	private double fxRate;
	private Date settlementDate;
	private Date expiryDate;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getBuyCcy() {
		return buyCcy;
	}
	public void setBuyCcy(String buyCcy) {
		this.buyCcy = buyCcy;
	}
	public String getSellCcy() {
		return sellCcy;
	}
	public void setSellCcy(String sellCcy) {
		this.sellCcy = sellCcy;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getSettlementAmount() {
		return settlementAmount;
	}
	public void setSettlementAmount(double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	public double getFxRate() {
		return fxRate;
	}
	public void setFxRate(double fxRate) {
		this.fxRate = fxRate;
	}
	public Date getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	

}
