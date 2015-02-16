package edu.pitt.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import edu.pitt.bank.Account;
import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * GUI displaying transactions for a specific account
 * @author Morrow, Vito Slash
 * @version 1.0
 * TransactionUI.java
 */
public class TransactionUI {
	JFrame frameTransactionUI;
	private JScrollPane sclPnTransactions;
	private JTable tableTransactions;
	private JButton btnExit;
	
	/**
	 * Create the application.
	 */
	public TransactionUI(Account account){
		
		initialize(account);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Account account) {
		frameTransactionUI = new JFrame();
		frameTransactionUI.setTitle("Bank 1017 Account Transactions");
		frameTransactionUI.setBounds(100, 100, 500, 340);
		frameTransactionUI.setVisible(true);
		frameTransactionUI.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frameTransactionUI.getContentPane().setLayout(null);
		
		sclPnTransactions = new JScrollPane();
		sclPnTransactions.setBounds(0, 0, 484, 250);
		
		frameTransactionUI.getContentPane().add(sclPnTransactions);
		DbUtilities db = new MySqlUtilities();
		String sql = "SELECT * FROM transaction WHERE accountId = '" + account.getAccountID() + "';";
		try {
			
			DefaultTableModel transactionList = db.getDataTable(sql);
			tableTransactions = new JTable(transactionList);
			tableTransactions.setFillsViewportHeight(true);
			tableTransactions.setShowGrid(true);
			tableTransactions.setGridColor(Color.black);
			sclPnTransactions.setViewportView(tableTransactions);
			
			btnExit = new JButton("Exit");
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frameTransactionUI.dispose();
				}
			});
			btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnExit.setBounds(326, 256, 148, 34);
			frameTransactionUI.getContentPane().add(btnExit);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameTransactionUI.dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(326, 256, 148, 34);
		frameTransactionUI.getContentPane().add(btnExit);
	}
}
