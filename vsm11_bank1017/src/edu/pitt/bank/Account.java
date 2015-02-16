package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

/**
 * Contains constructors and methods for Account type objects.
 * @author Morrow, Vito Slash
 * @version 1.0 
 * Account.java
 */
public class Account {
/*-----------------------------------CLASS PROPERTIES---------------------------------------*/
	private String accountID;
	private String type;
	private double balance;
	private double interestRate;
	private double penalty;
	private String status;
	private Date dateOpen;
	private ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	private ArrayList<Customer> accountOwners = new ArrayList<Customer>();

/*--------------------------CLASS CONSTRUCTORS AND METHODS----------------------------------*/	

	/**
	 * Constuctor for Account object.
	 * @param accountID Unique ID assigned to an account.
	 */
	public Account(String accountID){
		String sql = "SELECT * FROM account "; 
		sql += "WHERE accountID = '" + accountID + "';";
		DbUtilities db = new MySqlUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){ 		//The following initializes and sets value to the account attributes
				this.accountID = rs.getString("accountID");
				this.type = rs.getString("type");
				this.balance = rs.getDouble("balance");
				this.interestRate = rs.getDouble("interestRate");
				this.penalty = rs.getDouble("penalty");
				this.status = rs.getString("status");
				this.dateOpen = new Date();
			}
		} catch (SQLException e) {
			ErrorLogger.log(e.getMessage()); //error logging.
			ErrorLogger.log(sql); //records the sql query used that caused the error.
		}

		String sql1 = "SELECT * FROM bank1017.transaction "; 
		sql1 += "WHERE accountID = '" + accountID + "'";
		DbUtilities db1 = new MySqlUtilities();
		try {
			ResultSet rs = db1.getResultSet(sql1);
			while(rs.next()){ //Populates the TransactionList.
				createTransaction(rs.getString("transactionID"));
				createTransaction(rs.getString("type"), rs.getDouble("amount"));
			}
		} catch (SQLException e) {
			ErrorLogger.log(e.getMessage()); 
			ErrorLogger.log(sql);
		}
		db.closeDbConnection();
		db1.closeDbConnection();
	}

	/**
	 * Account constructor for opening new accounts.
	 * @param accountType The type in which the account belongs (i.e. Checking, Savings)
	 * @param initialBalance The amount of currency the account holds
	 */
	public Account(String accountType, double initialBalance){
		this.accountID = UUID.randomUUID().toString(); //randomly generated ID
		this.type = accountType;
		this.balance = initialBalance;
		this.interestRate = 0;
		this.penalty = 0;
		this.status = "active";
		this.dateOpen = new Date();

		String sql = "INSERT INTO bank1017.account "; //adds the new account the database
		sql += "(accountID,type,balance,interestRate,penalty,status,dateOpen) ";
		sql += " VALUES ";
		sql += "('" + this.accountID + "', ";
		sql += "'" + this.type + "', ";
		sql += this.balance + ", ";
		sql += this.interestRate + ", ";
		sql += this.penalty + ", ";
		sql += "'" + this.status + "', ";
		sql += "CURDATE());";

		DbUtilities db = new MySqlUtilities();
		db.executeQuery(sql);
		db.closeDbConnection();
	}


	/**
	 * Method for removing currency from an account.
	 * @param amount the number value of the amount wished to be taken out
	 */
	public void withdraw(double amount){
		this.balance -= amount;
		createTransaction(this.accountID, amount);
		updateDatabaseAccountBalance();
	}


	/**
	 * Method for adding currency to an account
	 * @param amount the number value of the amount wished to be added
	 */
	public void deposit(double amount){
		this.balance += amount;
		createTransaction(this.accountID, amount);
		updateDatabaseAccountBalance();
	}

	/**
	 * Method to adjust the account balances in the database to the current balance
	 */
	private void updateDatabaseAccountBalance(){
		String sql = "UPDATE bank1017.account SET balance = " + this.balance + " ";
		sql += "WHERE accountID = '" + this.accountID + "';";

		DbUtilities db = new MySqlUtilities();
		db.executeQuery(sql);
		db.closeDbConnection();
	}

	/**
	 * Method to create an object of type transaction
	 * @param transactionID the unique ID for a transaction
	 * @return object of type Transaction
	 */
	private Transaction createTransaction(String transactionID){
		Transaction t = new Transaction(transactionID);
		transactionList.add(t);
		return t;
	}

	/**
	 * Method to create an object of type transaction
	 * @param type the type of transaction (i.e. withdrawal, deposit)
	 * @param amount the number value of currency change 
	 * @return object of type transaction
	 */
	private Transaction createTransaction(String type, double amount){
		Transaction t = new Transaction(this.accountID, type, amount, this.balance);
		transactionList.add(t);
		return t;
	}

	/**
	 * Method to add a customer object to an existing or new account
	 * @param accountOwner customer object whom owns the account
	 */
	public void addAccountOwner(Customer accountOwner){
		this.accountOwners.add(accountOwner);
	}

	
/*---------------------------------GETTERS AND SETTERS--------------------------------------*/


	public String getAccountID(){
		return this.accountID;
	}

	public double getBalance(){
		return this.balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getPenalty() {
		return penalty;
	}

	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<Customer> getAccountOwners() {
		return accountOwners;
	}


	public String getType() {
		return type;
	}

	public Date getDateOpen() {
		return dateOpen;
	}

	public ArrayList<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
