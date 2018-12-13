package daoConnection;

import java.sql.*;

public class DBConnector {
	
	private static DBConnector dbConn;
	private Connection conn;
	
	private DBConnector() {
		conn = connect();
	}
	
	public static DBConnector getInstance() {
		if(dbConn == null) {
			dbConn = new DBConnector();
		} 
		return dbConn;
	}
	
	private Connection connect() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sys?", "root",
//					"root");
			conn = DriverManager
					.getConnection("jdbc:mysql://www.papademas.net:3306/510labs?autoReconnect=true&useSSL=false", "db510", "510");
			System.out.println("Connection Established Successfully...");
			return conn;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public Connection getConnection() throws SQLException, ClassNotFoundException{
        if(conn !=null && !conn.isClosed()) {
        	return conn;
        } else {
        	return connect();
        }
    }
	
	
	/**
	* Overriding the close connection to implement user defined close connection actions.
	 */
	      public void close() throws Exception {
	           /**********MAKE SURE THE CONNECTION IS CLOSED WHEN NOT IN USE*************/
	           try{
	        	   if(conn!=null) {
	        		   this.conn.close();  
	        	   }
	           }
	           catch(SQLException e){
	                System.out.println("Closure of Database connection failed :" + e);
	           }
	      }
	public DBConnector getDbConn() {
		return dbConn;
	}      
	      
}
