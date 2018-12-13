package model;

public class ViewCurrencyList {
	
	String CurrencyCode;
	 String CurrencyValue;
	 String CountryCode;
	/**
	 * @param currencyCode
	 * @param currencyValue
	 * @param countryCode
	 */
	public ViewCurrencyList(String currencyCode, String currencyValue, String countryCode) {
		super();
		CurrencyCode = currencyCode;
		CurrencyValue = currencyValue;
		CountryCode = countryCode;
	}
	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return CurrencyCode;
	}
	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}
	/**
	 * @return the currencyValue
	 */
	public String getCurrencyValue() {
		return CurrencyValue;
	}
	/**
	 * @param currencyValue the currencyValue to set
	 */
	public void setCurrencyValue(String currencyValue) {
		CurrencyValue = currencyValue;
	}
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return CountryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

}
