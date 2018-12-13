package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import daoConnection.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ViewCurrencyList;
import model.ViewTrading;

public class RemoveCurrencyController extends Controller implements Initializable {
@FXML
private TableView<ViewCurrencyList> ViewCurrencyTableView;

@FXML
private TableColumn<?, ?> removeCurrencyCode;

@FXML
private TableColumn<?, ?> removeCurrencyValue;

@FXML
private TableColumn<?, ?> removeCurrencyCountry;

@FXML
private Button RemoveCurrency;

@FXML
private TextField searchCurrency;

@FXML
private Button RefreshCurrencyList;


@FXML
private Button AdminHomeButton;

@FXML
void onAdminHomeClick(ActionEvent event) {
	
	try {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AdminHome.fxml"));
		Parent root2 = (Parent) fxmlLoader.load();
		Main.stage.setScene(new Scene(root2));
		Main.stage.show();
	} catch (Exception e) {
		e.printStackTrace();
	}

}

@FXML
private ObservableList<ViewTrading> data;

//Connection connection = null;
PreparedStatement pst = null;
ResultSet rs = null;

public RemoveCurrencyController() {
//	connection = DBConnection.connect();
}

@FXML
void onRefreshButtonClick(ActionEvent event) {

	ObservableList<ViewCurrencyList> data = FXCollections.observableArrayList();

	try {
		{
			String sql = "select currencyCode,Country,currencyValue from currencylist";
			pst = connection.prepareStatement(sql);
			// pst.setString(1, uName);
//			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				data.add(new ViewCurrencyList(rs.getString(1), rs.getString(2), rs.getString(3)));
				ViewCurrencyTableView.setItems(data);

			}
		}

	} catch (Exception e) {
		e.printStackTrace();
	}

}

	@FXML
	void onRemoveCurrencyClick(ActionEvent event) throws SQLException {
		

//		DBConnection db = new DBConnection();
		String currency_Code = searchCurrency.getText().toString();

			String sql = "Delete from  currencyList  where currencycode = ?";
			try {
				PreparedStatement pst1 = connection.prepareStatement(sql);
				pst1.setString(1, currency_Code);

				int count = pst1.executeUpdate();
				if (count > 0) {

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Currency Deletion");
					alert.setHeaderText("Currency deleted");
					alert.showAndWait();
				
				}

				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Deletion failed");
					alert.setHeaderText("Currency Deletion failed");
					alert.showAndWait();

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}
	

	public void initialize(URL location, ResourceBundle resources) {
		removeCurrencyCode.setCellValueFactory(new PropertyValueFactory<>("currencyCode"));
		removeCurrencyValue.setCellValueFactory(new PropertyValueFactory<>("currencyValue"));
		removeCurrencyCountry.setCellValueFactory(new PropertyValueFactory<>("countryCode"));
		
		ObservableList<ViewCurrencyList> data = FXCollections.observableArrayList();

		try {
			{
				String sql = "select currencyCode,Country,currencyValue from currencylist";
				pst = connection.prepareStatement(sql);
				// pst.setString(1, uName);
				pst = connection.prepareStatement(sql);
				rs = pst.executeQuery();

				while (rs.next()) {
					data.add(new ViewCurrencyList(rs.getString(1), rs.getString(2), rs.getString(3)));
					ViewCurrencyTableView.setItems(data);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
