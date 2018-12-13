package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminCurrencyController extends Controller implements Initializable{

	ObservableList<Object> data = FXCollections.observableArrayList();

	@FXML
	private TextField CurrencyCode;

	@FXML
	private TextField Country;
	@FXML
	private TextField CurrencyValue;

	@FXML
	private Button AddCurrency;
	
	
    @FXML
    private Label labelCode;

    @FXML
    private Label labelcountry;
    
    @FXML
    private Label labelvalue;
    
    @FXML
	private ComboBox currencyList;
    
	@FXML
	private TextField updateCurrencyValue;
	@FXML
	private TextField newCurrencyValue;
	
    @FXML
    private Label labelupdatecurrencyvalue;
    
    @FXML
    private Label labelupdatencurrencyvalue;


    @FXML
    private Button AdminHomeButton;

//    Connection connection = null;
	PreparedStatement preparedStatement = null;
	Statement st = null;
	ResultSet rs = null;

	public AdminCurrencyController() {
//		connection = DBConnection.connect();
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//void initialize() throws SQLException {
		
			
		try {
			rs = connection.createStatement().executeQuery("SELECT currencyCode FROM currencyList");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next())
			{
			data.add((rs.getString(1)) );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		currencyList.setItems(data);
		
		try {
			rs = connection.createStatement().executeQuery("SELECT currencyvalue FROM currencyList where currencyCode='"+currencyList.getValue()+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next())
			{
				updateCurrencyValue.setText(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	@FXML
void OnAddCurrencyClick(ActionEvent event) {
		
		String CurrencyCode = this.CurrencyCode.getText().toString();
		String Country = this.Country.getText().toString();
		String CurrencyValue = this.CurrencyValue.getText().toString();
	//	String regex = "\\d+";
		
		

		/****** Check if first name is left blank or empty *******/
		if (CurrencyCode == null || CurrencyCode.trim().equals("")) {
			labelCode.setText("Stock Name cannot be empty");
			return;
		}

		/****** Check if stock base price is left blank or empty *******/
		if (Country == null || Country.trim().equals("")) {
			labelcountry.setText("Stock base price cannot be empty");
			labelCode.setText("");
			labelvalue.setText("");
			return;
		}

		/****** Check if user name is left blank or empty *******/
		if (CurrencyValue == null || CurrencyValue.trim().equals("")) {
			labelvalue.setText("User Name cannot be empty");
			labelCode.setText("");
			labelcountry.setText("");
			
			// lblStatus.setStyle("-fx-text-fill:red");
			return;
		}

	

		try {
			
			PreparedStatement ps = connection
					.prepareStatement("insert into currencyList (currencyCode,Country,currencyValue) values(?,?,?)");
			
			ps.setString(1, CurrencyCode);
			ps.setString(2, Country);
			ps.setString(3, CurrencyValue);
			
			ps.executeUpdate();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Record Inserted");
			alert.setHeaderText("Record Inserted");
			alert.showAndWait();
			st.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("There is an issue in inserting into the backend during signup");
			return;
		}
	}

		
		
	
	@FXML
	void onAdminHomeClick(ActionEvent event) {
		
		try {
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AdminHome.fxml"));
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


	@FXML
	void OnCurrencyUpdate(ActionEvent event) {
		
		currencyList.getValue() ;
		
		String newCurrencyValue = this.newCurrencyValue.getText().toString();
	//	String regex = "\\d+";
		
		

		/****** Check if first name is left blank or empty *******/
		if (newCurrencyValue == null || newCurrencyValue.equals("")) {
			labelupdatencurrencyvalue.setText("New Currency value cannot be empty");
			return;
		}

		try {
			
			PreparedStatement ps = connection
					.prepareStatement("update currencyList set currencyValue=? where currencycode ='" +currencyList.getValue()+"'");
			
			ps.setString(1, newCurrencyValue);
					
			ps.executeUpdate();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Record Updated");
			alert.setHeaderText("Record Updated");
			alert.showAndWait();
	
			st.close();
			connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("There is an issue in updating into the backend during currency change");
			return;
		}
	}
}
