package controller;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import daoConnection.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdminLoginController extends Controller implements Initializable{

    @FXML
    private TextField adminID;

    @FXML
    private Label Title;

    @FXML
    private Button Login;
    
    @FXML
    private PasswordField a_password;
    
    @FXML
	private Button backclick;
    
    @FXML Button homeclick;
    
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
    void backtohome(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginScene.fxml"));
			Parent root2 = (Parent) fxmlLoader.load();
			Main.stage.setScene(new Scene(root2));
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
    
//    Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public AdminLoginController() {
//		connection = DBConnection.connect();
	}

    @FXML
	void adminController(ActionEvent event) throws SQLException, IOException 
    {

		String ID = adminID.getText().toString();
		String password = a_password.getText().toString();

		String sql = "SELECT * FROM Admin WHERE admin_id = ? and password = ?";
		if (adminID.equals("") || a_password.equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Input");
			alert.setHeaderText("Please input all the fields");
			alert.showAndWait();

		} else {
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, ID);
				preparedStatement.setString(2, password);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					Parent root = FXMLLoader.load(getClass().getResource("/view/AdminHome.fxml"));
					//Stage stage = new Stage();
					Main.stage.setScene(new Scene(root));
					Main.stage.setTitle("Main");
					Main.stage.show();

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

