package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import model.ViewCurrencyList;

public class ViewCurrencyListController extends Controller implements Initializable {

	@FXML
	private TableView<ViewCurrencyList> ViewCurrencyListView;

	@FXML
	private TableColumn<?, ?> iCurrencyCode;

	@FXML
	private TableColumn<?, ?> iCurrencyValue;

	@FXML
	private TableColumn<?, ?> iCountryCode;

	@FXML
	private ObservableList<ViewCurrencyList> data;
	
	@FXML
    private Button CurrencyListBack;

//	Connection connection = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public ViewCurrencyListController() {
//		connection = DBConnection.connect();
	}

	public void initialize(URL location, ResourceBundle resources) {
		iCurrencyCode.setCellValueFactory(new PropertyValueFactory<>("currencyCode"));
		iCurrencyValue.setCellValueFactory(new PropertyValueFactory<>("currencyValue"));
		iCountryCode.setCellValueFactory(new PropertyValueFactory<>("countryCode"));

		ObservableList<ViewCurrencyList> data = FXCollections.observableArrayList();

		try {
			{
				String sql = "select currencyCode,Country,currencyValue from CurrencyList";
				pst = connection.prepareStatement(sql);
				
				
				pst = connection.prepareStatement(sql);
				rs = pst.executeQuery();
				
				
                System.out.println("query worked");
				while (rs.next()) {
					data.add(new ViewCurrencyList(rs.getString(1), rs.getString(2), rs.getString(3)));
					ViewCurrencyListView.setItems(data);
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@FXML
    void onCurrencyListBackClick(ActionEvent event) {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AdminHome.fxml"));
			Parent root2 = (Parent) fxmlLoader.load();
			Main.stage.setScene(new Scene(root2));
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

}
