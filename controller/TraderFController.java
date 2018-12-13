package controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import com.tunyk.currencyconverter.BankUaCom;
import com.tunyk.currencyconverter.api.Currency;
import com.tunyk.currencyconverter.api.CurrencyConverter;
import com.tunyk.currencyconverter.api.CurrencyConverterException;

import daoConnection.CounterpartyDao;
import daoConnection.TradeDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.Product;
import model.Trade;

public class TraderFController extends Controller implements Initializable {
	
	ObservableList<Currency> ccyData = null;
	
//	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

    @FXML
    private TextField amount;

    @FXML
    private TextField fxRate;

    @FXML
    private Button SaveButton;
    
    @FXML
    private Button loadButton;

    @FXML
    private DatePicker settlementDate;

    @FXML
    private DatePicker tradeDate;

    @FXML
    private ComboBox<Currency> currencyList_S;

    @FXML
    private DatePicker expiryDate;

    @FXML
    private TextField NearestAmt;

    @FXML
    private ComboBox<String> counterPartyList;

    @FXML
    private Button SaveAsNewButton;

    @FXML
    private TextField tradingID;

    @FXML
    private ComboBox<Currency> currencyList_B;

    @FXML
    private TextField traderName;
    
    @FXML
    private TextField tradeStatus;
    
    @FXML
    private Button Cancel;
    
    Currency buyCcy;
    Currency sellCcy;
    String counterparty;
    
    TradeDao tradeDao;
    CounterpartyDao counterpartyDao;
    
    public TraderFController() {
//		connection = DBConnection.connect();
	}
       

    @FXML
    void OnCurrencyList_B (ActionEvent event) {
    	buyCcy = currencyList_B.getSelectionModel().getSelectedItem();
    	if(sellCcy !=null) {
    		displayFXRate();
    	}
    }
    
    private void displayFXRate() {
    	CurrencyConverter currencyConverter;
		try {
			currencyConverter = new BankUaCom(buyCcy, sellCcy);
			 String fxRate = Float.toString(currencyConverter.convertCurrency(1f));
			 
			 this.fxRate.setText(fxRate);
			 
		} catch (CurrencyConverterException e) {
			displayTraditionalFXRate();
		}
    }
    
    private void displayTraditionalFXRate() {
    	
    }


	@FXML
    void onEnterAmount(ActionEvent event) {
    	
    }


	@FXML
    void OnCurrencyList_S (ActionEvent event) {
    	sellCcy = currencyList_S.getSelectionModel().getSelectedItem();
    	if(buyCcy != null) {
    		displayFXRate();
    	}
    }

    @FXML
    void OnSaveAsNew(ActionEvent event) {
    	Product product = new Product();
    	Trade trade = new Trade(product);
    	populateFieldsFromScreen(product, trade);
    	
		int tradeId = tradeDao.save(trade);
		this.tradingID.setText(String.valueOf(tradeId));
		this.tradeStatus.setText("NEW");
    }

    private void populateFieldsFromScreen(Product product, Trade trade) {
    	product.setBuyCcy(this.buyCcy.toString());
    	product.setSellCcy(this.sellCcy.toString());
    	product.setQuantity(Double.valueOf(this.amount.getText()));
    	product.setSettlementAmount(Double.valueOf(this.NearestAmt.getText()));
    	product.setFxRate(Double.valueOf(this.fxRate.getText()));
    	
    	LocalDate ld = this.settlementDate.getValue();
    	Calendar c =  Calendar.getInstance();
    	c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
    	Date date = c.getTime();
    	product.setSettlementDate(date);
    	
    	ld = this.expiryDate.getValue();
    	c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
    	date = c.getTime();
    	product.setExpiryDate(date);
    	
    	
		trade.setTraderName(this.traderName.getText());
		
		ld = this.tradeDate.getValue();
    	c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
    	date = c.getTime();
		trade.setTradeDate(date);
		trade.setCounterparty(this.counterPartyList.getSelectionModel().getSelectedItem());
    }


	@FXML
    void OnSaveClick(ActionEvent event) {
    	
    	Trade trade = tradeDao.getTrade(Integer.parseInt(this.tradingID.getText()));
    	Product product = trade.getProduct();
    	populateFieldsFromScreen(product, trade);
    	
		tradeDao.update(trade);
		this.tradeStatus.setText("AMENDED");
    }
    
    @FXML
    void OnLoad(ActionEvent event) {
    	Trade trade = tradeDao.getTrade(Integer.parseInt(this.tradingID.getText()));
    	Product product = trade.getProduct();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
    	
    	this.currencyList_B.setValue(Currency.valueOf(product.getBuyCcy()));
    	this.currencyList_S.setValue(Currency.valueOf(product.getSellCcy()));
    	this.counterPartyList.setValue(trade.getCounterparty());
    	this.fxRate.setText(String.valueOf(product.getFxRate()));
    	this.amount.setText(String.valueOf(product.getQuantity()));
    	this.NearestAmt.setText(String.valueOf(product.getSettlementAmount()));
    	
    	Date input = product.getSettlementDate();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(input);
    	LocalDate date = LocalDate.of(cal.get(Calendar.YEAR),
    	        cal.get(Calendar.MONTH) + 1,
    	        cal.get(Calendar.DAY_OF_MONTH));
    	this.settlementDate.setValue(date);
    	
    	input = product.getExpiryDate();
    	cal = Calendar.getInstance();
    	cal.setTime(input);
    	date = LocalDate.of(cal.get(Calendar.YEAR),
    	        cal.get(Calendar.MONTH) + 1,
    	        cal.get(Calendar.DAY_OF_MONTH));
    	this.expiryDate.setValue(date);
    	
    	input = trade.getTradeDate();
    	cal = Calendar.getInstance();
    	cal.setTime(input);
    	date = LocalDate.of(cal.get(Calendar.YEAR),
    	        cal.get(Calendar.MONTH) + 1,
    	        cal.get(Calendar.DAY_OF_MONTH));
    	this.tradeDate.setValue(date);
    	
    	this.traderName.setText(trade.getTraderName());
    	this.tradeStatus.setText(trade.getStatus());
    	this.tradingID.setText(String.valueOf(trade.getTradeId()));
    }

    @FXML
    void onEnter(ActionEvent event) {
    	Trade trade = tradeDao.getTrade(Integer.parseInt(this.tradingID.getText()));
    	Product product = trade.getProduct();
    	
    	this.currencyList_B.setValue(Currency.valueOf(product.getBuyCcy()));
    	this.currencyList_S.setValue(Currency.valueOf(product.getSellCcy()));
    	
    }

    @FXML
    void onSave(ActionEvent event) {
    	
    	Trade trade = tradeDao.getTrade(Integer.parseInt(this.tradingID.getText()));
    	Product product = trade.getProduct();
    	populateFieldsFromScreen(product, trade);
    	
		tradeDao.update(trade);
		this.tradeStatus.setText("AMENDED");
    }

    @FXML
    void OnTradeSubmit(ActionEvent event) {

    }

    @FXML
    void OnSelectCounterParty(ActionEvent event) {
    	this.counterparty = this.counterPartyList.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    void OnCancel(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tradeDao = new TradeDao();
		counterpartyDao = new CounterpartyDao();
		
		counterPartyList.setItems(FXCollections.observableArrayList(counterpartyDao.getCounterpartyList()));
		currencyList_B.setItems(FXCollections.observableArrayList(Currency.values()));
		currencyList_S.setItems(FXCollections.observableArrayList(Currency.values()));
		amount.textProperty().addListener(new ChangeListener<String>() {
			
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {

		        System.out.println(" Text Changed to  " + newValue + ")\n");
		        setValue(newValue);
		        /*double amt = Double.valueOf(amount.getText());
		    	amt *= Double.valueOf(this.fxRate.getText());
		    	
		    	this.nearestAmt.setText(Double.toString(amt));*/
		    }

			private void setValue(String newValue) {
				
			}
		});
	}
}
