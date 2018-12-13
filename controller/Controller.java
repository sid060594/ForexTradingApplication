package controller;

import java.sql.Connection;
import java.sql.SQLException;

import daoConnection.DBConnector;

public class Controller {
	
	Connection connection = null;
	
	public Controller() {
		DBConnector dbConnection = DBConnector.getInstance();
		try {
			connection = dbConnection.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
