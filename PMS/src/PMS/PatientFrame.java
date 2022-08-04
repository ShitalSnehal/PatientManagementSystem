package PMS;
import java.sql.*;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
/*import javax.swing.JRadioButton;
import javax.swing.JTextArea;*/
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
 class PatientFrame extends JFrame {

	private static final Connection Null = null;
	private JPanel contentPane;
	private JTextField txtfName;
	private JTextField txtfWt;
	private JTextField txtfAdd;
	private JTextField txtfAge;
	private JTextField txtfHt;
	private JTextField txtfBldGrp;
	private JTextField txtfDiagno;

	/**
	 * Launch the application.
	 * 
	 * 
	 */
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs= null;
	private JTextField txtfId;
	private JTextField txtfPid;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientFrame frame = new PatientFrame();
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
	public PatientFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 490);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(115, 114, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtfName = new JTextField();
		txtfName.setBounds(205, 109, 175, 22);
		contentPane.add(txtfName);
		txtfName.setColumns(10);
		
		JLabel lblDob = new JLabel("DOB:");
		lblDob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDob.setBounds(115, 164, 46, 14);
		contentPane.add(lblDob);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender.setBounds(115, 261, 64, 14);
		contentPane.add(lblGender);
		
		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWeight.setBounds(384, 213, 53, 19);
		contentPane.add(lblWeight);
		
		txtfWt = new JTextField();
		txtfWt.setBounds(447, 214, 86, 20);
		contentPane.add(txtfWt);
		txtfWt.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(115, 320, 81, 14);
		contentPane.add(lblAddress);
		
		txtfAdd = new JTextField();
		txtfAdd.setBounds(205, 319, 175, 20);
		contentPane.add(txtfAdd);
		txtfAdd.setColumns(10);
		
		JLabel lblDiagno = new JLabel("Diagnosis:");
		lblDiagno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiagno.setBounds(115, 353, 79, 17);
		contentPane.add(lblDiagno);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAge.setBounds(115, 214, 46, 18);
		contentPane.add(lblAge);
		
		txtfAge = new JTextField();
		txtfAge.setBounds(205, 214, 86, 20);
		contentPane.add(txtfAge);
		txtfAge.setColumns(10);
		
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHeight.setBounds(385, 162, 64, 18);
		contentPane.add(lblHeight);
		
		txtfHt = new JTextField();
		txtfHt.setBounds(447, 161, 86, 20);
		contentPane.add(txtfHt);
		txtfHt.setColumns(10);
		
		JButton btnSurgeryDetail = new JButton("Add Surgery Details");
		btnSurgeryDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSurgeryDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSurgeryDetail.setBounds(447, 393, 153, 23);
		contentPane.add(btnSurgeryDetail);
		
		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(205, 164, 136, 20);
		contentPane.add(dateChooser);
		
		JList list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 12));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Male", "Female", "Other"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(205, 260, 86, 48);
		contentPane.add(list);
		
		JButton btnClear = new JButton("Reset");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClear.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				txtfPid.setText(null);
				txtfName.setText(null);
				dateChooser.setCalendar(null);
				txtfWt.setText(null);
				txtfHt.setText(null);
				txtfAge.setText(null);
				list.clearSelection();
				txtfBldGrp.setText(null);
				txtfAdd.setText(null);
				txtfDiagno.setText(null);
			}
		});
		btnClear.setBounds(291, 393, 89, 23);
		contentPane.add(btnClear);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(240, 248, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(72, 209, 204));
		panel.setBounds(10, 11, 611, 30);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPatientInfo = new JLabel("Patient Information");
		lblPatientInfo.setForeground(new Color(240, 248, 255));
		lblPatientInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPatientInfo.setBounds(37, 11, 317, 14);
		panel.add(lblPatientInfo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 139, 139));
		panel_1.setBounds(10, 52, 95, 389);
		contentPane.add(panel_1);
		
		JLabel lblBldGrp = new JLabel("Blood Group:");
		lblBldGrp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBldGrp.setBounds(351, 268, 86, 22);
		contentPane.add(lblBldGrp);
		
		txtfBldGrp = new JTextField();
		txtfBldGrp.setBounds(447, 270, 86, 20);
		contentPane.add(txtfBldGrp);
		txtfBldGrp.setColumns(10);
		
		txtfDiagno = new JTextField();
		txtfDiagno.setBounds(204, 350, 267, 20);
		contentPane.add(txtfDiagno);
		txtfDiagno.setColumns(10);
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try
				{
			//	Class.forName("com.mysql.jdbc.Driver");6YUUUUUUUUUUUUUUUUUUUUEEAQQQFHHHHHHHHHHHHHHHHHHH	w	w																																																							
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/PMS","root","root");
					pst=con.prepareStatement("insert into patientInfo (Pid,Id,Pname, Dob, Age,Gender, Height,Weight,BloodGroup, Address, Diagnosis) values (?,?,?,?,?,?,?,?,?,?,?)");
					pst.setString(1, txtfPid.getText());
					pst.setString(2, txtfId.getText());
					pst.setString(3, txtfName.getText());
					SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
					String date= sdf.format( dateChooser.getDate());
					
					pst.setString(4, date);
					pst.setString(5, txtfAge.getText());
					pst.setString(6,(String) list.getSelectedValue());
					pst.setString(7, txtfHt.getText());
					pst.setString(8, txtfWt.getText());
					pst.setString(9, txtfBldGrp.getText());
					pst.setString(10, txtfAdd.getText());
					pst.setString(11, txtfDiagno.getText());
					
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null , "Patient Record is saved");
					
					
				}
				catch(SQLException ex)
				
				{
					System.err.println(ex);
					
				}
				
			}
		});
		btnSave.setBounds(136, 393, 89, 23);
		contentPane.add(btnSave);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(465, 114, 27, 14);
		contentPane.add(lblNewLabel_1);
		
		txtfId = new JTextField();
		txtfId.setBounds(502, 110, 86, 20);
		contentPane.add(txtfId);
		txtfId.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Home");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DocFrame frame1= new DocFrame();
				frame1.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(107, 64, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Patient ID:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(413, 68, 79, 14);
		contentPane.add(lblNewLabel_1_1);
		
		txtfPid = new JTextField();
		txtfPid.setColumns(10);
		txtfPid.setBounds(502, 65, 86, 20);
		contentPane.add(txtfPid);
	}
}
