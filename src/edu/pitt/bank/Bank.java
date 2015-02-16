package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

/**
 * Class containing methods and constructors of bank objects
 * @author Morrow, Vito Slash
 * @version 1,0
 * Bank.java
 */
public class Bank {
/*-----------------------------------CLASS PROPERTIES---------------------------------------*/
	private ArrayList<Account> accountList = new ArrayList<Account>();
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	
/*--------------------------CLASS CONSTRUCTORS AND METHODS----------------------------------*/	

	/**
	 * Constructor for Bank object
	 */
	public Bank(){
		loadAccounts();
		setAccountOwners();
	}

	/**
	 * Method to bring account information from the database to the application
	 */
	public void loadAccounts(){
		DbUtilities db = new MySqlUtilities();
		String sql = "SELECT accountId FROM account;";
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				String ID = rs.getString("accountID");
				Account load = new Account(ID);
				accountList.add(load); //populates accountList attribute.

			}

		} catch (SQLException e) {
			ErrorLogger.log(e.getMessage()); // error logging
            ErrorLogger.log(sql); // record query used to cause error
		}
		db.closeDbConnection();
	}

	/**
	 * Method to search through accounts to find a specific one
	 * @param accountID unique ID of account being searched for
	 * @return account object with matching ID
	 */
	public Account findAccount(String accountID){
		Account foundAccount = null;
		for(Account accountLoop : accountList){
			if(accountLoop.getAccountID().equalsIgnoreCase(accountID)){
				foundAccount = accountLoop;
			}
		}
		return foundAccount;
	}

	/**
	 * Method to search for a specific customer from the customerList
	 * @param customerID unique ID for customer being searched for
	 * @return customer object with matching ID
	 */
	public Customer findCustomer(String customerID){
		Customer foundCustomer = null;
		for(Customer customerLoop : customerList){
			if(customerLoop.getCustomerID().equalsIgnoreCase(customerID)){
				foundCustomer = customerLoop;
			}
		}
		return foundCustomer;
	}

	/**
	 * Method to set Customer objects with corresponding Account objects
	 */
	public void setAccountOwners(){
		DbUtilities db = new MySqlUtilities();
		String sql = "SELECT * FROM account JOIN customer_account ON accountId = fk_accountId JOIN customer ON fk_customerId = customerId;";
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				Account accLoad = new Account(rs.getString("accountID"));
				Customer custLoad = new Customer(rs.getString("customerID"));
				customerList.add(custLoad);
				accLoad.addAccountOwner(custLoad);
			}

		} catch (SQLException e) {
			ErrorLogger.log(e.getMessage()); // Log error
            ErrorLogger.log(sql); //record query used to produce error
		}
		db.closeDbConnection();
	}

/*---------------------------------GETTERS AND SETTERS--------------------------------------*/

	public ArrayList<Account> getAccounts() {
		return accountList;
	}

}
