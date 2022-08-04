package PMS;
import java.sql.*;
import java.util.Vector;
//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//import javax.swing.border.LineBorder;

public class PatientSearchFrame extends JFrame {

	private JPanel contentPane;
	private final JPanel panel_1 = new JPanel();
	private JTextField txtfName;
	private JTable table;
  
	Connection conn=null;
	PreparedStatement pst=null, pst1=null;
	private ResultSet rs=null, rs1=null;
	
	int i,q,id;
	
	/* Database Connection
	
	*/


	public Connection getConnection()
	{
		try 
		{
		conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/PMS", "root", "root");
		}
		catch (Exception e)
		{
			
			System.err.println(e);
		}
		 return conn;
	}
	
	 public void upDate()
	  {
		getConnection();
		try
		{
			
			String q= "Select * from patientInfo  ";
			pst=conn.prepareStatement(q);
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
pst.close();			
			  
						}
		catch(Exception ex)
		{
			System.err.println(ex);
			
		}
		rs=null;
	  }
		  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientSearchFrame frame = new PatientSearchFrame();
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
	public PatientSearchFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 948, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 32, 911, 475);
		panel.setBackground(new Color(175, 238, 238));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Patient Name");
		lblNewLabel.setBounds(67, 24, 94, 14);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel);
		
		txtfName = new JTextField();
		String search=txtfName.getText();
		txtfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				getConnection();
				try
				{
					
					String sql= "Select * from patientInfo where Pname Like ?";
					pst1=conn.prepareStatement(sql);
					pst1.setString(1,'%'+ txtfName.getText() +'%');
					ResultSet result=pst1.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(result));
			}
				catch(Exception ex)
				{
					System.err.println(ex);
					
				}
			}
		});
	
		txtfName.setBounds(188, 22, 134, 20);
		panel.add(txtfName);
		txtfName.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 891, 328);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Pid", "Id", "Pname", "Dob", "Age", "Gender", "Height", "Weight", "BloodGroup", "Address", "Diagnosis"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(122);
		table.getColumnModel().getColumn(4).setPreferredWidth(98);
		scrollPane.setViewportView(table);
		
		JButton btnLoad = new JButton("Load Table");
		btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upDate();
			}
			
		});
		btnLoad.setBounds(523, 21, 213, 23);
		panel.add(btnLoad);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DocFrame frame1= new DocFrame();
				frame1.setVisible(true);
			}
		});
		btnNewButton.setBounds(794, 21, 74, 23);
		panel.add(btnNewButton);
		/*table.setModel(new DefaultTabl
			new Object[][] {
			},
			new String[] {
			}
		));*/
		
		//String search=txtfName.getText();
		panel_1.setBounds(0, 0, 943, 34);
		panel_1.setBackground(new Color(0, 255, 255));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Search Patient Name");
		lblNewLabel_1.setBounds(399, 5, 145, 17);
		lblNewLabel_1.setForeground(new Color(240, 248, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1);
		//upDate();
	}
}
