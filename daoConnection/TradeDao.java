package daoConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.mysql.jdbc.Statement;

import model.Product;
import model.Trade;

public class TradeDao {
	
	Connection connection;
	ProductDao productDao;
	
	public TradeDao() {
		productDao = new ProductDao();
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
	 * @param trade
	 * @return
	 */
	public int save (Trade trade) {
		PreparedStatement preparedStmt =null;
		ResultSet rs = null;
		try {
			
			int productId = productDao.save(trade.getProduct());
			preparedStmt = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setInt(1, productId);
			preparedStmt.setString(2, trade.getTraderName());
			preparedStmt.setTimestamp(3, new Timestamp(trade.getTradeDate().getTime()));
			preparedStmt.setString(4, trade.getCounterparty());
			preparedStmt.setString(5, "NEW");
			
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
	 * updates the existing trade in the database.
	 * 
	 * @param trade
	 */
	public void update (Trade trade) {
		PreparedStatement preparedStmt =null;
		ResultSet rs = null;
		try {
			
			productDao.update(trade.getProduct());
			preparedStmt = connection.prepareStatement(UPDATE_SQL);
			preparedStmt.setString(1, trade.getTraderName());
			preparedStmt.setTimestamp(2, new Timestamp(trade.getTradeDate().getTime()));
			preparedStmt.setString(3, trade.getCounterparty());
			preparedStmt.setString(4, "AMENDED");
			preparedStmt.setInt(5, trade.getTradeId());
			
			preparedStmt.executeUpdate();
			System.out.println("Trade Updated Successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStmt,rs);
		}
		
	}
	
	/**
	 * Retrieves the Existing Trade from the Trade Table with the Product details.
	 * 
	 * @param tradeId
	 * @return
	 */
	public Trade getTrade (int tradeId) {
		PreparedStatement preparedStmt =null;
		ResultSet rs = null;
		try {
			
			preparedStmt = connection.prepareStatement(SELECT_SQL);
			preparedStmt.setInt(1, tradeId);
			
			rs = preparedStmt.executeQuery();
			
			if(rs!=null) {
				while (rs.next()) {
					return fetchTrade(rs);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStmt,rs);
		}
		
		return null;
		
	}
	
	private Trade fetchTrade(ResultSet rs) throws SQLException {
		
		Product product = new Product();
		
		product.setBuyCcy(rs.getString(BUY_CCY));
		product.setSellCcy(rs.getString(SELL_CCY));
		product.setProductId(rs.getInt(PRODUCT_ID));
		product.setQuantity(rs.getFloat(QUANTITY));
		product.setSettlementAmount(rs.getFloat(SETTLE_AMT));
		product.setFxRate(rs.getFloat(FX_RATE));
		product.setSettlementDate(new Date(rs.getTimestamp(SETTLE_DATE).getTime()));
		product.setExpiryDate(new Date(rs.getTimestamp(EXPIRY_DATE).getTime()));
		
		Trade trade =  new Trade(product);
		trade.setTradeId(rs.getInt(TRADE_ID));
		trade.setTraderName(rs.getString(TRADER_NAME));
		trade.setTradeDate(new Date(rs.getTimestamp(TRADE_TIMESTAMP).getTime()));
		trade.setCounterparty(rs.getString(COUNTERPARTY));
		trade.setStatus(rs.getString(STATUS));		
		
		return trade;
	}

	private final String INSERT_SQL = "INSERT INTO 510labs.htrade (productID, tradername, tradetimestamp, updatedtradetimestamp, counterparty, status)"
			+ "VALUES (?,?,?,NOW(),?,?)";
	
	private final String  SELECT_SQL = "SELECT * FROM 510labs.htrade trade,510labs.dproductfx prd  WHERE tradeID=? and prd.productID=trade.productID;";
	
	private final String UPDATE_SQL = "UPDATE 510labs.htrade SET tradername=?, tradetimestamp=?, updatedtradetimestamp=NOW(), counterparty=? , status=? WHERE tradeID = ?";
	
	private final String PRODUCT_ID = "productID";
	
	private final String BUY_CCY = "buycurrency";
	
	private final String SELL_CCY = "sellcurrency";
	
	private final String QUANTITY = "quantity";
	
	private final String SETTLE_AMT = "settlementamount";
	
	private final String FX_RATE = "fxrate";
	
	private final String SETTLE_DATE = "settlementdate";
	
	private final String EXPIRY_DATE = "expirydate";
	
	private final String TRADE_ID = "tradeID";
	
	private final String TRADER_NAME = "tradername";
	
	private final String TRADE_TIMESTAMP = "tradetimestamp";
		
	private final String COUNTERPARTY = "counterparty";
	
	private final String STATUS = "status";

}
