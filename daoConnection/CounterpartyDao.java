package daoConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CounterpartyDao {
	
	Connection connection;
	public CounterpartyDao() {
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

	public Vector<String> getCounterpartyList() {
		PreparedStatement preparedStmt =null;
		ResultSet rs = null;
		
		try {
			
			preparedStmt = connection.prepareStatement(SELECT_SQL);
			
			rs = preparedStmt.executeQuery();
			
			if(rs!=null) {
				return fetchBankNames(rs);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStmt,rs);
		}
		
		return null;
		
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

	private Vector<String> fetchBankNames(ResultSet rs) throws SQLException {
		Vector<String> counterpartyList = new Vector<>();
		StringBuilder name = null;
		while (rs.next()) {
			name = new StringBuilder().append(rs.getString(SHORT_NAME)).append(" [").append(rs.getString(FULL_NAME)).append("]");
			counterpartyList.addElement(name.toString());
		}
		return counterpartyList;
	}

	private final String  SELECT_SQL = "SELECT * FROM 510labs.dcounterparty";
	
	private final String SHORT_NAME = "cShortName";
	
	private final String FULL_NAME = "cFullName";

}
