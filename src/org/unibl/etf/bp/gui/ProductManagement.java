package org.unibl.etf.bp.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.CallableStatement;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ProductManagement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textWorkerPlaceId;
	private JTextField textId;
	private JTextField textColorId;
	private JTextField textDescriptionId;
	private JTextField textSizeId;
	private JTextField textBrandId;
	private JTextField textCategoryId;
	private JTextField textSearch;
	private JTable table;
	private JTextField textPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductManagement frame = new ProductManagement();
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
	public ProductManagement() {
		setTitle("Upravljanje proizvodima");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 10, 10, 140);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 246, 766, 287);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cijena", "Radnja ID", "Proizvod ID", "Boja ID", "Opis ID", "Velicina ID", "Brend ID", "Kategorija ID"
			}
		));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		JLabel lblNewLabel = new JLabel("Radnja ID");
		lblNewLabel.setBounds(33, 41, 102, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Proizvod ID");
		lblNewLabel_1.setBounds(33, 70, 102, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Boja ID");
		lblNewLabel_2.setBounds(33, 99, 80, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Opis ID");
		lblNewLabel_3.setBounds(33, 128, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Velicina ID");
		lblNewLabel_4.setBounds(33, 157, 92, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Brend ID");
		lblNewLabel_5.setBounds(33, 191, 80, 13);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Kategorija ID");
		lblNewLabel_6.setBounds(33, 220, 80, 13);
		contentPane.add(lblNewLabel_6);
		
		textWorkerPlaceId = new JTextField();
		textWorkerPlaceId.setBounds(145, 38, 138, 19);
		contentPane.add(textWorkerPlaceId);
		textWorkerPlaceId.setColumns(10);
		
		textId = new JTextField();
		textId.setBounds(145, 67, 137, 19);
		contentPane.add(textId);
		textId.setColumns(10);
		
		textColorId = new JTextField();
		textColorId.setBounds(145, 96, 138, 19);
		contentPane.add(textColorId);
		textColorId.setColumns(10);
		
		textDescriptionId = new JTextField();
		textDescriptionId.setBounds(145, 125, 138, 19);
		contentPane.add(textDescriptionId);
		textDescriptionId.setColumns(10);
		
		textSizeId = new JTextField();
		textSizeId.setBounds(145, 154, 138, 19);
		contentPane.add(textSizeId);
		textSizeId.setColumns(10);
		
		textBrandId = new JTextField();
		textBrandId.setBounds(145, 183, 138, 19);
		contentPane.add(textBrandId);
		textBrandId.setColumns(10);
		
		textCategoryId = new JTextField();
		textCategoryId.setBounds(145, 217, 138, 19);
		contentPane.add(textCategoryId);
		textCategoryId.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String price = textPrice.getText();
				String workPlaceId = textWorkerPlaceId.getText();
				String id = textId.getText();
				String colorId = textColorId.getText();
				String descriptionId = textDescriptionId.getText();
				String sizeId = textSizeId.getText();
				String brandId = textBrandId.getText();
				String categoryId = textCategoryId.getText();
				addProduct(Double.parseDouble(price), Integer.parseInt(workPlaceId), Integer.parseInt(id), Integer.parseInt(colorId), Integer.parseInt(descriptionId), 
						Integer.parseInt(sizeId), Integer.parseInt(brandId), Integer.parseInt(categoryId));
				textWorkerPlaceId.setText("");
				textPrice.setText("");
				textId.setText("");
				textColorId.setText("");
				textDescriptionId.setText("");
				textSizeId.setText("");
				textBrandId.setText("");
				textCategoryId.setText("");
				model.setRowCount(0);
				showProducts();
			}
		});
		
		btnNewButton.setBounds(325, 50, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Azuriraj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String price = textPrice.getText();
				String id = textId.getText();
				String brandId = textBrandId.getText();
				String colorId = textColorId.getText();
				String descriptionId = textDescriptionId.getText();
				String sizeId = textSizeId.getText();
				String categoryId = textCategoryId.getText();
				String workPlaceId = textWorkerPlaceId.getText();
				updateProduct(Double.parseDouble(price), Integer.parseInt(workPlaceId), Integer.parseInt(id), Integer.parseInt(colorId), Integer.parseInt(descriptionId), 
						Integer.parseInt(sizeId), Integer.parseInt(brandId), Integer.parseInt(categoryId));
				textPrice.setText("");
				textId.setText("");
				textBrandId.setText("");
				textColorId.setText("");
				textDescriptionId.setText("");
				textSizeId.setText("");
				textCategoryId.setText("");
				textWorkerPlaceId.setText("");
				model.setRowCount(0);
				showProducts();
			}
		});
		btnNewButton_1.setBounds(325, 81, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Obrisi");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				deleteProduct(Integer.parseInt(id));
				textId.setText("");
				model.setRowCount(0);
				showProducts();
			}
		});
		btnNewButton_2.setBounds(325, 112, 85, 21);
		contentPane.add(btnNewButton_2);
		
		textSearch = new JTextField();
		textSearch.setBounds(535, 51, 143, 19);
		contentPane.add(textSearch);
		textSearch.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Cijena");
		lblNewLabel_8.setBounds(45, 13, 80, 13);
		contentPane.add(lblNewLabel_8);
		
		textPrice = new JTextField();
		textPrice.setBounds(146, 10, 137, 19);
		contentPane.add(textPrice);
		textPrice.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Pretrazi");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textSearch.getText());
				textSearch.setText("");
		        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
		             CallableStatement stmt = (CallableStatement) connection.prepareCall("{call prikazi_red_proizvoda(?)}")) {
		            stmt.setInt(1, id);
		            ResultSet rs = stmt.executeQuery();
		            if (rs.next()) {
		            	double cijena = rs.getDouble("Cijena");
		            	int radnjaId = rs.getInt("RADNJA_RadnjaID");
		            	int proizvodId = rs.getInt("ProizvodID");
		            	int bojaId = rs.getInt("BOJA_PROIZVODA_SIFARNIK_BojaID");
		                int opisId= rs.getInt("OPIS_PROIZVODA_SIFARNIK_OpisID");
		                int velicinaId = rs.getInt("VELICINA_PROIZVODA_SIFARNIK_VelicinaID");
		                int brendId = rs.getInt("BREND_PROIZVODA_SIFARNIK_BrendID");
		                int categoryId = rs.getInt("KATEGORIJA_PROIZVODA_SIFARNIK_KategorijaID");
		                
		                textPrice.setText(String.valueOf(cijena));
		                textWorkerPlaceId.setText(String.valueOf(radnjaId));
		                textId.setText(String.valueOf(proizvodId));
		                textColorId.setText(String.valueOf(bojaId));
		                textDescriptionId.setText(String.valueOf(opisId));
		                textSizeId.setText(String.valueOf(velicinaId));    
		                textBrandId.setText(String.valueOf(brendId));
		                textCategoryId.setText(String.valueOf(categoryId));
		            } else {
		            	textPrice.setText("");
		                textWorkerPlaceId.setText("");
		                textId.setText("");
		                textColorId.setText("");
		                textDescriptionId.setText("");
		                textSizeId.setText("");    
		                textBrandId.setText("");
		                textCategoryId.setText("");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnNewButton_3.setBounds(565, 81, 85, 21);
		contentPane.add(btnNewButton_3);
		showProducts();
	}
	
	private void addProduct(double price, int workPlaceId, int id, int colorId, int descriptionId, 
			int sizeId, int brandId, int categoryId) {
		 Connection connection = null;
	     CallableStatement callableStatement = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	            String procedureCall = "{call dodaj_proizvod(?, ?, ?, ?, ?, ?, ?, ?)}";
	            callableStatement = (CallableStatement) connection.prepareCall(procedureCall);
	            callableStatement.setDouble(1, price); 
	            callableStatement.setInt(2, workPlaceId);
	            callableStatement.setInt(3, id);
	            callableStatement.setInt(4, colorId);
	            callableStatement.setInt(5, descriptionId);
	            callableStatement.setInt(6, sizeId);
	            callableStatement.setInt(7, brandId);
	            callableStatement.setInt(8, categoryId);
	            callableStatement.execute();
				JOptionPane.showMessageDialog(null, "Uspjesno dodavanje");
	        } catch (ClassNotFoundException | SQLException e) {
	        	e.printStackTrace();
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
	
	private void updateProduct(double price, int workPlaceId, int id, int colorId, int descriptionId, 
			int sizeId, int brandId, int categoryId) {
		Connection connection = null;
	     CallableStatement callableStatement = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	            String procedureCall = "{call brisanje_proizvoda(?)}";
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
		            String procedureCall = "{call dodaj_proizvod(?, ?, ?, ?, ?, ?, ?, ?)}";
		            callableStatement1 = (CallableStatement) connection1.prepareCall(procedureCall);
		            callableStatement1.setDouble(1, price); 
		            callableStatement1.setInt(2, workPlaceId);
		            callableStatement1.setInt(3, id);
		            callableStatement1.setInt(4, colorId);
		            callableStatement1.setInt(5, descriptionId);
		            callableStatement1.setInt(6, sizeId);
		            callableStatement1.setInt(7, brandId);
		            callableStatement1.setInt(8, categoryId);
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
	
	private void deleteProduct(int productId) {
		 Connection connection = null;
	     CallableStatement callableStatement = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	            String procedureCall = "{call brisanje_proizvoda(?)}";
	            callableStatement = (CallableStatement) connection.prepareCall(procedureCall);
	            callableStatement.setInt(1, productId);
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
	
	public void showProducts() {
	    Connection c = null;
	    Statement s = null;
	    ResultSet rs = null;

	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        s = c.createStatement();
	        rs = s.executeQuery("select * from proizvodiView"); 
	        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
	        defaultTableModel.setRowCount(0);
	        while (rs.next()) {
	            Vector<Object> v = new Vector<>();
	            v.add(rs.getDouble(1));
	            v.add(rs.getInt(2));
	            v.add(rs.getInt(3));
	            v.add(rs.getInt(4));
	            v.add(rs.getInt(5));
	            v.add(rs.getInt(6));
	            v.add(rs.getInt(7));
	            v.add(rs.getInt(8));
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
