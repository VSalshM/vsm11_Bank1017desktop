package edu.pitt.ui;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.ComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;

import edu.pitt.bank.Account;
import edu.pitt.bank.Bank;
import edu.pitt.bank.Customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Color;

/**
 * GUI displaying Account Information
 * @author Morrow, Vito Slash
 * @version 1.0
 * AccountDetails.java
 */
public class AccountDetails {

	private JFrame frameAccountDetails;
	private JTextField txtAmount;
	Bank b = new Bank();

	/**
	 * Create the application.
	 */
	public AccountDetails(Customer customer) {
		initialize(customer);
		b.setAccountOwners();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Customer customer) {
		frameAccountDetails = new JFrame();
		frameAccountDetails.setTitle("Bank1017 Account Details");
		frameAccountDetails.setBounds(100, 100, 500, 400);
		frameAccountDetails.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frameAccountDetails.setVisible(true);
		frameAccountDetails.getContentPane().setLayout(null);

		JTextArea txtWelcome = new JTextArea();
		txtWelcome.setLineWrap(true);
		txtWelcome.setWrapStyleWord(true);
		txtWelcome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtWelcome.setEditable(false);
		txtWelcome.setText(customer.getFirstName() + " " + customer.getLastName() + ", welcome to 1017 Bank. You have the Following Permissions in this system: ");
		txtWelcome.setBounds(20, 20, 440, 60);
		frameAccountDetails.getContentPane().add(txtWelcome);

		final JComboBox<Account> cboAccounts = new JComboBox<Account>();
		for(Account account : b.getAccounts()){
			cboAccounts.addItem(account);
		}
		cboAccounts.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				frameAccountDetails.update(null);
			}
		});
		cboAccounts.setMaximumRowCount(20);
		cboAccounts.setBounds(140, 100, 320, 30);
		frameAccountDetails.getContentPane().add(cboAccounts);

		JLabel lblAccounts = new JLabel("Your accounts:");
		lblAccounts.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAccounts.setBounds(20, 100, 120, 30);
		frameAccountDetails.getContentPane().add(lblAccounts);

		JLabel lblType = new JLabel("Account type: " + ((Account) cboAccounts.getSelectedItem()).getType());
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblType.setBounds(20, 150, 160, 20);
		frameAccountDetails.getContentPane().add(lblType);

		JLabel lblBalnce = new JLabel("Balance: $" + ((Account) cboAccounts.getSelectedItem()).getBalance());
		lblBalnce.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBalnce.setBounds(20, 170, 160, 20);
		frameAccountDetails.getContentPane().add(lblBalnce);

		JLabel lblInterest = new JLabel("Inerest Rate: " + ((Account) cboAccounts.getSelectedItem()).getInterestRate() + "%");
		lblInterest.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInterest.setBounds(20, 190, 150, 20);
		frameAccountDetails.getContentPane().add(lblInterest);

		JLabel lblPenalty = new JLabel("Penalty: $" + ((Account) cboAccounts.getSelectedItem()).getPenalty());
		lblPenalty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPenalty.setBounds(20, 210, 150, 20);
		frameAccountDetails.getContentPane().add(lblPenalty);

		txtAmount = new JTextField();
		txtAmount.setForeground(Color.BLACK);
		txtAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAmount.setBounds(300, 165, 160, 30);
		frameAccountDetails.getContentPane().add(txtAmount);
		txtAmount.setColumns(10);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmount.setBounds(230, 165, 70, 30);
		frameAccountDetails.getContentPane().add(lblAmount);

		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnDeposit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeposit.setBounds(260, 215, 90, 30);
		frameAccountDetails.getContentPane().add(btnDeposit);

		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnWithdraw.setBounds(360, 215, 100, 30);
		frameAccountDetails.getContentPane().add(btnWithdraw);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameAccountDetails.dispose();
				new LoginUI();

			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBounds(380, 290, 80, 30);
		frameAccountDetails.getContentPane().add(btnExit);

		JButton btnShowTransaction = new JButton("Show Transactions");
		btnShowTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TransactionUI((Account) cboAccounts.getSelectedItem());

			}
		});
		btnShowTransaction.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShowTransaction.setBounds(190, 290, 180, 30);
		frameAccountDetails.getContentPane().add(btnShowTransaction);
	}
}
