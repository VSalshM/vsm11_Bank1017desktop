package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

/**
 * Class containing methods and constructors for customer objects
 * @author Morrow, Vito Slash
 * @version 1.0
 * Customer.java
 */
public class Customer {
/*-----------------------------------CLASS PROPERTIES---------------------------------------*/
	private String customerID;
	private String firstName;
	private String lastName;
	private String ssn;
	private String streetAddress;
	private String city;
	private String state;
	private int zip;
	private String loginName;
	private int pin;
	
/*--------------------------CLASS CONSTRUCTORS AND METHODS----------------------------------*/	
	
	/**
	 * Constructor for customer object
	 * @param customerID unique ID for customer object
	 */
	public Customer(String customerID){
		String sql = "SELECT * FROM customer "; 
		sql += "WHERE customerID = '" + customerID + "';";  //Searches database for matching customerID
		DbUtilities db = new MySqlUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){  //the following initializes and sets value to class attributes
				this.firstName = rs.getString("firstName");
				this.lastName = rs.getString("lastName");
				this.ssn = rs.getString("ssn");
				this.streetAddress = rs.getString("streetAddress");
				this.city = rs.getString("city");
				this.state = rs.getString("state");
				this.zip = rs.getInt("zip");
				this.loginName = rs.getString("loginName");
				this.pin = rs.getInt("pin");
			}
		} catch (SQLException e) {
			ErrorLogger.log(e.getMessage()); //error logging
            ErrorLogger.log(sql); //records query used to produce error
            }
		db.closeDbConnection();
	}
	
	/**
	 * Constructor for customer object
	 * @param lastName Last name of customer
	 * @param firstName First name of customer
	 * @param ssn Social security number of customer
	 * @param loginName Login name of customer
	 * @param pin Personal identification number for customer
	 * @param streetAddress Street Address of customer
	 * @param city City where customer lives
	 * @param state State where customer lives
	 * @param zip zip code of customer
	 */
	public Customer(String lastName, String firstName, String ssn, String loginName, int pin, String streetAddress, String city, String state, int zip){
		this.customerID = UUID.randomUUID().toString();
		this.lastName = lastName;
		this.firstName = firstName;
		this.ssn = ssn;
		this.loginName = loginName;
		this.pin = pin;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		
		String sql = "INSERT INTO bank1017.customer "; //Adds new customer to database
		sql += "(customerID,lastName,firstName,ssn,loginName,pin,streetAddress,city,state,zip) ";
		sql += " VALUES ";
		sql += "('" + this.customerID + "', ";
		sql += "'" + this.lastName + "', ";
		sql += "'" + this.firstName + "', ";
		sql += "'" + this.ssn + "', ";
		sql += "'" + this.loginName + ", ";
		sql += "'" + this.pin + "', ";
		sql += "'" + this.streetAddress + "', ";
		sql += "'" + this.city + "', ";
		sql += "'" + this.state + "', ";
		sql += "'" + this.zip + ");";
		
		DbUtilities db = new MySqlUtilities();
		db.executeQuery(sql);
		db.closeDbConnection();
	}

/*---------------------------------GETTERS AND SETTERS--------------------------------------*/

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getCustomerID() {
		return customerID;
	}

	public String getLoginName() {
		return loginName;
	}
	
}
