package PMS;
import java.sql.*;
import java.text.MessageFormat;
//import java.util.ArrayList;
import java.util.Vector;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class PatientListFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtfName;
	private JTextField txtfDob;
	private JTextField txtfAge;
	private JTextField txtfGender;
	private JTextField txtfHt;
	private JTextField txtfWt;
	private JTextField txtfBld;
	private JTextField txtfAddress;
	private JTextField txtfDiag;
	private JTextField txtfId;

	/**
	 * Launch the application.
	 */
	
	
	
	JTable table= new JTable();
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	int q, i, id, deleteitem;
	private JTextField txtfPreop;
	private JTextField txtfPlan;
	private JTextField txtfDop;
	private JTextField txtfMedicine;
	private JTextField txtfPid;
	//private JTable table;
	//preparedStatement 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientListFrame frame = new PatientListFrame();
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
  
  public Connection getConnection()
  {
	 
	   try
	   {
		   conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/PMS","root","root");
		    
		   
	   }
	   catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	  
	  return conn;
  }
  
  /*public void show_table()
  {
	  try
		{
			getConnection();
			String query= "Select * from patientInfo";
			pst=conn.prepareStatement(query);
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
					  
						}
		catch(Exception ex)
		{
			System.err.println(ex);
			
		}
	
 }*/
	  
  
	
  public void upDate()
  {
	getConnection();
	try {
		pst= conn.prepareStatement("select * from patientInfo");
		 rs=pst.executeQuery();
		 ResultSetMetaData rsData= rs.getMetaData();
		  //table.setModel(DbUtils.resultSetToTableModel(rs));
		  //JTable table1= new JTable();
		  q=rsData.getColumnCount();
		  DefaultTableModel model=(DefaultTableModel) table.getModel();
		 model.setRowCount(0);
		  while(rs.next())
		  {
			  Vector columnData= new Vector();
		    for (i=1;i<=q;i++)
		    {
		    	columnData.add(rs.getInt("Pid"));
		    	columnData.add(rs.getInt("Id"));
		    	columnData.add(rs.getString("Pname"));
		    	columnData.add(rs.getString("Dob"));
		    	columnData.add(rs.getString("Age"));
		    	columnData.add(rs.getString("Gender"));
		    	columnData.add(rs.getString("Height"));
		    	columnData.add(rs.getString("Weight"));
		    	columnData.add(rs.getString("BloodGroup"));
		    	columnData.add(rs.getString("Address"));
		    	columnData.add(rs.getString("Diagnosis"));
		    	//columnData.add(rs.getShort("Pid"));
		    		
		    	
		    }
		    model.addRow(columnData);	    
		  
	} 
	}
		catch (Exception Ex)
		{
			JOptionPane.showMessageDialog(null, Ex);
		}
	}
	  
  
  /**
	 * Create the frame.
	 
	 */
	public PatientListFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1009, 700);
		contentPane = new JPanel();
	//	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		panel.setBounds(0, 0, 983, 35);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("List of Patients");
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 16));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(175, 238, 238));
		panel_1.setBounds(0, 29, 983, 380);
		contentPane.add(panel_1);
		
		
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(210, 325, 100, 44);
		
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
btnUpdate.addActionListener(new ActionListener() 
{
		public void actionPerformed(ActionEvent e) {
			
			DefaultTableModel model=(DefaultTableModel) table.getModel();
			int selectedRow= table.getSelectedRow();
			getConnection();
			if(txtfPid.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Please enter Valid Pid");
			}
			else
			
			try {
				id=Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
				//JOptionPane.showMessageDialog(null, "id="+id);
				pst=conn.prepareStatement("update patientInfo set Pname=?, Dob=?, Age=?, Gender=?, Height=?, Weight=?, BloodGroup=?, Address=?, Diagnosis=? where Pid=?");
				
				pst.setString(1, txtfName.getText());
				pst.setString(2, txtfDob.getText());
				pst.setString(3, txtfAge.getText());
				pst.setString(4, txtfGender.getText());
				pst.setString(5, txtfHt.getText());
				pst.setString(6, txtfWt.getText());
				pst.setString(7, txtfBld.getText());
				pst.setString(8, txtfAddress.getText());
				pst.setString(9, txtfDiag.getText());
				
				pst.setInt(10,id);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null,"Record updated Successfully");
				upDate();
				}
			catch(SQLException ex)
			{
				java.util.logging.Logger.getLogger(PatientListFrame.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
			}
			}
		});
		panel_1.setLayout(null);
		panel_1.add(btnUpdate);
		
		JButton btnNewButton_1 = new JButton("Add Patient");
		btnNewButton_1.setBounds(52, 325, 111, 44);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		
btnNewButton_1.addActionListener(new ActionListener() {
			 
	
	
	
	public void actionPerformed(ActionEvent e) {
				//new PatientFrame();
				getConnection();
				if(txtfPid.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Please enter Valid Pid");
				}
				else
				
				try {
					
					pst=conn.prepareStatement("insert into patientInfo ( Pid, Id, Pname, Dob, Age, Gender, Height, Weight, BloodGroup, Address, Diagnosis) values (?,?,?,?,?,?,?,?,?,?,?)");
					
					pst.setString(1, txtfPid.getText());
					pst.setString(2, txtfId.getText());
					pst.setString(3, txtfName.getText());
					pst.setString(4, txtfDob.getText());
					pst.setString(5, txtfAge.getText());
					pst.setString(6, txtfGender.getText());
					pst.setString(7, txtfHt.getText());
					pst.setString(8, txtfWt.getText());
					pst.setString(9, txtfBld.getText());
					pst.setString(10, txtfAddress.getText());
					pst.setString(11, txtfDiag.getText());
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Record Added Successfully");
					upDate();
					conn.close();		}
				/*catch(ClassNotFoundException ex)
				{
					java.util.logging.Logger.getLogger(PatientListFrame.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
				}*/
				catch(SQLException ex)
				{
					java.util.logging.Logger.getLogger(PatientListFrame.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
				}
			}
			
		});

		panel_1.add(btnNewButton_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(369, 325, 100, 44);
		
btnDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
			
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				
				try 
				{ 
					id=Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
					deleteitem=JOptionPane.showConfirmDialog(null,"Are you sure You want to Delete","warning",JOptionPane.YES_NO_OPTION);
					//JOptionPane.showMessageDialog(null, "id="+id);
					if(deleteitem==JOptionPane.YES_OPTION)
					{
						 getConnection();
						 pst=conn.prepareStatement("Delete from patientInfo where Pid=?");
						
						 pst.setInt(1, id);
						 pst.executeUpdate();
						
						 JOptionPane.showMessageDialog(null, "Record Deleted Successsfully");
						 upDate();
						 txtfPid.setText("");
						 txtfId.setText("");
						 txtfId.requestFocus();
						 txtfName.setText("");
						 txtfDob.setText("");
						 txtfAge.setText("");
						 txtfGender.setText("");
						 txtfHt.setText("");
						 txtfWt.setText("");
						 txtfBld.setText("");
						 txtfAddress.setText("");
						 txtfDiag.setText("");
						
					}
					conn.close();
				}
				
				catch(SQLException ex)
				{
					System.err.println(ex);
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(btnDelete);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(22, 188, 86, 19);
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Georgia", Font.PLAIN, 16));
		panel_1.add(lblName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(22, 222, 119, 19);
		lblDateOfBirth.setForeground(Color.BLACK);
		lblDateOfBirth.setFont(new Font("Georgia", Font.PLAIN, 16));
		panel_1.add(lblDateOfBirth);
		
		JLabel lblName_1_1 = new JLabel("Age");
		lblName_1_1.setBounds(22, 252, 86, 19);
		lblName_1_1.setForeground(Color.BLACK);
		lblName_1_1.setFont(new Font("Georgia", Font.PLAIN, 16));
		panel_1.add(lblName_1_1);
		
		JLabel lblName_1_1_1 = new JLabel("Gender");
		lblName_1_1_1.setBounds(22, 282, 86, 19);
		lblName_1_1_1.setForeground(Color.BLACK);
		lblName_1_1_1.setFont(new Font("Georgia", Font.PLAIN, 16));
		panel_1.add(lblName_1_1_1);
		
		JLabel lblName_1_1_1_1 = new JLabel("Height");
		lblName_1_1_1_1.setBounds(369, 153, 86, 19);
		lblName_1_1_1_1.setForeground(Color.BLACK);
		lblName_1_1_1_1.setFont(new Font("Georgia", Font.PLAIN, 16));
		panel_1.add(lblName_1_1_1_1);
		
		JLabel lblWeight = new JLabel("Id");
		lblWeight.setBounds(22, 153, 73, 19);
		lblWeight.setForeground(Color.BLACK);
		lblWeight.setFont(new Font("Georgia", Font.PLAIN, 16));
		panel_1.add(lblWeight);
		
		JLabel lblBld = new JLabel("Blood Group");
		lblBld.setBounds(369, 222, 100, 19);
		lblBld.setForeground(Color.BLACK);
		lblBld.setFont(new Font("Georgia", Font.PLAIN, 16));
		panel_1.add(lblBld);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(369, 252, 86, 19);
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setFont(new Font("Georgia", Font.PLAIN, 16));
		panel_1.add(lblAddress);
		
		JLabel lblDiagnosis = new JLabel("Diagnosis");
		lblDiagnosis.setBounds(369, 282, 86, 19);
		lblDiagnosis.setForeground(Color.BLACK);
		lblDiagnosis.setFont(new Font("Georgia", Font.PLAIN, 16));
		panel_1.add(lblDiagnosis);
		
		txtfName = new JTextField();
		txtfName.setBounds(137, 189, 200, 20);
		panel_1.add(txtfName);
		txtfName.setColumns(10);
		
		txtfDob = new JTextField();
		txtfDob.setBounds(137, 220, 100, 20);
		txtfDob.setColumns(10);
		panel_1.add(txtfDob);
		
		txtfAge = new JTextField();
		txtfAge.setBounds(137, 251, 86, 20);
		txtfAge.setColumns(10);
		panel_1.add(txtfAge);
		
		txtfGender = new JTextField();
		txtfGender.setBounds(137, 282, 86, 20);
		txtfGender.setColumns(10);
		panel_1.add(txtfGender);
		
		txtfHt = new JTextField();
		txtfHt.setBounds(490, 154, 86, 20);
		txtfHt.setColumns(10);
		panel_1.add(txtfHt);
		
		txtfWt = new JTextField();
		txtfWt.setBounds(490, 189, 100, 20);
		txtfWt.setColumns(10);
		panel_1.add(txtfWt);
		
		txtfBld = new JTextField();
		txtfBld.setBounds(490, 223, 100, 20);
		txtfBld.setColumns(10);
		panel_1.add(txtfBld);
		
		txtfAddress = new JTextField();
		txtfAddress.setBounds(488, 253, 211, 20);
		txtfAddress.setColumns(10);
		panel_1.add(txtfAddress);
		
		txtfDiag = new JTextField();
		txtfDiag.setBounds(490, 283, 243, 20);
		txtfDiag.setColumns(10);
		panel_1.add(txtfDiag);
		
		txtfId = new JTextField();
		txtfId.setBounds(137, 154, 148, 20);
		txtfId.setColumns(10);
		panel_1.add(txtfId);
		
		JLabel lblWeight_1 = new JLabel("Weight");
		lblWeight_1.setBounds(369, 188, 73, 19);
		lblWeight_1.setForeground(Color.BLACK);
		lblWeight_1.setFont(new Font("Georgia", Font.PLAIN, 16));
		panel_1.add(lblWeight_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 963, 90);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				int selectedRow= table.getSelectedRow();

				txtfId.setText(model.getValueAt(selectedRow, 1).toString());
				txtfName.setText(model.getValueAt(selectedRow, 2).toString());
				txtfDob.setText(model.getValueAt(selectedRow, 3).toString());
				txtfAge.setText(model.getValueAt(selectedRow, 4).toString());
				txtfGender.setText(model.getValueAt(selectedRow, 5).toString());
				txtfHt.setText(model.getValueAt(selectedRow, 6).toString());
				txtfWt.setText(model.getValueAt(selectedRow,7).toString());
				txtfBld.setText(model.getValueAt(selectedRow, 8).toString());
				txtfAddress.setText(model.getValueAt(selectedRow, 9).toString());
				txtfDiag.setText(model.getValueAt(selectedRow,10 ).toString());
			}
		});
		scrollPane.setViewportView(table);
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(new Color(238, 232, 170));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Pid", "Id", "Name", "Date_of_Birth", "Age", "Gender", "Height", "Weight", "Blood Group", "Address", "Diagnosis"
			}
		));
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MessageFormat header=new MessageFormat("Printing in Progress");
				MessageFormat footer= new MessageFormat("Page(0, header, footer)");
				
				try {
					table.print(JTable.PrintMode.NORMAL,header,footer);
					
				}
				catch(java.awt.print.PrinterException ex)
				{
					System.err.format("No Printer Found", ex.getMessage());
				}
			}
		});
		
		
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPrint.setBounds(668, 325, 100, 44);
		panel_1.add(btnPrint);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtfId.setText("");
				txtfName.setText("");
				txtfDob.setText("");
				txtfAge.setText("");
				txtfGender.setText("");
				txtfHt.setText("");
				txtfWt.setText("");
				txtfBld.setText("");
				txtfAddress.setText("");
				txtfDiag.setText("");
				
				
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBounds(519, 325, 100, 44);
		panel_1.add(btnReset);
		
		JButton btnDetails = new JButton("Details");
		
		btnDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDetails.setBounds(825, 325, 111, 44);
		panel_1.add(btnDetails);
		
		JLabel lblName_1_1_1_1_1 = new JLabel("Pid");
		lblName_1_1_1_1_1.setForeground(Color.BLACK);
		lblName_1_1_1_1_1.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblName_1_1_1_1_1.setBounds(594, 153, 25, 19);
		panel_1.add(lblName_1_1_1_1_1);
		
		txtfPid = new JTextField();
		txtfPid.setToolTipText("Please enter the Valid Pid");
		txtfPid.setColumns(10);
		txtfPid.setBounds(629, 154, 125, 20);
		SwingUtilities.invokeLater(new Runnable() {
		    @Override
		    public void run() {
		    	txtfPid.requestFocusInWindow();
		    }
		});
		panel_1.add(txtfPid);
		
		JButton btnNewButton_2 = new JButton("Home");
		btnNewButton_2.setBackground(new Color(0, 250, 154));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DocFrame frame1= new DocFrame();
				frame1.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(10, 119, 89, 23);
		panel_1.add(btnNewButton_2);
		
		JLabel lblPleaseEnterValid = new JLabel("* Please enter valid Pid");
		lblPleaseEnterValid.setForeground(new Color(255, 0, 0));
		lblPleaseEnterValid.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblPleaseEnterValid.setBounds(626, 137, 161, 19);
		panel_1.add(lblPleaseEnterValid);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(175, 238, 238));
		panel_2.setBounds(0, 409, 983, 241);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(false);
		
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(true);
			}
		});
		
		
		JLabel lblDiagnosis_1_1 = new JLabel("Pre-operative Diagnosis");
		lblDiagnosis_1_1.setForeground(Color.BLACK);
		lblDiagnosis_1_1.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblDiagnosis_1_1.setBounds(10, 48, 176, 19);
		panel_2.add(lblDiagnosis_1_1);
		
		txtfPreop = new JTextField();
		txtfPreop.setColumns(10);
		txtfPreop.setBounds(196, 49, 517, 20);
		panel_2.add(txtfPreop);
		
		JLabel lblDiagnosis_1_1_1 = new JLabel("Planned Procedure");
		lblDiagnosis_1_1_1.setForeground(Color.BLACK);
		lblDiagnosis_1_1_1.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblDiagnosis_1_1_1.setBounds(10, 78, 176, 19);
		panel_2.add(lblDiagnosis_1_1_1);
		
		txtfPlan = new JTextField();
		txtfPlan.setColumns(10);
		txtfPlan.setBounds(196, 80, 517, 20);
		panel_2.add(txtfPlan);
		
		JLabel lblDiagnosis_1_2 = new JLabel("Date of Procedure");
		lblDiagnosis_1_2.setForeground(Color.BLACK);
		lblDiagnosis_1_2.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblDiagnosis_1_2.setBounds(10, 18, 142, 19);
		panel_2.add(lblDiagnosis_1_2);
		
		JLabel lblDiagnosis_1_3 = new JLabel("Medication");
		lblDiagnosis_1_3.setForeground(Color.BLACK);
		lblDiagnosis_1_3.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblDiagnosis_1_3.setBounds(10, 112, 86, 19);
		panel_2.add(lblDiagnosis_1_3);
		
		txtfDop = new JTextField();
		txtfDop.setColumns(10);
		txtfDop.setBounds(196, 18, 142, 20);
		panel_2.add(txtfDop);
		
		txtfMedicine = new JTextField();
		txtfMedicine.setColumns(10);
		txtfMedicine.setBounds(196, 113, 517, 20);
		panel_2.add(txtfMedicine);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
getConnection();
				
				try {
					
					pst=conn.prepareStatement("insert into patientDetails (Pid, id ,Pname, Dop, PreoperativeDiagnosis, PlannedProcedure, Medication) values (?,?,?,?,?,?,?)");
					pst.setString(1, txtfPid.getText());
					pst.setString(2, txtfId.getText());
					pst.setString(3, txtfName.getText());
					pst.setString(4, txtfDop.getText());
					pst.setString(5, txtfPreop.getText());
					pst.setString(6, txtfPlan.getText());
					pst.setString(7, txtfMedicine.getText());
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Record Saved Successfully");
					upDate();
					conn.close();
					}
				/*catch(ClassNotFoundException ex)
				{
					java.util.logging.Logger.getLogger(PatientListFrame.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
				}*/
				catch(SQLException ex)
				{
					java.util.logging.Logger.getLogger(PatientListFrame.class.getName()).log(java.util.logging.Level.SEVERE,null,ex);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(44, 180, 108, 38);
		panel_2.add(btnNewButton);
		upDate();
		
		
	}
}
