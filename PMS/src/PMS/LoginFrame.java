package PMS;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class LoginFrame extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtfUname;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 302);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUname = new JLabel("Username");
		lblUname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUname.setBounds(102, 84, 71, 19);
		contentPane.add(lblUname);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(102, 132, 71, 14);
		contentPane.add(lblPassword);
		
		txtfUname = new JTextField();
		txtfUname.setBounds(181, 83, 121, 20);
		contentPane.add(txtfUname);
		txtfUname.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(72, 209, 204));
		panel.setBounds(10, 11, 414, 30);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String uname= txtfUname.getText();
				String password= passwordField.getText();
				if (uname.contains("manik") &&  password.contains("manikleena") )
				{
					//txtfUname.setText();
					//txtfPassword.setText();
					DocFrame frame=new DocFrame();
					frame.setVisible(true);				}
				else
					JOptionPane.showMessageDialog(null,"Invalid Username and Password");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(155, 208, 89, 23);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(181, 131, 121, 20);
		contentPane.add(passwordField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 10));
		chckbxNewCheckBox.setBackground(new Color(175, 238, 238));
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbxNewCheckBox.isSelected())
				{
					passwordField.setEchoChar((char)0);
				}
				else
				{
					passwordField.setEchoChar('*');
				}
				
			}
			
		});
		chckbxNewCheckBox.setBounds(181, 147, 110, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame= new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm if you want to Exit", "Patient Management System", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExit.setBounds(262, 208, 89, 23);
		contentPane.add(btnExit);
		
		
	}
}
