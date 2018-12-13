package controller;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert.AlertType;

public class ManagerSetLimitController extends Controller{

	ObservableList<Object> data = FXCollections.observableArrayList();
	@FXML
	private ComboBox tradingID;

	@FXML
	private TextField orderLimit;
	@FXML
	private Button ManagerSubmit;
	@FXML
	private Button backclick;

	@FXML
	private Label labelorder;
	
	@FXML
	private Button InvestorFunctionsBack;
//	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public ManagerSetLimitController() {
//		connection = DBConnection.connect();
	}

	@FXML
	void initialize() throws SQLException {

		try {
			resultSet = connection.createStatement().executeQuery("SELECT traderID FROM trader");
		} catch (SQLException e) {
			System.out.println(e);
		}
		while (resultSet.next()) {
			data.add((resultSet.getString(1)));
		}

		tradingID.setItems(data);
		resultSet.close();
		connection.close();
	}
	@FXML
	void Onbackclick(ActionEvent event) {
		
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
void OnTradeSubmit(ActionEvent event) {
		
		
			tradingID.getValue() ;
			
			String traderlimit = this.orderLimit.getText().toString();
		//	String regex = "\\d+";
			
			/****** Check if first name is left blank or empty *******/
			if (orderLimit == null || orderLimit.equals("")) {
				labelorder.setText("New Currency value cannot be empty");
				return;
			}

			try {
				
				PreparedStatement ps = connection
						.prepareStatement("update tradingManager set orderLimit=? where traderID ='" +tradingID.getValue()+"'");
				
				ps.setString(1, traderlimit);
						
				ps.executeUpdate();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Updated orderlimit");
				alert.setHeaderText("Updated");
				alert.showAndWait();
		
				ps.close();
				connection.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("There is an issue in updating into the backend during currency change");
				return;
			}
		}
	}
