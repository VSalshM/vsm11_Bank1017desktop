package edu.pitt.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.UIManager;

import edu.pitt.bank.Security;
import edu.pitt.utilities.ErrorLogger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * GUI used for logging into the system
 * @author Morrow, Vito Slash
 * @version 1,0
 * LoginUI.java
 */
public class LoginUI {

	private JFrame frameLoginUI;
	private JTextField txtLoginName;
	private JTextField txtPassword;
	private JButton btnExit;
	final private JLabel lblIncorrectLogin = new JLabel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI window = new LoginUI();
					window.frameLoginUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameLoginUI = new JFrame();
		frameLoginUI.setBackground(UIManager.getColor("Button.background"));
		frameLoginUI.setTitle("Bank1017 Login");
		frameLoginUI.setBounds(100, 100, 500, 300);
		frameLoginUI.setVisible(true);
		frameLoginUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLoginUI.getContentPane().setLayout(null);

		lblIncorrectLogin.setText("Incorrect Login Name or Password. Please try again.");
		lblIncorrectLogin.setForeground(Color.RED);
		lblIncorrectLogin.setBounds(20, 20, 440, 20);
		lblIncorrectLogin.setVisible(false);
		frameLoginUI.getContentPane().add(lblIncorrectLogin);

		JLabel lblLoginName = new JLabel("Login Name:");
		lblLoginName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLoginName.setBounds(20, 60, 110, 30);
		frameLoginUI.getContentPane().add(lblLoginName);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(20, 110, 110, 30);
		frameLoginUI.getContentPane().add(lblPassword);

		txtLoginName = new JTextField();
		txtLoginName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtLoginName.setBounds(130, 60, 330, 30);
		frameLoginUI.getContentPane().add(txtLoginName);
		txtLoginName.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setBackground(UIManager.getColor("TextField.background"));
		txtPassword.setBounds(130, 110, 330, 30);
		frameLoginUI.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Security sec = new Security();
					if(sec.validateLogin(txtLoginName.getText() , Integer.parseInt(txtPassword.getText())) == null){
						lblIncorrectLogin.setVisible(true);
					}else{
						AccountDetails acc = new AccountDetails(sec.validateLogin(txtLoginName.getText() , Integer.parseInt(txtPassword.getText())));
						frameLoginUI.dispose();

					}
				}catch(NullPointerException e1){
					ErrorLogger.log(e1.getMessage());
				}catch(NumberFormatException e1){
					ErrorLogger.log(e1.getMessage());
				}

			}




		});
		btnLogin.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnLogin.setForeground(UIManager.getColor("Button.foreground"));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogin.setBounds(210, 170, 90, 30);
		frameLoginUI.getContentPane().add(btnLogin);

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameLoginUI.dispose();
			}
		});
		btnExit.setBackground(UIManager.getColor("Button.darkShadow"));
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(310, 170, 70, 30);
		frameLoginUI.getContentPane().add(btnExit);




	}
}
