package org.unibl.etf.bp.gui;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

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
import java.awt.event.ActionEvent;

public class WorkerManagement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textPosition;
	private JTextField textSalary;
	private JTextField textWorkerId;
	private JTextField textWorkerPlaceId;
	private JTextField textSearch;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerManagement frame = new WorkerManagement();
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
	public WorkerManagement() {
		setTitle("Upravljanje radnicima");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 10, 10, 140);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 231, 741, 311);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ime", "Prezime", "Pozicija", "Plata", "Radnik ID", "Radnja ID"
			}
		));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		JLabel lblNewLabel = new JLabel("Ime");
		lblNewLabel.setBounds(52, 37, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prezime");
		lblNewLabel_1.setBounds(52, 66, 62, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Pozicija");
		lblNewLabel_2.setBounds(52, 95, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Plata");
		lblNewLabel_3.setBounds(52, 126, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Radnik ID");
		lblNewLabel_4.setBounds(52, 155, 62, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Radnja ID");
		lblNewLabel_5.setBounds(52, 185, 62, 13);
		contentPane.add(lblNewLabel_5);
		
		textName = new JTextField();
		textName.setBounds(107, 34, 143, 19);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textSurname = new JTextField();
		textSurname.setBounds(107, 63, 143, 19);
		contentPane.add(textSurname);
		textSurname.setColumns(10);
		
		textPosition = new JTextField();
		textPosition.setBounds(107, 92, 143, 19);
		contentPane.add(textPosition);
		textPosition.setColumns(10);
		
		textSalary = new JTextField();
		textSalary.setBounds(107, 123, 143, 19);
		contentPane.add(textSalary);
		textSalary.setColumns(10);
		
		textWorkerId = new JTextField();
		textWorkerId.setBounds(107, 152, 143, 19);
		contentPane.add(textWorkerId);
		textWorkerId.setColumns(10);
		
		textWorkerPlaceId = new JTextField();
		textWorkerPlaceId.setBounds(107, 182, 143, 19);
		contentPane.add(textWorkerPlaceId);
		textWorkerPlaceId.setColumns(10);
		
		JButton btnNewButton = new JButton("Pretrazi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textSearch.getText());
				textSearch.setText("");
		        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
		             CallableStatement stmt = (CallableStatement) connection.prepareCall("{call prikazi_red_radnika(?)}")) {
		            stmt.setInt(1, id);
		            ResultSet rs = stmt.executeQuery();
		            if (rs.next()) {
		            	String ime = rs.getString("Ime");
		            	String prezime = rs.getString("Prezime");
		            	String pozicija = rs.getString("Pozicija");
		            	double plata = rs.getDouble("Plata");
		                int radnikId = rs.getInt("RADNIK_SIFARNIK_RadnikID");
		                int radnjaId = rs.getInt("RADNJA_RadnjaID");
		                
		                textName.setText(ime);
		                textSurname.setText(prezime);
		                textPosition.setText(pozicija);
		                textSalary.setText(String.valueOf(plata));
		                textWorkerId.setText(String.valueOf(radnikId));
		                textWorkerPlaceId.setText(String.valueOf(radnjaId));      
		            } else {
		                textName.setText("");
		                textSurname.setText("");
		                textPosition.setText("");
		                textSalary.setText("");
		                textWorkerId.setText("");
		                textWorkerPlaceId.setText("");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnNewButton.setBounds(587, 77, 85, 21);
		contentPane.add(btnNewButton);
		
		textSearch = new JTextField();
		textSearch.setBounds(556, 48, 143, 19);
		contentPane.add(textSearch);
		textSearch.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Dodaj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText();
				String surname = textSurname.getText();
				String position = textPosition.getText();
				String salary = textSalary.getText();
				String workerId = textWorkerId.getText();
				String workerPlaceId = textWorkerPlaceId.getText();
				addWorker(name, surname, position, Double.parseDouble(salary), Integer.parseInt(workerId), Integer.parseInt(workerPlaceId));
				textName.setText("");
				textSurname.setText("");
				textPosition.setText("");
				textSalary.setText("");
				textWorkerId.setText("");
				textWorkerPlaceId.setText("");;
				model.setRowCount(0);
				showWorkers();
			}
		});
		btnNewButton_1.setBounds(317, 78, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Azuriraj");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText();
				String surname = textSurname.getText();
				String position = textPosition.getText();
				String salary = textSalary.getText();
				String workerId = textWorkerId.getText();
				String workerPlaceId = textWorkerPlaceId.getText();
				updateWorker(name, surname, position, Double.parseDouble(salary), Integer.parseInt(workerId), Integer.parseInt(workerPlaceId));
				textName.setText("");
				textSurname.setText("");
				textPosition.setText("");
				textSalary.setText("");
				textWorkerId.setText("");
				textWorkerPlaceId.setText("");;
				model.setRowCount(0);
				showWorkers();
			}
		});
		btnNewButton_2.setBounds(317, 111, 85, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Obrisi");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textWorkerId.getText();
				deleteWorker(Integer.parseInt(id));
				textWorkerId.setText("");
				model.setRowCount(0);
				showWorkers();
			}
		});
		btnNewButton_3.setBounds(317, 142, 85, 21);
		contentPane.add(btnNewButton_3);
		showWorkers();
	}
	
	private void addWorker(String name, String surname, String position, double salary, int workerId, int workerPlaceId) {
		 Connection connection = null;
	     CallableStatement callableStatement = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	            String procedureCall = "{call dodaj_radnika(?, ?, ?, ?, ?, ?)}";
	            callableStatement = (CallableStatement) connection.prepareCall(procedureCall);
	            callableStatement.setString(1, name); 
	            callableStatement.setString(2, surname);
	            callableStatement.setString(3, position);
	            callableStatement.setDouble(4, salary);
	            callableStatement.setInt(5, workerId);
	            callableStatement.setInt(6, workerPlaceId);
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
	
	private void updateWorker(String name, String surname, String position, double salary, int workerId, int workerPlaceId) {
		 Connection connection = null;
	     CallableStatement callableStatement = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	            String procedureCall = "{call brisanje_radnika(?)}";
	            callableStatement = (CallableStatement) connection.prepareCall(procedureCall);
	            callableStatement.setInt(1, workerId);
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
	            String procedureCall = "{call dodaj_radnika(?, ?, ?, ?, ?, ?)}";
	            callableStatement1 = (CallableStatement) connection1.prepareCall(procedureCall);
	            callableStatement1.setString(1, name); 
	            callableStatement1.setString(2, surname);
	            callableStatement1.setString(3, position);
	            callableStatement1.setDouble(4, salary);
	            callableStatement1.setInt(5, workerId);
	            callableStatement1.setInt(6, workerPlaceId);
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
	
	private void deleteWorker(int workerId) {
		 Connection connection = null;
	     CallableStatement callableStatement = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	            String procedureCall = "{call brisanje_radnika(?)}";
	            callableStatement = (CallableStatement) connection.prepareCall(procedureCall);
	            callableStatement.setInt(1, workerId);
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
	
	public void showWorkers() {
	    Connection c = null;
	    Statement s = null;
	    ResultSet rs = null;

	    try {
	        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
	        s = c.createStatement();
	        rs = s.executeQuery("select * from radniciView"); 
	        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
	        defaultTableModel.setRowCount(0);
	        while (rs.next()) {
	            Vector<Object> v = new Vector<>();
	            v.add(rs.getString(1));
	            v.add(rs.getString(2));
	            v.add(rs.getString(3));
	            v.add(rs.getDouble(4));
	            v.add(rs.getInt(5));
	            v.add(rs.getInt(6));
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
