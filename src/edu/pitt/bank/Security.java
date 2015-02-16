package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

/**
 * Class used to verify login personal's identity
 * @author Morrow, Vito Slash
 * @version 1.0
 * Security.java
 */
public class Security {
/*-----------------------------------CLASS PROPERTIES---------------------------------------*/
	private String userID;

/*--------------------------CLASS CONSTRUCTORS AND METHODS----------------------------------*/	

	/**
	 * Method to match entered information with database to display proper customer and account information
	 * @param loginName name used to login to the system
	 * @param pin personal identification number 
	 * @return customer object with matching login and pin
	 */
	public Customer validateLogin(String loginName, int pin){
		Customer secCust;
		String sql = "SELECT * FROM customer WHERE loginName ='" + loginName + "' AND pin = '" + pin + "';";
		DbUtilities db = new MySqlUtilities();
		try{
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				if(rs.getString("customerID") == null || rs.getString("customerID").equals("null")){
					this.userID = "null";

				}else if(rs.getString("customerID") != null){
					this.userID = rs.getString("customerid");
				}
			}

		}catch(SQLException e){
			ErrorLogger.log(e.getMessage());
			ErrorLogger.log(sql);
		}
		db.closeDbConnection();
		secCust = new Customer(userID);

		return secCust;
	}

	/**
	 * Lists groups users belong to
	 * @param userID the ID of the user whose groups you would like to see
	 * @return groups of user
	 */
	public ArrayList<String> listUserGroups(String userID){
		return null;
	}
}
