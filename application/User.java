package application;
/**
 * This interface interacts with trader, contains User's details,
 * provides trade booking GUI to the trader to book FXtrade,
 * also admin will be able to add different users.
 *
 * @author SIDDHARTH SINGH, POONAM BHATORE
 * @date 11/4/2017
 * @Lab_number – ITMD 510 PROJECT PHASE - 1
 * @CWID - A20401776, A20403612
 */

public interface User {

	String name=null;
	String id=null;
	String userRole=null;
	String password=null;
	
	//method declaration to display trader name and role
	void getRole();
	void getName();
}
