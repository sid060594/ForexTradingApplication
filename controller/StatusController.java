package controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import daoConnection.DBConnector;

public class StatusController extends Controller implements Initializable {

	@FXML
	TableView statusTableView;
	
	private ObservableList<ObservableList> data;
	public Stage dialogStage;
	@FXML
	Button Close;
	
//	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public StatusController() {
//		connection = DBConnection.connect();
	}
	@FXML
	public void CloseController()
	{
		dialogStage.fireEvent(new WindowEvent(dialogStage,
				 WindowEvent.WINDOW_CLOSE_REQUEST));
	}
	public void start(Stage primaryStage) {
		try {
			//Create a loader for the UI components
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CheckStatus.fxml"));
			//Inflate the view using the loader
            AnchorPane root = (AnchorPane) loader.load();

            Scene scene = new Scene(root);
            //Set the scene to the stage
            primaryStage.setScene(scene);
            //Get the controller instance from the loader
          StatusController controller = loader.getController();
            //Set the parent stage in the controller
            controller.setDialogStage(primaryStage);
            
            //Show the view
            primaryStage.show();
    		
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			e.printStackTrace();
		}	
}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ResultSet rs;
		try {
			rs = connection.createStatement().executeQuery("SELECT currencyCode FROM currencyList");
		
		data = FXCollections.observableArrayList();

		try {
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				// We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j).toString());
							}
						});

				statusTableView.getColumns().addAll(col);
				System.out.println("Column [" + i + "] ");
			}

		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
			
	}
}
