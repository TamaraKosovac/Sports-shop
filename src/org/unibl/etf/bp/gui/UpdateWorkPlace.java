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
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateWorkPlace extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
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
					UpdateWorkPlace frame = new UpdateWorkPlace();
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
	public UpdateWorkPlace() {
		setTitle("Azuriraj radnju");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 10, 10, 140);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 220, 729, 318);
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
		JLabel lblNewLabel = new JLabel("Radnja ID");
		lblNewLabel.setBounds(104, 41, 63, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Naziv");
		lblNewLabel_1.setBounds(104, 82, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Adresa");
		lblNewLabel_2.setBounds(104, 119, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		textId = new JTextField();
		textId.setBounds(177, 38, 96, 19);
		contentPane.add(textId);
		textId.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(177, 79, 96, 19);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textAddress = new JTextField();
		textAddress.setBounds(177, 119, 96, 19);
		contentPane.add(textAddress);
		textAddress.setColumns(10);
		
		JButton btnNewButton = new JButton("Azuriraj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				String name = textName.getText();
				String address = textAddress.getText();
				updateWorkPlace(Integer.parseInt(id), name, address);
				textId.setText("");
				textName.setText("");
				textAddress.setText("");
				model.setRowCount(0);
				showWorkPlace();
			}
		});
		btnNewButton.setBounds(375, 149, 85, 21);
		contentPane.add(btnNewButton);
		showWorkPlace();
	}

	private void updateWorkPlace(int idOfWorkPlace, String newNameOfTheWorkPlace, String addressOfTheWorkPlace) {
	    Connection c = null;
	    PreparedStatement ps = null;
	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        ps = c.prepareStatement("update radnja set Naziv = ?, Adresa = ? where RadnjaID = ?");
	        ps.setString(1, newNameOfTheWorkPlace);
	        ps.setString(2, addressOfTheWorkPlace);
	        ps.setInt(3, idOfWorkPlace);
	        int tmp = ps.executeUpdate();
			if(tmp==1) {
				JOptionPane.showMessageDialog(null, "Radnja je uspjesno azurirana");
			} else {
				JOptionPane.showMessageDialog(null, "Radnja nije azurirana");
			}
	    } catch (SQLException e) {
	    	e.printStackTrace();
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
