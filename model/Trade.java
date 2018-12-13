package model;

import java.util.Date;

public class Trade {
	
	private int tradeId;
	private Product product;
	private String traderName;
	private Date tradeDate;
	private String counterparty;
	private String status;
	
	public Trade(Product product) {
		this.product = product;
	}
	
	public int getTradeId() {
		return tradeId;
	}
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getTraderName() {
		return traderName;
	}
	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getCounterparty() {
		return counterparty;
	}
	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
