package model;

import java.sql.Date;

import javafx.beans.property.*;

public class ViewTrading {
	Integer TraderID;
	 String Action;
	 String CurrencyCode;
	 String SettlementCurrencyCode;
	 String Quantity;
	 Date SettlementDate;
	 Date ExpiryDate;
	 String TotalAmount;
	 String Balance;
	 
	

	/**
	 * @param Action
	 * @param CurrencyCode
	 * @param SettlementCurrencyCode
	 * @param Quantity
	 * @param TotalAmount
	 * @param SettlementDate
	 * @param ExpiryDate
	 * @param TraderID
	 * @param Balance
	 */
	public ViewTrading( Integer TraderID,String Action, String CurrencyCode, String SettlementCurrencyCode, String Quantity,
			Date SettlementDate, Date ExpiryDate, String TotalAmount, String Balance) {
		super();
		TraderID = TraderID;
		Action = Action;
		CurrencyCode = CurrencyCode;
		SettlementCurrencyCode = SettlementCurrencyCode;
		Quantity = Quantity;
		SettlementDate = SettlementDate;
		ExpiryDate = ExpiryDate;
		TotalAmount = TotalAmount;
		Balance = Balance;
	}

	/**
	 * @return the Action
	 */
	public String getAction() {
		return Action;
	}

	/**
	 * @param Action the Action to set
	 */
	public void setAction(String Action) {
		Action = Action;
	}

	/**
	 * @return the CurrencyCode
	 */
	public String getCurrencyCode() {
		return CurrencyCode;
	}

	/**
	 * @param CurrencyCode the CurrencyCode to set
	 */
	public void setCurrencyCode(String CurrencyCode) {
		CurrencyCode = CurrencyCode;
	}

	/**
	 * @return the SettlementCurrencyCode
	 */
	public String getSettlementCurrencyCode() {
		return SettlementCurrencyCode;
	}

	/**
	 * @param SettlementCurrencyCode the SettlementCurrencyCode to set
	 */
	public void setSettlementCurrencyCode(String SettlementCurrencyCode) {
		SettlementCurrencyCode = SettlementCurrencyCode;
	}

	/**
	 * @return the Quantity
	 */
	public String getQuantity() {
		return Quantity;
	}

	/**
	 * @param Quantity the Quantity to set
	 */
	public void setQuantity(String Quantity) {
		Quantity = Quantity;
	}

	/**
	 * @return the TotalAmount
	 */
	public String getTotalAmount() {
		return TotalAmount;
	}

	/**
	 * @param TotalAmount the TotalAmount to set
	 */
	public void setTotalAmount(String TotalAmount) {
		TotalAmount = TotalAmount;
	}

	/**
	 * @return the SettlementDate
	 */
	public Date getSettlementDate() {
		return SettlementDate;
	}

	/**
	 * @param SettlementDate the SettlementDate to set
	 */
	public void setSettlementDate(Date SettlementDate) {
		SettlementDate = SettlementDate;
	}

	/**
	 * @return the ExpiryDate
	 */
	public Date getExpiryDate() {
		return ExpiryDate;
	}

	/**
	 * @param ExpiryDate the ExpiryDate to set
	 */
	public void setExpiryDate(Date ExpiryDate) {
		ExpiryDate = ExpiryDate;
	}

	/**
	 * @return the TraderID
	 */
	public Integer getTraderID() {
		return TraderID;
	}

	/**
	 * @param TraderID the TraderID to set
	 */
	public void setTraderID(Integer TraderID) {
		TraderID = TraderID;
	}

	public String getBalance() {
		return Balance;
	}

	public void setBalance(String Balance) {
		Balance = Balance;
	}

}