package PMS;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DocFrame extends JFrame {
private JFrame frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DocFrame frame = new DocFrame();
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
	public DocFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(72, 209, 204));
		panel.setBounds(5, 5, 424, 31);
		contentPane.add(panel);
		
		JLabel lblNewLabel_3 = new JLabel("Home Page");
		lblNewLabel_3.setForeground(new Color(240, 248, 255));
		lblNewLabel_3.setFont(new Font("Arial Black", Font.PLAIN, 18));
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("View Patient List");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(52, 73, 97, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Search Patient");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(173, 73, 89, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Add New Patient");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(279, 81, 107, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel label_view = new JLabel("New label");
		label_view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				PatientListFrame frame= new PatientListFrame();
				frame.setVisible(true);
			}
		});
		ImageIcon img= new ImageIcon (this.getClass().getResource("/List.jpg"));
		label_view.setIcon(img);
		label_view.setBounds(62, 115, 81, 80);
		contentPane.add(label_view);
		
		JLabel label_search = new JLabel("New label");
		label_search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				PatientSearchFrame frame = new PatientSearchFrame();
				frame.setVisible(true);
			}
		});
		ImageIcon img2= new ImageIcon (this.getClass().getResource("/Search_Patient.jpg"));
		label_search.setIcon(img2);
		label_search.setBounds(173, 115, 81, 80);
		contentPane.add(label_search);
		
		JLabel label_add = new JLabel("New label");
		label_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				PatientFrame frame = new PatientFrame();
				frame.setVisible(true);
			}
		});
		ImageIcon img3= new ImageIcon (this.getClass().getResource("/AddPatient1.png"));
		label_add.setIcon(img3);
		label_add.setBounds(282, 115, 81, 80);
		contentPane.add(label_add);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame= new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm if you want to Exit", "Patient Management System", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnNewButton.setBounds(173, 216, 89, 23);
		contentPane.add(btnNewButton);
	}
}