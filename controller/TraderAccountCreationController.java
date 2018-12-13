package controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TraderAccountCreationController extends Controller {

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private Button signup;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField cnfpassword;

    @FXML
    private Label firstLabel;

    @FXML
    private Label secondLabel;

    @FXML
    private Label thirdLabel;

    @FXML
    private Label fourthLabel;

    @FXML
    private TextField contact;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private Label fifthLabel;

    @FXML
    private Label sixthLabel;

    @FXML
    private Label seventhLabel;
    
    @FXML
    private Button Reset;
    
    private Statement st = null;
//	DBConnection con = new DBConnection();
	
	@FXML
    void resetController(ActionEvent event) {
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginScene.fxml"));
			Parent root1 = (Parent) fxmlLoader.load();
			Main.stage.setScene(new Scene(root1));
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
	
	

    @FXML
	void Onsignup(ActionEvent event) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
    	 MessageDigest md = null;
  		try {
  			md = MessageDigest.getInstance("MD5");
  		} catch (NoSuchAlgorithmException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}

  		synchronized (md) {

  			md.reset();
  			String fName = this.fName.getText().toString();
  			String lName = this.lName.getText().toString();
  			String contact = this.contact.getText().toString();
  			String address = this.address.getText().toString();
  			String password = this.password.getText().toString();
  			
  			
          byte[] hash = md.digest(password.getBytes("CP1252"));

 			StringBuffer sb = new StringBuffer();
 			for (int i = 0; i < hash.length; ++i) {
 				sb.append(Integer.toHexString((hash[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
 			}

 			password = sb.toString();
 			
 			String email = this.email.getText().toString();
  			
  			String regex = "\\d+";
          
		
		

		/****** Check if first name is left blank or empty *******/
		if (fName == null || fName.trim().equals("") ||lName == null || lName.trim().equals("")||contact == null || contact.trim().equals("")
				||email == null || email.trim().equals("") ||address == null || address.trim().equals("") ||password == null || password.trim().equals("")
				||this.password.getText().toString() ==null ||this.password.getText().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "All fields are mandatory", "Error", JOptionPane.ERROR_MESSAGE);
			
		}

		
		
	//	else{
			try {
		
//			if(DBConnection.connect()!=null) {
				st = connection.createStatement();
				String SQLQuery = null;
				SQLQuery = "SELECT COUNT(TRADERID) FROM Trader";
				ResultSet rs = st.executeQuery(SQLQuery);
				int invID = 0;
				while (rs.next())
					invID = rs.getInt(1) + 1;
				// rs.close();
				PreparedStatement ps = connection
						.prepareStatement("insert into Trader values(?,?,?,?,?,?,?)");
				ps.setInt(1, invID);
				ps.setString(2, fName);
				ps.setString(3, lName);		
				ps.setString(4, contact);
				ps.setString(5, address);
				ps.setString(6, password);
				ps.setString(7, email);


				ps.executeUpdate();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Record Inserted");
				alert.setHeaderText("Record Inserted");
				alert.showAndWait();
				
				st.close();
//				con.close();
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("There is an issue in inserting into the backend during signup");
			return;
		}
	}
  		}
    }
//}
    



