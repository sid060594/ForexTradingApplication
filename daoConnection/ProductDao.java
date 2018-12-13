package daoConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.mysql.jdbc.Statement;

import model.Product;

public class ProductDao {
	
	Connection connection;
	
	public ProductDao() {
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
	
	/**
	 * saves the Trade into Trade table which internally saves the product.
	 * 
	 * @param product
	 * @return
	 */
	public int save (Product product) {
		PreparedStatement preparedStmt =null;
		ResultSet rs = null;
		try {
			
			preparedStmt = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, product.getBuyCcy());
			preparedStmt.setString(2, product.getSellCcy());
			preparedStmt.setFloat(3, (float)product.getQuantity());
			preparedStmt.setFloat(4, (float)product.getSettlementAmount());
			preparedStmt.setFloat(5, (float)product.getFxRate());
			preparedStmt.setTimestamp(6, new Timestamp(product.getSettlementDate().getTime()));
			preparedStmt.setTimestamp(7, new Timestamp(product.getExpiryDate().getTime()));
			
			preparedStmt.executeUpdate();
			System.out.println("Trade Saved Successfully");
			
			rs = preparedStmt.getGeneratedKeys();
			if(rs!=null) {
				while (rs.next()) {
					return rs.getInt(1);
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStmt,rs);
		}
		
		return 0;
		
	}
	
	private void close(PreparedStatement preparedStmt, ResultSet rs) {
		
		if(preparedStmt!=null) {
			try {
				preparedStmt.close();
			} catch (SQLException e) {
				System.out.println("Error while closing PreparedStatement");
			}
		}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Error while closing ResultSet");
				}
		}
		
	}

	/**
	 * updates the existing product in the database.
	 * 
	 * @param product
	 */
	public void update (Product product) {
		PreparedStatement preparedStmt =null;
		ResultSet rs = null;
		try {
			
			preparedStmt = connection.prepareStatement(UPDATE_SQL);
			preparedStmt.setString(1, product.getBuyCcy());
			preparedStmt.setString(2, product.getSellCcy());
			preparedStmt.setFloat(3, (float)product.getQuantity());
			preparedStmt.setFloat(4, (float)product.getSettlementAmount());
			preparedStmt.setFloat(5, (float)product.getFxRate());
			preparedStmt.setTimestamp(6, new Timestamp(product.getSettlementDate().getTime()));
			preparedStmt.setTimestamp(7, new Timestamp(product.getExpiryDate().getTime()));
			
			preparedStmt.executeUpdate();
			System.out.println("Product Updated Successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStmt,rs);
		}
		
	}
	
	private final String INSERT_SQL = "INSERT INTO 510labs.dproductfx (buycurrency, sellcurrency, quantity, settlementamount, fxrate, settlementdate, expirydate)"
			+ "VALUES (?,?,?,?,?,?,?)";
	
	private final String UPDATE_SQL = "update 510labs.dproductfx set buycurrency=?, sellcurrency=?, quantity=?, settlementamount=?, fxrate=?, settlementdate=?, expirydate=? where productID=?";
	

}
