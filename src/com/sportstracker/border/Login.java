package com.sportstracker.border;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField pwdField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPleaseLoginOr = new JLabel("Please Login or Sign up if you don't have an account");
		lblPleaseLoginOr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPleaseLoginOr.setBounds(35, 28, 344, 23);
		frame.getContentPane().add(lblPleaseLoginOr);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(90, 75, 70, 23);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPwd = new JLabel("Password:");
		lblPwd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPwd.setBounds(90, 120, 70, 23);
		frame.getContentPane().add(lblPwd);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(158, 77, 106, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(158, 122, 106, 21);
		frame.getContentPane().add(pwdField);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(20, 184, 89, 23);
		frame.getContentPane().add(btnSignUp);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(320, 184, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(221, 184, 89, 23);
		frame.getContentPane().add(btnLogin);
	}
}
