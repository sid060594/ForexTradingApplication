package controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class AdminHomeController {
    @FXML
    private Button AdminSubmit;

    @FXML
    private RadioButton addCurrency;

    @FXML
    private RadioButton updateCurrency;

    @FXML
    private RadioButton removeCurrency;
    

    @FXML
    private RadioButton viewcurrencylist;
  
    @FXML
    private Button AdminBack;
    
    @FXML
	private ToggleGroup AdminToggleG;
    
    public void toggleGroupAssign() {
    	AdminToggleG = new ToggleGroup();
    	addCurrency.setToggleGroup(AdminToggleG);
    	updateCurrency.setToggleGroup(AdminToggleG);
    	removeCurrency.setToggleGroup(AdminToggleG);
    	
	}

    @FXML
    void homeclick(ActionEvent event) {
    	
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
    void OnAdminSubmit(ActionEvent event) {
    	toggleGroupAssign();
		if (addCurrency.isSelected()) {
			addCurrency.requestFocus();
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddCurrency.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Main.stage.setScene(new Scene(root1));
				Main.stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (updateCurrency.isSelected()) {
			try {
				updateCurrency.requestFocus();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UpdateCurrency.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Main.stage.setScene(new Scene(root1));
				Main.stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (removeCurrency.isSelected()) {
			try {
				removeCurrency.requestFocus();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/RemoveCurrency.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Main.stage.setScene(new Scene(root1));
				Main.stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (viewcurrencylist.isSelected()) {
			try {
				viewcurrencylist.requestFocus();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ViewCurrencyList.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Main.stage.setScene(new Scene(root1));
				Main.stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
		
    }


}
