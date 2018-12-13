package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Main;
import daoConnection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class TraderFController_backup extends Controller {
	
	ObservableList<String> bsList=FXCollections.observableArrayList("Buy","Sell");
	ObservableList<Object> data = FXCollections.observableArrayList();
	ObservableList<Object> settle = FXCollections.observableArrayList();
	@FXML
	private ComboBox buy_sell;
@FXML 
private ComboBox settcurrencyList;
	@FXML
	private ComboBox currencyList;
	@FXML
	private Button InvestorSubmit;

	@FXML
	private ToggleGroup InvFuncToggleGroup;

	@FXML
	private RadioButton ViewPortfolioRadio;

	@FXML
	private RadioButton ViewStocksRadio;

	@FXML
	private Button InvestorFunctionsBack;
//	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public TraderFController_backup() {
//		connection = DBConnection.connect();
	}

	@FXML
	void initialize() throws SQLException {
		buy_sell.setItems(bsList);
		buy_sell.setValue("Buy");
				
		ResultSet rs = connection.createStatement().executeQuery("SELECT currencyCode,settleCurrencyCode FROM currencyList");
		while (rs.next())
		{
		data.add(rs.getString(1)) ;
		settle.add(rs.getString(2)) ;
		}
		
		currencyList.setItems(data);
		settcurrencyList.setItems(settle);
		
		rs.close();
		connection.close();
	}
	
	@FXML
void OnTradeSubmit(ActionEvent event) {
		
		try {
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginScene.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Main.stage.setScene(new Scene(root1));
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@FXML
	void OnCurrencyList(ActionEvent event) {
		
		try {
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginScene.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Main.stage.setScene(new Scene(root1));
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@FXML
void onEnter(ActionEvent event) {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginScene.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Main.stage.setScene(new Scene(root1));
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
