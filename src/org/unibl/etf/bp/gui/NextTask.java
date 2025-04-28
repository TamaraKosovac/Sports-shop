package org.unibl.etf.bp.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.sifarnici.Task;

import javax.swing.JLabel;
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
import javax.swing.JScrollPane;

public class NextTask extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textId;
	private JTextField textName;
	private JTable table;
	private Task task = new Task();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NextTask frame = new NextTask();
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
	public NextTask() {
		setTitle("Upravljanje zadacima");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 10, 10, 140);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 223, 729, 300);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Zadatak  ID", "Opis"
			}
		));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		JLabel lblNewLabel = new JLabel("Zadatak ID");
		lblNewLabel.setBounds(169, 46, 63, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Opis");
		lblNewLabel_1.setBounds(169, 90, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		textId = new JTextField();
		textId.setBounds(255, 43, 96, 19);
		contentPane.add(textId);
		textId.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(255, 87, 96, 19);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				String name = textName.getText();
				task.addTask(Integer.parseInt(id), name);
				textId.setText("");
				textName.setText("");
				model.setRowCount(0);
				showTask();
			}
		});
		btnNewButton.setBounds(129, 174, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Azuriraj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				String name = textName.getText();
				task.updateTask(Integer.parseInt(id), name);
				textId.setText("");
				textName.setText("");
				model.setRowCount(0);
				showTask();
			}
		});
		btnNewButton_1.setBounds(324, 174, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Obrisi");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textId.getText();
				task.deleteTask(Integer.parseInt(id));
				textId.setText("");
				model.setRowCount(0);
				showTask();
			}
		});
		btnNewButton_2.setBounds(551, 174, 85, 21);
		contentPane.add(btnNewButton_2);
		showTask();
	}
	
	public void showTask() {
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "rootstudent123");
			s = c.createStatement();
			rs = s.executeQuery("select * from zadatak_sifarnik");
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
			while(rs.next()) {
				Vector<Object> v = new Vector<>();
				v.add(rs.getInt(1));
				v.add(rs.getString(2));
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
