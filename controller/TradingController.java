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
import model.ViewTrading;

public class TradingController extends Controller implements Initializable{

	@FXML
	private TableView<ViewTrading> tableViewTrading;

	@FXML
	private TableColumn<?, ?> iAction;

	@FXML
	private TableColumn<?, ?> iCurrencyCode;

	@FXML
	private TableColumn<?, ?> iSettlementCurrencyCode;

	@FXML
	private TableColumn<?, ?> iQuantity;

	@FXML
	private TableColumn<?, ?> iTotalAmount;
	
	@FXML
	private TableColumn<?, ?> iSettlementDate;

	@FXML
	private TableColumn<?, ?> iExpiryDate;

	@FXML
	private TableColumn<?, ?> iTraderID;
	
	@FXML
	private TableColumn<?, ?> iBalance;

	@FXML
	private ObservableList<ViewTrading> data;
    
    @FXML
    private Button ViewTradeSummary;
    
//    Connection connection = null;
    PreparedStatement pst = null;
	ResultSet rs = null;
    
    public TradingController() {
//		connection = DBConnection.connect();
	}

    @FXML
    void homeclick(ActionEvent event) {
    	
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Home.fxml"));
    		Parent root2 = (Parent) fxmlLoader.load();
    		Main.stage.setScene(new Scene(root2));
    		Main.stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    }
    @FXML
    void onViewTradeSummary(ActionEvent event) {
    	

		ObservableList<ViewTrading> data = FXCollections.observableArrayList();
		
		try {
			{
				String sql = "select * from TradeSummary";
//				pst = connection.prepareStatement(sql);
				
				pst = connection.prepareStatement(sql);
				rs = pst.executeQuery();
				
				while (rs.next()) {
					data.add(new ViewTrading(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getDate(6), rs.getDate(7),
							rs.getString(8), rs.getString(9)));
					tableViewTrading.setItems(data);
					
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

    }

	public void initialize(URL location, ResourceBundle resources) {
		iTraderID.setCellValueFactory(new PropertyValueFactory<>("TraderID"));
		iAction.setCellValueFactory(new PropertyValueFactory<>("Action"));
		iCurrencyCode.setCellValueFactory(new PropertyValueFactory<>("CurrencyCode"));
		iSettlementCurrencyCode.setCellValueFactory(new PropertyValueFactory<>("SettlementCurrencyCode"));
		iQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));	
		iSettlementDate.setCellValueFactory(new PropertyValueFactory<>("SettlementDate"));
		iExpiryDate.setCellValueFactory(new PropertyValueFactory<>("ExpiryDate"));
		iTotalAmount.setCellValueFactory(new PropertyValueFactory<>("TotalAmount"));
		iBalance.setCellValueFactory(new PropertyValueFactory<>("Balance"));

	}

}
