package org.unibl.etf.bp.gui;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddWorkPlace extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField textId;
	private JTextField textName;
	private JTextField textAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddWorkPlace frame = new AddWorkPlace();
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
	public AddWorkPlace() {
		setTitle("Dodaj radnju");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 10, 10, 140);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 222, 733, 310);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Radnja ID", "Naziv", "Adresa"
			}
		));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				String name = textName.getText();
				String address = textAddress.getText();
				addWorkPlace(Integer.parseInt(id), name, address);
				textId.setText("");
				textName.setText("");
				textAddress.setText("");
				model.setRowCount(0);
				showWorkPlace();
			}
		});
		
		btnNewButton.setBounds(479, 157, 85, 21);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("Radnja ID");
		lblNewLabel.setBounds(136, 45, 63, 13);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Naziv");
		lblNewLabel_1.setBounds(136, 81, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Adresa");
		lblNewLabel_2.setBounds(136, 123, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		textId = new JTextField();
		textId.setBounds(209, 42, 96, 19);
		contentPane.add(textId);
		textId.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(209, 78, 96, 19);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textAddress = new JTextField();
		textAddress.setBounds(209, 120, 96, 19);
		contentPane.add(textAddress);
		textAddress.setColumns(10);
		showWorkPlace();
	}
	
	private void addWorkPlace(int idOfWorkPlace, String nameOfTheWorkPlace, String addressOfTheWorkPlace) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
			ps = c.prepareStatement("insert into radnja values (?, ?, ?)");
			ps.setInt(1, idOfWorkPlace);
			ps.setString(2, nameOfTheWorkPlace);
			ps.setString(3,  addressOfTheWorkPlace);
			int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Radnja je uspjesno sacuvana");
			} else {
				JOptionPane.showMessageDialog(null, "Radnja nije sacuvana");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Radnja nije sacuvana");
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (c != null)
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	private void showWorkPlace() {
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
			s = c.createStatement();
			rs = s.executeQuery("select * from radnja");
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
			while(rs.next()) {
				Vector<Object> v = new Vector<>();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				defaultTableModel.addRow(v);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (s != null)
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (c != null)
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

}
