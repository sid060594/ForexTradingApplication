package controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;
import daoConnection.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class LoginController extends Controller {

	@FXML
	private TextField InvUser;

	@FXML
	private PasswordField InvPass;

	@FXML
	private Button InvestorLoginBack;

	@FXML
	private Button InvestorLoginButton;

//	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public LoginController() {
//		connection = DBConnection.connect();
	}

	@FXML
	void OnAdminLogin(ActionEvent event) throws Exception {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AdminLogin.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			//Stage stage = new Stage();
			Main.stage.setScene(new Scene(root1));
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void onExistingInvestorClick(ActionEvent event) throws Exception {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginScene.fxml"));
			Parent root2 = (Parent) fxmlLoader.load();
			Main.stage.setScene(new Scene(root2));
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void onNewInvestorClick(ActionEvent event) throws Exception {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UserRegistration.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Main.stage.setScene(new Scene(root1));
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void OnInvCancelClick(ActionEvent event) {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginScene.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			//Stage stage = new Stage();
			Main.stage.setScene(new Scene(root1));
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void OnManagerLogin(ActionEvent event)  {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/TradingManagerLogin.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			//Stage stage = new Stage();
			Main.stage.setScene(new Scene(root1));
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@FXML
	void OnInvLoginClick(ActionEvent event) throws SQLException, IOException {
		MessageDigest md = null;
 		try {
 			md = MessageDigest.getInstance("MD5");
 		} catch (NoSuchAlgorithmException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}

 		synchronized (md) {

 			md.reset();
 			String InvestorUserName = InvUser.getText();
 			String InvestorPassword = InvPass.getText();
         
         byte[] hash = md.digest(InvestorPassword.getBytes("CP1252"));

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < hash.length; ++i) {
				sb.append(Integer.toHexString((hash[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
			}

			InvestorPassword = sb.toString();
		

		String sql = "SELECT * FROM trader WHERE email = ? and password = ?";
		
		if (InvestorUserName.equals("") || InvestorPassword.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Input");
			alert.setHeaderText("Please input all the fields");
			alert.showAndWait();

		} else {
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, InvestorUserName);
				preparedStatement.setString(2, InvestorPassword);
				
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					Parent root1 = FXMLLoader.load(getClass().getResource("/view/Home.fxml"));
					Main.stage.setScene(new Scene(root1));
					Main.stage.setTitle("Main");
					Main.stage.show();
					preparedStatement.close();
					connection.close();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Wrong Input");
					alert.setHeaderText("Invalid User Name or Password");
					// alert.setContentText("Error Message");
					alert.showAndWait();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	}
	
	@FXML
    private void handleRetrieveAccount(ActionEvent event) {
        
    }
	
	 
}
