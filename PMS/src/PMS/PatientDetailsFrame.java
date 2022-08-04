package PMS;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PatientDetailsFrame extends JFrame {
	private JTable table;
	private JTextField txtfName;
	private JTextField txtfDop;
	private JTextField txtfPreDiag;
	private JTextField txtfPlan;
	private JTextField txtfId;
	private JTextField txtfMedicine;
    private JFrame frame;
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	int i, q, id;
	private JTextField txtfPid;
	
	/**
	 * Launch the application.
	 */
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
	public void upDate()
	{   
		getConnection();
		//DefaultTableModel model= (DefaultTableModel) table.getModel();
		try {
			pst=conn.prepareStatement("Select * from patientDetails") ;
			rs= pst.executeQuery();
			ResultSetMetaData rsmd= rs.getMetaData();
			q=rsmd.getColumnCount();
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
					columnData.add(rs.getString("Dop"));
					columnData.add(rs.getString("PreoperativeDiagnosis"));
					columnData.add(rs.getString("PlannedProcedure"));
					columnData.add(rs.getString("Medication"));
					
				} model.addRow(columnData);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientDetailsFrame frame = new PatientDetailsFrame();
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
	public PatientDetailsFrame() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		getContentPane().setBackground(new Color(175, 238, 238));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 530);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(72, 209, 204));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(30, 144, 255), null, null, null));
		panel.setBounds(10, 11, 858, 55);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPatientDetails = new JLabel("Patient Details");
		lblPatientDetails.setBounds(265, 11, 327, 44);
		lblPatientDetails.setForeground(new Color(240, 248, 255));
		lblPatientDetails.setFont(new Font("Tahoma", Font.PLAIN, 48));
		panel.add(lblPatientDetails);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(32, 98, 819, 129);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model=(DefaultTableModel) table.getModel();
				int selectedRow= table.getSelectedRow();
				
				txtfPid.setText(model.getValueAt(selectedRow,0).toString());
				txtfId.setText(model.getValueAt(selectedRow, 1).toString());
				txtfName.setText(model.getValueAt(selectedRow, 2).toString());
				txtfDop.setText(model.getValueAt(selectedRow, 3).toString());
				txtfPreDiag.setText(model.getValueAt(selectedRow, 4).toString());
				txtfPlan.setText(model.getValueAt(selectedRow, 5).toString());
				txtfMedicine.setText(model.getValueAt(selectedRow, 6).toString());
				
			}
		});
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Pid", "Id", "Pname", "Date of Procedure", "Pre-Operative Diagnosis", "Planned Procedure", "Medication"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(101);
		table.getColumnModel().getColumn(3).setPreferredWidth(138);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		
		JLabel lblWeight = new JLabel("Patient Name");
		lblWeight.setForeground(Color.BLACK);
		lblWeight.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblWeight.setBounds(36, 255, 108, 19);
		getContentPane().add(lblWeight);
		
		JLabel lblWeight_1 = new JLabel("Date ");
		lblWeight_1.setForeground(Color.BLACK);
		lblWeight_1.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblWeight_1.setBounds(719, 255, 45, 19);
		getContentPane().add(lblWeight_1);
		
		JLabel lblWeight_2 = new JLabel("Pre-operative Diagnosis");
		lblWeight_2.setForeground(Color.BLACK);
		lblWeight_2.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblWeight_2.setBounds(36, 300, 169, 19);
		getContentPane().add(lblWeight_2);
		
		JLabel lblWeight_3 = new JLabel("Planned Procedure");
		lblWeight_3.setForeground(Color.BLACK);
		lblWeight_3.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblWeight_3.setBounds(36, 341, 169, 19);
		getContentPane().add(lblWeight_3);
		
		JLabel lblWeight_4 = new JLabel("ID");
		lblWeight_4.setForeground(Color.BLACK);
		lblWeight_4.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblWeight_4.setBounds(468, 255, 45, 19);
		getContentPane().add(lblWeight_4);
		
		JLabel lblWeight_5 = new JLabel("Medication");
		lblWeight_5.setForeground(Color.BLACK);
		lblWeight_5.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblWeight_5.setBounds(36, 389, 108, 19);
		getContentPane().add(lblWeight_5);
		

		JButton btnUpdate = new JButton("Update");
		
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(230, 442, 108, 38);
		getContentPane().add(btnUpdate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnReset.setBounds(406, 442, 108, 38);
		getContentPane().add(btnReset);
		
		txtfPid = new JTextField();
		txtfPid.setColumns(10);
		txtfPid.setBounds(642, 256, 67, 20);
		getContentPane().add(txtfPid);
		
		txtfName = new JTextField();
		txtfName.setBounds(230, 256, 228, 20);
		getContentPane().add(txtfName);
		txtfName.setColumns(10);
		
		txtfDop = new JTextField();
		txtfDop.setColumns(10);
		txtfDop.setBounds(774, 256, 77, 20);
		getContentPane().add(txtfDop);
		
		txtfPreDiag = new JTextField();
		txtfPreDiag.setColumns(10);
		txtfPreDiag.setBounds(230, 301, 540, 20);
		getContentPane().add(txtfPreDiag);
		
		txtfPlan = new JTextField();
		txtfPlan.setColumns(10);
		txtfPlan.setBounds(230, 342, 540, 20);
		getContentPane().add(txtfPlan);
		
		txtfId = new JTextField();
		txtfId.setColumns(10);
		txtfId.setBounds(506, 256, 86, 20);
		getContentPane().add(txtfId);
		
		txtfMedicine = new JTextField();
		txtfMedicine.setColumns(10);
		txtfMedicine.setBounds(230, 388, 540, 20);
		getContentPane().add(txtfMedicine);
		//JFrame frame1;
		JButton btnExit = new JButton("Exit");
		
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBounds(666, 442, 108, 38);
		getContentPane().add(btnExit);
		
		JLabel lblWeight_4_1 = new JLabel("Pid");
		lblWeight_4_1.setForeground(Color.BLACK);
		lblWeight_4_1.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblWeight_4_1.setBounds(609, 255, 34, 19);
		getContentPane().add(lblWeight_4_1);
		
		
	
		upDate();
	}
}
