package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class TraderViewController {

	@FXML
	private Button checkStatus;

	@FXML
	private Button logout;
	
	@FXML
	private Button trading;

	@FXML
	void OnStatusCheck(ActionEvent event) throws Exception {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ViewTrading.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			//Stage stage = new Stage();
			Main.stage.setScene(new Scene(root1));
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void OnLogoutClick(ActionEvent event) throws Exception {

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
	public void OnTradeBooking(ActionEvent event) throws Exception {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/TradingView.fxml"));
			
			Parent root1 = (Parent) fxmlLoader.load();
			Scene scene = new Scene(root1);
			scene.getStylesheets().add(getClass().getResource("/application/firstpage.css").toExternalForm());
			
			Main.stage.setScene(scene);
			Main.stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

