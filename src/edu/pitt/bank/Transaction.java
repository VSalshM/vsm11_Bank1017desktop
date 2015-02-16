package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.ErrorLogger;
import edu.pitt.utilities.MySqlUtilities;

/**
 * Class contains constructors and methods fro transaction typ objects
 * @author Morrow, Vito Slash
 * @version 1.0
 * Transaction.java
 */
public class Transaction {
/*-----------------------------------CLASS PROPERTIES---------------------------------------*/
	private String transactionID;
	private String accountID;
	private String type;
	private double amount;
	private double balance;
	private Date transactionDate; 

/*--------------------------CLASS CONSTRUCTORS AND METHODS----------------------------------*/	

	/**
	 * constructor pulls information of existing transaction object
	 * @param transactionID unique IDof transaction object
	 */
	public Transaction(String transactionID){
		String sql = "SELECT * FROM bank1017.transaction "; 
		sql += "WHERE transactionID = '" + transactionID + "'"; // searches database for matching ID
		System.out.println(sql);
		DbUtilities db = new MySqlUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){  //initializes and sets vlue to class attributes
				this.transactionID = rs.getString("transactionID");
				this.accountID = rs.getString("accountID");
				this.type = rs.getString("type");
				this.amount = rs.getDouble("amount");
				this.balance = rs.getDouble("balance");
				this.transactionDate = new Date();
			}
		} catch (SQLException e) {
			ErrorLogger.log(e.getMessage()); //error logging
			ErrorLogger.log(sql); // records query used to produce error
		}
		db.closeDbConnection();
	}
	
	/**
	 * Constructor for transaction class for new transactions
	 * @param accountID Id for the account creating the transaction
	 * @param type Type of transaction created
	 * @param amount number value of currency change
	 * @param balance remaining currency in account
	 */
	public Transaction(String accountID, String type, double amount, double balance){
		this.transactionID = UUID.randomUUID().toString();
		this.type = type;
		this.amount = amount;
		this.accountID = accountID;
		this.balance = balance;
		
		String sql = "INSERT INTO bank1017.transaction ";  //adds transaction to the database
		sql += "(transactionID, accountID, amount, transactionDate, type, balance) ";
		sql += " VALUES ";
		sql += "('" + this.transactionID + "', ";
		sql += "'" + this.accountID + "', ";
		sql += amount + ", ";
		sql += "CURDATE(), ";
		sql += "'" + this.type + "', ";
		sql += balance + ");";

		
		DbUtilities db = new MySqlUtilities();
		db.executeQuery(sql);
		db.closeDbConnection();
	}

/*---------------------------------GETTERS AND SETTERS--------------------------------------*/

	public String getTransactionID() {
		return transactionID;
	}

	public String getAccountID() {
		return accountID;
	}

	public String getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	public double getBalance() {
		return balance;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}
	
}
