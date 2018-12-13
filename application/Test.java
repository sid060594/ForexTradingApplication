package application;

import com.tunyk.currencyconverter.BankUaCom;
import com.tunyk.currencyconverter.api.Currency;
import com.tunyk.currencyconverter.api.CurrencyConverter;
import com.tunyk.currencyconverter.api.CurrencyConverterException;

public class Test {

	public static void main(String[] args) {
		CurrencyConverter currencyConverter;
		try {
			currencyConverter = new BankUaCom(Currency.AZM, Currency.JPY);
			 Float inEuro = currencyConverter.convertCurrency(1f);
			   System.out.println("1 USD = "+inEuro+" Euro");
		} catch (CurrencyConverterException e) {}
		  
	}


}
