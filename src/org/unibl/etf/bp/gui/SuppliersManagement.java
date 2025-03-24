package org.unibl.etf.bp.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.CallableStatement;

import javax.swing.JScrollPane;

public class SuppliersManagement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textAddress;
	private JTextField textContact;
	private JTextField textId;
	private JTextField textSearch;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SuppliersManagement frame = new SuppliersManagement();
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
	public SuppliersManagement() {
		setTitle("Upravljanje dobavljacima");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 10, 10, 140);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 213, 739, 325);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Adresa", "Kontakt", "Dobavljac ID"
			}
		));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		JLabel lblNewLabel = new JLabel("Adresa");
		lblNewLabel.setBounds(37, 54, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kontakt");
		lblNewLabel_1.setBounds(37, 77, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Dobavljac ID");
		lblNewLabel_2.setBounds(37, 109, 74, 13);
		contentPane.add(lblNewLabel_2);
		
		textAddress = new JTextField();
		textAddress.setBounds(114, 51, 142, 19);
		contentPane.add(textAddress);
		textAddress.setColumns(10);
		
		textContact = new JTextField();
		textContact.setBounds(114, 77, 142, 19);
		contentPane.add(textContact);
		textContact.setColumns(10);
		
		textId = new JTextField();
		textId.setBounds(114, 106, 142, 19);
		contentPane.add(textId);
		textId.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				String address = textAddress.getText();
				String contact = textContact.getText();
				addSupplier(address, contact, Integer.parseInt(id));
				textId.setText("");
				textAddress.setText("");
				textContact.setText("");
				model.setRowCount(0);
				showSuppliers();
			}
		});
		btnNewButton.setBounds(321, 50, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Azuriraj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				String address = textAddress.getText();
				String contact = textContact.getText();
				updateSupplier(address, contact, Integer.parseInt(id));
				textId.setText("");
				textAddress.setText("");
				textContact.setText("");
				model.setRowCount(0);
				showSuppliers();
			}
		});
		btnNewButton_1.setBounds(321, 77, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Obrisi");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				deleteSupplier(Integer.parseInt(id));
				textId.setText("");
				model.setRowCount(0);
				showSuppliers();
			}
		});
		btnNewButton_2.setBounds(321, 105, 85, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Pretrazi");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textSearch.getText());
				textSearch.setText("");
		        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
		             CallableStatement stmt = (CallableStatement) connection.prepareCall("{call prikazi_red_dobavljaca(?)}")) {
		            stmt.setInt(1, id);
		            ResultSet rs = stmt.executeQuery();
		            if (rs.next()) {
		            	String adresa = rs.getString("Adresa");
		            	String kontakt = rs.getString("Kontakt");
		                int id1 = rs.getInt("DOBAVLJAC_SIFARNIK_DobavljacID");
		                textId.setText(String.valueOf(id1));
		                textContact.setText(kontakt);
		                textAddress.setText(adresa);
		            } else {
		                textId.setText("");
		                textContact.setText("");
		                textAddress.setText("");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnNewButton_3.setBounds(564, 69, 85, 21);
		contentPane.add(btnNewButton_3);
		
		textSearch = new JTextField();
		textSearch.setBounds(531, 40, 142, 19);
		contentPane.add(textSearch);
		textSearch.setColumns(10);
		
		showSuppliers();
	}
	
	
	private void addSupplier(String address, String contact, int id) {
		 Connection connection = null;
	     CallableStatement callableStatement = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	            String procedureCall = "{call dodaj_dobavljaca(?, ?, ?)}";
	            callableStatement = (CallableStatement) connection.prepareCall(procedureCall);
	            callableStatement.setString(1, address); 
	            callableStatement.setString(2, contact);
	            callableStatement.setInt(3, id);
	            callableStatement.execute();
			    JOptionPane.showMessageDialog(null, "Uspjesno dodavanje");
	        } catch (ClassNotFoundException | SQLException e) {
	        	JOptionPane.showMessageDialog(null, "Neuspjesno dodavanje");
	        } finally {
	            try {
	                if (callableStatement != null) callableStatement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	}
	
	private void updateSupplier(String address, String contact, int id) {
		 Connection connection = null;
	     CallableStatement callableStatement = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	            String procedureCall = "{call brisanje_dobavljaca(?)}";
	            callableStatement = (CallableStatement) connection.prepareCall(procedureCall);
	            callableStatement.setInt(1, id);
	            callableStatement.execute();
	        } catch (ClassNotFoundException | SQLException e) {
	        } finally {
	            try {
	                if (callableStatement != null) callableStatement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        
	         Connection connection1 = null;
		     CallableStatement callableStatement1 = null;
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
		            String procedureCall = "{call dodaj_dobavljaca(?, ?, ?)}";
		            callableStatement1 = (CallableStatement) connection1.prepareCall(procedureCall);
		            callableStatement1.setString(1, address); 
		            callableStatement1.setString(2, contact);
		            callableStatement1.setInt(3, id);
		            callableStatement1.execute();
		        } catch (ClassNotFoundException | SQLException e) {
		        } finally {
		            try {
		                if (callableStatement1 != null) callableStatement1.close();
		                if (connection1 != null) connection1.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        JOptionPane.showMessageDialog(null, "Uspjesno azuriranje");
	}
	
	private void deleteSupplier(int supplierId) {
		 Connection connection = null;
	     CallableStatement callableStatement = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	            String procedureCall = "{call brisanje_dobavljaca(?)}";
	            callableStatement = (CallableStatement) connection.prepareCall(procedureCall);
	            callableStatement.setInt(1, supplierId);
	            callableStatement.execute();
	            JOptionPane.showMessageDialog(null, "Uspjesno brisanje");
	        } catch (ClassNotFoundException | SQLException e) {
	        	JOptionPane.showMessageDialog(null, "Neuspjesno brisanje");
	        } finally {
	            try {
	                if (callableStatement != null) callableStatement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	}
	
	private void showSuppliers() {
		Connection c = null;
	    Statement s = null;
	    ResultSet rs = null;

	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        s = c.createStatement();
	        rs = s.executeQuery("select * from dobavljaciView"); 
	        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
	        defaultTableModel.setRowCount(0);
	        while (rs.next()) {
	            Vector<Object> v = new Vector<>();
	            v.add(rs.getString(1));
	            v.add(rs.getString(2));
	            v.add(rs.getString(3));
	            defaultTableModel.addRow(v);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (s != null) {
	            try {
	                s.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (c != null) {
	            try {
	                c.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
}
